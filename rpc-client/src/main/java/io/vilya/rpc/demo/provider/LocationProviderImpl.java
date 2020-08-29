/**
 * 
 */
package io.vilya.rpc.demo.provider;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class LocationProviderImpl implements LocationProvider {

	@Override
	public String getLocation() {
		return "Hangzhou";
	}

}
