/**
 * 
 */
package io.vilya.rpc.demo;

import com.google.common.collect.ImmutableMap;

import io.vilya.rpc.demo.provider.LocationProvider;
import io.vilya.rpc.demo.provider.LocationProviderImpl;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderRegistry {

	private ImmutableMap<Class<?>, Object> providers = ImmutableMap.<Class<?>, Object>builder()
			.put(LocationProvider.class, new LocationProviderImpl())
			.build();
	
	public Object getObject(Class<?> type) {
		return providers.get(type);
	}
	
}
