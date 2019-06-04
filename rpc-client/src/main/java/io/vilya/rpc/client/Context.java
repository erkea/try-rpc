/**
 * 
 */
package io.vilya.rpc.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.vilya.rpc.common.CallException;
import io.vilya.rpc.common.CallRequest;
import io.vilya.rpc.common.CallResponse;

/**
 * TODO 临时
 * @author erkea <erkea@vilya.io>
 *
 */
public class Context {
	
	private static final Logger log = LoggerFactory.getLogger(Context.class);

	private static final ConcurrentHashMap<Long, CompletableFuture<CallResponse>> futureMap = new ConcurrentHashMap<>();
	
	private static ChannelHandlerContext chc;
	
	private Context() {}
	
	public static void setContext(ChannelHandlerContext context) {
		chc = context;
		log.info("setContext: {}", context);
	}
	
	public static Object request(CallRequest invocation) {
		CompletableFuture<CallResponse> future = new CompletableFuture<>();
		futureMap.put(invocation.getId(), future);
		chc.writeAndFlush(invocation);
		
		CallResponse response = null;
		try {
			response = future.get();		// TODO timeout
		} catch (InterruptedException e) {	// TODO InterruptedException
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw new CallException(e);
		}
		return response == null ? null : response.getData();
	}
	
	public static void onResponse(CallResponse response) {
		CompletableFuture<CallResponse> future = futureMap.get(response.getId());
		if (future == null) {
			return;
		}
		
		future.complete(response);
	}
	
}
