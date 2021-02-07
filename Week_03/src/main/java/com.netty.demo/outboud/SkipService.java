package com.netty.demo.outboud;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wangdan
 * @date 2021/1/31
 */
public interface SkipService {
    void skip(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
