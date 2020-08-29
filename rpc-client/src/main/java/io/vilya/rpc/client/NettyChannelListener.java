/**
 * 
 */
package io.vilya.rpc.client;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public interface NettyChannelListener {

	void onConnected();
	
	void onClosed();
	
}
