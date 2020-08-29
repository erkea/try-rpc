/**
 * 
 */
package io.vilya.rpc.common;

import java.io.Serializable;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class CallResponse implements Serializable {
	
	private static final long serialVersionUID = -8625276023797560662L;

	private Long id;
	
	private Object data;
	
	private Throwable exception;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the exception
	 */
	public Throwable getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
}
