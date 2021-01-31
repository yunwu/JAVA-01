package com.netty.demo.filter;


import com.netty.demo.exceptions.FilterException;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class PreRequestFilter implements RequestFilter {

    @Override
    public void filter(FullHttpRequest fullHttpRequest, FullHttpResponse fullHttpResponse) {
        //鉴权，校验token
        if (!fullHttpRequest.headers().get("token").equals("123")){
            throw new FilterException(1, "身份异常");
        }
        fullHttpRequest.headers().add("pre", true);
    }
}
