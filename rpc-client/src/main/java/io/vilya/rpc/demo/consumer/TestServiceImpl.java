/**
 * 
 */
package io.vilya.rpc.demo.consumer;

import io.vilya.rpc.demo.ProviderProxy;
import io.vilya.rpc.demo.provider.LocationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class TestServiceImpl implements TestService {

	private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public void test() {
		LocationProvider locationProvider = ProviderProxy.newProxy(LocationProvider.class);
		log.info(locationProvider.getLocation());
	}

}
