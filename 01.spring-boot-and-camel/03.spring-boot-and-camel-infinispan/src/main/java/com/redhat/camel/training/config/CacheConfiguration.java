package com.redhat.camel.training.config;

import org.apache.camel.component.infinispan.remote.InfinispanRemoteComponent;
import org.apache.camel.component.infinispan.remote.InfinispanRemoteConfiguration;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  com.redhat.camel.training.config.CacheConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 01 Oct 2024 11:20
 */
@Configuration
public class CacheConfiguration {

    @Value("${infinispan.host}")
    private String host;

    @Value("${infinispan.port}")
    private Integer port;

    @Value("${infinispan.username}")
    private String username;

    @Value("${infinispan.password}")
    private String password;

    @Value("${infinispan.server-name}")
    private String serverName;

    protected ConfigurationBuilder getConfiguration() {
        ConfigurationBuilder clientBuilder = new ConfigurationBuilder();
        clientBuilder.forceReturnValues(true);
        clientBuilder.addServer().host(host).port(port);

        // authentication
        clientBuilder.security().authentication().username(username).password(password)
                .serverName(serverName).saslMechanism("DIGEST-MD5").realm("default");
        return clientBuilder;
    }

    @Bean(name = "infinispanRemoteComponent")
    public InfinispanRemoteComponent infinispanRemoteComponent() {
        InfinispanRemoteConfiguration infinispanRemoteConfiguration = new InfinispanRemoteConfiguration();
        infinispanRemoteConfiguration.setHosts(host + ":" + port);
        infinispanRemoteConfiguration.setUsername(username);
        infinispanRemoteConfiguration.setPassword(password);

        RemoteCacheManager cacheContainer = new RemoteCacheManager(getConfiguration().build());
        infinispanRemoteConfiguration.setCacheContainer(cacheContainer);

        InfinispanRemoteComponent component = new InfinispanRemoteComponent();
        component.setConfiguration(infinispanRemoteConfiguration);
        return component;
    }
}