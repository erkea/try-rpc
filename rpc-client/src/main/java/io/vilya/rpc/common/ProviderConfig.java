/**
 * 
 */
package io.vilya.rpc.common;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderConfig {

	private String address;
	
	private Integer port;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
