/**
 * 
 */
package io.vilya.rpc.demo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author erkea <erkea@vilya.io>
 *
 */
public class DefaultSequenceGenerator implements SequenceGenerator {

	private AtomicLong count = new AtomicLong();
	
	@Override
	public long generate() {
		return count.incrementAndGet();
	}

}
