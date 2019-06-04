/**
 * 
 */
package io.vilya.rpc.server;

import com.google.common.collect.ImmutableMap;

import io.vilya.rpc.demo.provider.LocationProvider;
import io.vilya.rpc.demo.provider.LocationProviderImpl;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderRegistry {

	private ImmutableMap<String, Object> providers = ImmutableMap.<String, Object>builder()
			.put(LocationProvider.class.getName(), new LocationProviderImpl())
			.build();
	
	public Object getObject(String type) {
		return providers.get(type);
	}
	
}
