/**
 * 
 */
package io.vilya.rpc.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderInvoker {
	
	private ProviderRegistry registry;
	
	public ProviderInvoker(ProviderRegistry registry) {
		this.registry = registry;
	}

	public Object invoke(ProviderInvocation invocation) {
		try {
			Object object = registry.getObject(invocation.getType());
			Method method = object.getClass().getMethod(invocation.getMethod());
			return method.invoke(object, invocation.getArgs());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e); // TODO custom exception
		}
	}
}
