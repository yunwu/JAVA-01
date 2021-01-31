package com.netty.demo.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class SubRequestFilter implements RequestFilter {

    @Override
    public void filter(FullHttpRequest fullHttpRequest, FullHttpResponse fullHttpResponse) {
        fullHttpResponse.headers().set("Content-Type", "application/json");
        fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(fullHttpResponse.headers().get("Content-Length")));
    }
}
