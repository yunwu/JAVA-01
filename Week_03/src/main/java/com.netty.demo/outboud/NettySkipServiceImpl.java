package com.netty.demo.outboud;

import com.netty.demo.filter.PreRequestFilter;
import com.netty.demo.filter.RequestFilter;
import com.netty.demo.filter.SubRequestFilter;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.OkHttpClient;

import java.util.concurrent.*;

/**
 * @author wangdan
 * @date 2021/1/31
 */
public class NettySkipServiceImpl implements SkipService{

    private final NettyClient nettyClient = NettyClient.nettyClient;

    public void skip(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        String url = fullHttpRequest.uri();
        nettyClient.connect(url, fullHttpRequest);
    }


}
