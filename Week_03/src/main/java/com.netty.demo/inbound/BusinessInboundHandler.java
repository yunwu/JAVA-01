package com.netty.demo.inbound;

import com.netty.demo.outboud.SkipService;
import com.netty.demo.router.RandomRouter;
import com.netty.demo.router.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class BusinessInboundHandler extends ChannelInboundHandlerAdapter {

    private static final String urls = "http://localhost:8801,http://localhost:8802";

    private SkipService skipService;

    public BusinessInboundHandler(SkipService skipService){
        skipService = skipService;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            //TODO router
            Router router = new RandomRouter();
            String url = router.router(urls);
            fullRequest.setUri(url);
            //filter

            skipService.skip(fullRequest, ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
