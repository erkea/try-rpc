/**
 * 
 */
package io.vilya.rpc.common;

import java.io.Serializable;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class CallRequest implements Serializable {
	
	private static final long serialVersionUID = -1650625597080167305L;

	private Long id;

	private String type;
	
	private String method;
	
	private Object[] args;

	/**
	 * @param type
	 * @param method
	 * @param args
	 */
	public CallRequest(Long id, String type, String method, Object[] args) {
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
