/**
 * 
 */
package io.vilya.rpc.client;

import java.lang.reflect.Method;
import java.util.Objects;

import com.google.common.reflect.AbstractInvocationHandler;

import io.vilya.rpc.client.sequence.DefaultSequenceGenerator;
import io.vilya.rpc.client.sequence.SequenceGenerator;
import io.vilya.rpc.common.CallRequest;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ConsumerProxy extends AbstractInvocationHandler {

	private SequenceGenerator sequenceGenerator = new DefaultSequenceGenerator();
	
	private ConsumerContext context;
	
	private Class<?> type;
	
	public ConsumerProxy(ConsumerContext context, Class<?> type) {
		this.context = Objects.requireNonNull(context, "context");
		this.type = Objects.requireNonNull(type, "type");
	}
	
	@Override
	protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
		return context.request(new CallRequest(sequenceGenerator.generate(), type.getName(), method.getName(), args));
	}
	
}
