/**
 * 
 */
package io.vilya.rpc.demo.consumer;

import io.vilya.rpc.client.ConsumerContext;
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
		ConsumerContext context = new ConsumerContext();
		context.start();
		LocationProvider locationProvider = context.newProxy(LocationProvider.class);
		String location = locationProvider.getLocation();
		log.info(location);
	}

}
