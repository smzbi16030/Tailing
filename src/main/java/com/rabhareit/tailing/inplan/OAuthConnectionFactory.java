package com.rabhareit.tailing.inplan;

import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;

public class OAuthConnectionFactory<A> extends ConnectionFactory<A> {
    /**
     * Creates a new ConnectionFactory.
     *
     * @param providerId      the assigned, unique id of the provider this factory creates connections to (used when indexing this factory in a registry)
     * @param serviceProvider the model for the ServiceProvider used to conduct the connection authorization/refresh flow and obtain a native service API instance
     * @param apiAdapter      the adapter that maps common operations exposed by the ServiceProvider's API to the uniform {@link Connection} model
     */
    public OAuthConnectionFactory(String providerId, ServiceProvider<A> serviceProvider, ApiAdapter<A> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }

    @Override
    public Connection<A> createConnection(ConnectionData data) {
        return null;
    }
}
