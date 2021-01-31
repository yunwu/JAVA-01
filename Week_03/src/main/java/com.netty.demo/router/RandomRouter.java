package com.netty.demo.router;

import java.util.Random;

public class RandomRouter implements Router {

    @Override
    public String router(String urls) {
        String[] urlArr = urls.split(",");
        Random random = new Random(System.currentTimeMillis());
        return urlArr[random.nextInt(urlArr.length)];
    }
}
