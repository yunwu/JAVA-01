package com.netty.demo.outboud;

import com.netty.demo.filter.PreRequestFilter;
import com.netty.demo.filter.RequestFilter;
import com.netty.demo.filter.SubRequestFilter;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author wangdan
 * @date 2021/1/31
 */
@Slf4j
public class OkHttpSkipServiceImpl implements SkipService{

    private final OkHttpClient mOkHttpClient;
    private final ExecutorService proxyService;
    private final RequestFilter preFilter;
    private final RequestFilter subFilter;



    public OkHttpSkipServiceImpl(){
        mOkHttpClient = new OkHttpClient();
        preFilter = new PreRequestFilter();
        subFilter = new SubRequestFilter();
        int cors = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cors, cors, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory("proxyService"),handler);
    }

    @Override
    public void skip(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        preFilter.filter(fullHttpRequest, null);
        proxyService.submit(()->sendGet(fullHttpRequest, ctx, fullHttpRequest.uri()));
    }
    private void sendGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url).get();
        for (String name : inbound.headers().names()) {
            requestBuilder.addHeader(name, inbound.headers().get(name));
        }

        FullHttpResponse fullHttpResponse = null;
        try {
            Response response = mOkHttpClient.newCall(requestBuilder.build()).execute();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(response.peekBody( Integer.parseInt(response.headers().get("Content-Length"))).string().getBytes()));
            subFilter.filter(null,fullHttpResponse);
            ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            log.error("okhttp方式出现跳转异常,request:{},error:{}", inbound, e);
        }finally {
            if (!HttpUtil.isKeepAlive(inbound)) {
                ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(fullHttpResponse);
            }
        }
        ctx.flush();
    }

}
