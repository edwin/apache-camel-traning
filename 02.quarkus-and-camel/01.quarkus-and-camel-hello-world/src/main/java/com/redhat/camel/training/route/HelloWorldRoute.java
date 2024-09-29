package com.redhat.camel.training.route;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

/**
 * <pre>
 *  com.redhat.camel.training.route.HelloWorldRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 29 Sep 2024 10:44
 */
@ApplicationScoped
public class HelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/api")
                .get("/hello-world")
                .produces("application/json")
                .to("direct:hello-world");

        from("direct:hello-world")
                .routeId("hello-world-api")
                .log("calling getHelloWorld")
                .setBody(constant("{\"hello\":\"world\"}"));

    }
}
