package com.netty.demo.outboud;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author wangdan
 * @date 2021/1/31
 */
public class NettyRequestHandller extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    //异步调用读取管道数据
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response= (FullHttpResponse) msg;
        //System.out.println("获取到服务器返回值"+responseJson);
        ctx.writeAndFlush(response);

    }

}
