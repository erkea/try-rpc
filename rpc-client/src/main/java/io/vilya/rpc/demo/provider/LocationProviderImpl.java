/**
 * 
 */
package io.vilya.rpc.demo.provider;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class LocationProviderImpl implements LocationProvider {

	@Override
	public String getLocation() {
		return "Hangzhou";
	}

}
