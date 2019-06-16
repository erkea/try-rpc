/**
 * 
 */
package io.vilya.rpc.client;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public interface NettyChannelListener {

	void onConnected();
	
	void onClosed();
	
}
