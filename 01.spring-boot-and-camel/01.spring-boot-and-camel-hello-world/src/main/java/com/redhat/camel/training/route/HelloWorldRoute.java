package com.redhat.camel.training.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  com.redhat.camel.training.route.HelloWorldRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 Sep 2024 19:48
 */
@Component
public class HelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest()
                .get("/hello-world")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .to("direct:hello-world");

        from("direct:hello-world")
                .routeId("hello-world-api")
                .log("calling getHelloWorld")
                .setBody(constant("{\"hello\":\"world\"}"));
    }
}
