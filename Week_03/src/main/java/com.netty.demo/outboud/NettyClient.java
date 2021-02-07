package com.netty.demo.outboud;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

/**
 * @author wangdan
 * @date 2021/1/31
 */
public class NettyClient {

    public static ChannelPoolMap<InetSocketAddress, FixedChannelPool> poolMap;

    private static Bootstrap bootstrap = new Bootstrap();

    public static NettyClient nettyClient = new NettyClient();

    static {
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true);
    }

    private NettyClient(){

       init();
    }

    public void init(){
      poolMap = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
          @Override
          protected FixedChannelPool newPool(InetSocketAddress inetSocketAddress) {
              ChannelPoolHandler handler = new ChannelPoolHandler() {
                  @Override
                  public void channelReleased(Channel channel) throws Exception {

                  }

                  @Override
                  public void channelAcquired(Channel channel) throws Exception {

                  }

                  @Override
                  public void channelCreated(Channel channel) throws Exception {
                      channel.pipeline().addLast(new NettyRequestHandller());//添加相应回调处理
                  }
              };

              return new FixedChannelPool(bootstrap.remoteAddress(inetSocketAddress), handler, 5);
          }
      };

    }

    public void connect(String url, final FullHttpRequest request){
        String[] urlinfo = url.split(":");
        InetSocketAddress address = new InetSocketAddress(urlinfo[0], Integer.parseInt(urlinfo[1]));
        final SimpleChannelPool pool = NettyClient.poolMap.get(address);
        Future<Channel> future = pool.acquire();
        future.addListener(new GenericFutureListener<Future<? super Channel>>() {
            @Override
            public void operationComplete(Future<? super Channel> future) throws Exception {
                if (future.isSuccess()){
                    Channel ch = (Channel) future.getNow();
                    ch.writeAndFlush(request);
                    pool.release(ch);
                }
            }
        });
        try{
            synchronized (request){
                request.wait();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
