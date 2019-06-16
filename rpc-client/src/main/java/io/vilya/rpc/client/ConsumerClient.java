/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.vilya.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public final class ConsumerClient {

	private static final Logger log = LoggerFactory.getLogger(ConsumerClient.class);

	private static final String HOST = System.getProperty("host", "127.0.0.1");
	private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

	private ConsumerContext context;

	public ConsumerClient(ConsumerContext context) {
		this.context = Objects.requireNonNull(context, "context");
	}

	public void start(NettyChannelListener listener) throws InterruptedException {
		Bootstrap b = new Bootstrap();
		b.group(context.getEventLoopGroup()).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						p.addLast(new ObjectEncoder());
						p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
						p.addLast(new ConsumerChannelHandler(context));
					}
				});
		ChannelFuture f = b.connect(HOST, PORT).sync();
		listener.onConnected();
		f.channel().closeFuture().addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				listener.onClosed();
			}
		});
	}
}
