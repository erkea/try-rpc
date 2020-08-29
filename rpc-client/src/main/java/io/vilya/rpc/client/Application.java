/**
 * 
 */
package io.vilya.rpc.client;

import io.vilya.rpc.demo.consumer.TestService;
import io.vilya.rpc.demo.consumer.TestServiceImpl;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class Application {

	public static void main(String[] args) {
		TestService testService = new TestServiceImpl();
		testService.test();
	}
	
}
