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

import java.util.Objects;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.vilya.rpc.common.CallResponse;
import io.vilya.rpc.client.ConsumerContext;

public class ConsumerChannelHandler extends ChannelInboundHandlerAdapter {
	
	private ConsumerContext context;
	
	public ConsumerChannelHandler(ConsumerContext context) {
		this.context = Objects.requireNonNull(context, "context");
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		context.setNettyContext(ctx);
	}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	if (msg instanceof CallResponse) {
    		context.onResponse((CallResponse) msg);
    	}
    	super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
