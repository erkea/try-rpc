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
package io.vilya.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.vilya.rpc.common.CallRequest;
import io.vilya.rpc.common.CallResponse;

/**
 * Handles both client-side and server-side handler depending on which
 * constructor was called.
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {

	private ProviderInvoker invoker;
	
	public RpcServerHandler(ProviderInvoker invoker) {
		this.invoker = invoker;
	}
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	if (msg instanceof CallRequest) {
            CallRequest invocation = (CallRequest) msg;
    		Object ret = invoker.invoke(invocation);
    		CallResponse callResponse = new CallResponse();
            callResponse.setId(invocation.getId());
            callResponse.setData(ret);
    		ctx.write(callResponse);
    	} else {
    		ctx.fireChannelRead(msg);
    	}
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
