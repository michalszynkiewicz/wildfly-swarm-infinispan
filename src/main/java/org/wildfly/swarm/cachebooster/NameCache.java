package org.wildfly.swarm.cachebooster;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

/**
 * mstodo: Header
 *
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 1/31/18
 */
@ApplicationScoped
public class NameCache {
    public static final String CACHE_NAME = "nameCache";

    private static final String KEY = "name";

    private Integer timeToLive = 5;

    @Resource(lookup = "java:jboss/infinispan/container/server")
    private EmbeddedCacheManager cacheManager;

    private Cache<String, String> cache;

    @PostConstruct
    public void setUp() {
        cache = cacheManager.getCache(CACHE_NAME);
    }

    public void put(String name) {
        cache.put(KEY, name, timeToLive, TimeUnit.SECONDS);
    }

    public String get() {
        return cache.get(KEY);
    }

    public boolean hasValue() {
        return cache.containsKey(KEY);
    }

    public void remove() {
        cache.remove(KEY);
    }
}
