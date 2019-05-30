/**
 * 
 */
package io.vilya.rpc.demo;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class CallResponse {
	
	private Long id;
	
	private Object data;

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
	
}
