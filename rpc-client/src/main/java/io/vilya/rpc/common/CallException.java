/**
 * 
 */
package io.vilya.rpc.common;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class CallException extends RpcUncheckedException {
	
	private static final long serialVersionUID = 1146250738360079863L;

	public CallException(Throwable cause) {
		super(cause);
	}
	
}
