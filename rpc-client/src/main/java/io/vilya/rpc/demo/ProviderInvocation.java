/**
 * 
 */
package io.vilya.rpc.demo;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderInvocation implements Serializable {
	
	private Long id;

	private String type;
	
	private String method;
	
	private Object[] args;

	/**
	 * @param type
	 * @param method
	 * @param args
	 */
	public ProviderInvocation(Long id, String type, String method, Object[] args) {
		this.id = id;
		this.type = type;
		this.method = method;
		this.args = args;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}
	
}
