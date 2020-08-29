/**
 * 
 */
package io.vilya.rpc.client.sequence;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class DefaultSequenceGenerator implements SequenceGenerator {

	private AtomicLong count = new AtomicLong();
	
	@Override
	public long generate() {
		return count.incrementAndGet();
	}

}
