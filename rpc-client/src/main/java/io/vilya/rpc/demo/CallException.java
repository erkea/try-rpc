/**
 * 
 */
package io.vilya.rpc.demo;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class CallException extends RuntimeException {
	
	private static final long serialVersionUID = 1146250738360079863L;

	public CallException(Throwable cause) {
		super(cause);
	}
	
}
