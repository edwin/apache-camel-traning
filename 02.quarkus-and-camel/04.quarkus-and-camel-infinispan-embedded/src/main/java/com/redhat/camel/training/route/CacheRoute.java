package com.redhat.camel.training.route;

import com.redhat.camel.training.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * <pre>
 *  com.redhat.camel.training.route.CacheRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 01 Oct 2024 19:53
 */
@ApplicationScoped
public class CacheRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/api")
                .get("/get-cache/{id}")
                .produces("application/json")
                .to("direct:get-cache")
                .post("/add-cache")
                .to("direct:add-cache");

        from("direct:get-cache")
                .routeId("get-cache-api")
                .log("calling get-cache ${header.id}")
                // process the response
                .process(exchange -> {
                    // get the employee-id from path variable
                    String id = (String) exchange.getIn().getHeader("id");
                    exchange.getOut().setHeader("id", id);

                    exchange.getIn().getHeaders().clear();
                })
                .to("bean:myCacheService?method=get")
                .log("sending response get-cache-api to frontend as json")
                .marshal()
                .json(JsonLibrary.Jackson);;

        from("direct:add-cache")
                .routeId("add-cache-api")
                .log("calling add-cache")
                .unmarshal()
                    .json(JsonLibrary.Jackson, Employee.class)
                // process the request
                .process(exchange -> {
                    exchange.getOut().setHeader("employee", exchange.getIn().getBody());
                    exchange.getIn().getHeaders().clear();
                })
                .to("bean:myCacheService?method=put")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
    }
}