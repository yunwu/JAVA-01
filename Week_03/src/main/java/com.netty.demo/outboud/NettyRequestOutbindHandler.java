package com.netty.demo.outboud;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wangdan TODO 这里需要在查一下资料验证真伪
 * @date 2021/1/31
 */
public class NettyRequestOutbindHandler extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        FullHttpRequest request = (FullHttpRequest) o;
        channelHandlerContext.writeAndFlush(request);
    }
}
