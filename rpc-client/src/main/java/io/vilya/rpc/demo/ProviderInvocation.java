/**
 * 
 */
package io.vilya.rpc.demo;

import java.lang.reflect.Method;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderInvocation {

	private Class<?> type;
	
	private Method method;
	
	private Object[] args;

	/**
	 * @param type
	 * @param method
	 * @param args
	 */
	public ProviderInvocation(Class<?> type, Method method, Object[] args) {
		this.type = type;
		this.method = method;
		this.args = args;
	}

	/**
	 * @return the type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}
	
}
