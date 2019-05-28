/**
 * 
 */
package io.vilya.rpc.demo;

import java.lang.reflect.Method;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class ProviderProxy {

	public static <T> T newProxy(Class<T> type) {
		return Reflection.newProxy(type, new AbstractInvocationHandler() {
			@Override
			protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
				
				return null;
			}
		});
	}

}
