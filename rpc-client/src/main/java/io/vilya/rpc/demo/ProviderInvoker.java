/**
 * 
 */
package io.vilya.rpc.demo;

import java.lang.reflect.InvocationTargetException;

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
			return invocation.getMethod().invoke(object, invocation.getArgs());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e); // TODO custom exception
		}
	}
}
