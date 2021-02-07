package com.netty.demo.filter;

import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public interface RequestFilter {

    void filter(FullHttpRequest fullHttpRequest, FullHttpResponse fullHttpResponse);
}
