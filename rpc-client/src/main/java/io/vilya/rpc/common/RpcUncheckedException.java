/**
 * 
 */
package io.vilya.rpc.common;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class RpcUncheckedException extends RuntimeException {

	private static final long serialVersionUID = 1813341775963695393L;

	public RpcUncheckedException(Throwable cause) {
		super(cause);
	}
	
}
