/**
 * 
 */
package io.vilya.rpc.client;

import java.lang.reflect.Method;

import io.vilya.rpc.common.CallRequest;

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
				return Context.request(new CallRequest(1l, type.getName(), method.getName(), args));
			}
		});
	}

}
