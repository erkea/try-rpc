/**
 * 
 */
package io.vilya.rpc.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.Reflection;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.vilya.rpc.common.CallException;
import io.vilya.rpc.common.CallRequest;
import io.vilya.rpc.common.CallResponse;
import io.vilya.rpc.common.RpcUncheckedException;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class ConsumerContext {

	private static final Logger log = LoggerFactory.getLogger(ConsumerContext.class);

	private final ConcurrentHashMap<Long, CompletableFuture<CallResponse>> futureMap = new ConcurrentHashMap<>();

	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(1);

	private ChannelHandlerContext nettyContext;

	private ConsumerClient client;
	
	private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

	public ConsumerContext() {
		this.client = new ConsumerClient(this);
	}

	public Object request(CallRequest invocation) {
		CompletableFuture<CallResponse> future = new CompletableFuture<>();
		futureMap.put(invocation.getId(), future);
		nettyContext.writeAndFlush(invocation);

		CallResponse response = null;
		try {
			response = future.get(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException | TimeoutException e) {
			throw new CallException(e);
		}
		return response == null ? null : response.getData();
	}

	public void onResponse(CallResponse response) {
		CompletableFuture<CallResponse> future = futureMap.get(response.getId());
		if (future == null) {
			return;
		}

		future.complete(response);
	}

	public <T> T newProxy(Class<T> type) {
		return Reflection.newProxy(type, new ConsumerProxy(this, type));
	}

	public void setNettyContext(ChannelHandlerContext nettyContext) {
		this.nettyContext = nettyContext;
	}
	
	public EventLoopGroup getEventLoopGroup() {
		return eventLoopGroup;
	}

	public void start() {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		eventLoopGroup.submit(() -> {
			try {
				client.start(new NettyChannelListener() {
					@Override
					public void onConnected() {
						countDownLatch.countDown();
					}

					@Override
					public void onClosed() {
					}
				});
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		log.info("started");
	}
}
