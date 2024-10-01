package com.redhat.camel.training.service;

import com.redhat.camel.training.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.infinispan.Cache;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 * <pre>
 *  com.redhat.camel.training.service.CacheService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 01 Oct 2024 21:06
 */
@ApplicationScoped
@Named("myCacheService")
public class CacheService {

    private EmbeddedCacheManager cacheManager;

    private Cache<String, Employee> cache;

    public CacheService () {
        // initialize
        cacheManager = new DefaultCacheManager();

        // config
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.simpleCache(true);

        cache = cacheManager.administration()
                .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
                .getOrCreateCache("employee-cache", builder.build());
    }

    public void put (@Header("employee") Employee employee) {
        cache.put(employee.getId(), employee);
    }

    public Employee get (@Header("id") String id) {
        return cache.getOrDefault(id, new Employee());
    }
}
