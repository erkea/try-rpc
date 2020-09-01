/**
 * 
 */
package io.vilya.rpc.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhukuanxin <cafedada@vilya.io>
 *
 */
public class ProviderRegistry {

    private static final Logger log = LoggerFactory.getLogger(ProviderRegistry.class);
    
	private Map<String, Object> providers = new HashMap<>();
	
	public Object getObject(String type) {
		return providers.get(type);
	}
	
	public void register(@Nonnull String type, @Nonnull Object instance) {
	    Objects.requireNonNull(type, "type");
	    Objects.requireNonNull(instance, "instance");
	    providers.compute(type, (k, v) -> {
	        if (v != null) {
	            log.debug("value of {} overrided.", type);
	        }
	        return instance;
	    });
	}
	
}
