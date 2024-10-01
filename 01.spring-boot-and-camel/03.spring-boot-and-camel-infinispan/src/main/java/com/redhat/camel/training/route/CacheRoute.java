package com.redhat.camel.training.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.redhat.camel.training.model.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  com.redhat.camel.training.route.CacheRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 01 Oct 2024 10:02
 */
@Component
public class CacheRoute extends RouteBuilder {

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public void configure() throws Exception {
        rest()
                .get("/get-cache/{id}")
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .to("direct:get-cache")
                .post("/add-cache")
                    .to("direct:add-cache");

        from("direct:get-cache")
                .routeId("get-cache-api")
                    .log("calling get-cache ${header.id}")
                // process the request
                .process(exchange -> {
                    // get the employee-id from path variable
                    String id = (String) exchange.getIn().getHeader("id");

                    exchange.getOut().setHeader(InfinispanConstants.OPERATION, constant(InfinispanOperation.GET));
                    exchange.getOut().setHeader(InfinispanConstants.KEY, id);

                    exchange.getIn().getHeaders().clear();
                })
                .to("infinispanRemoteComponent://employee-cache")
                    .log("sending response get-cache-api to frontend as json");

        from("direct:add-cache")
                .routeId("add-cache-api")
                    .log("calling add-cache")
                .unmarshal()
                    .json(JsonLibrary.Jackson, Employee.class)
                // process the request
                .process(exchange -> {
                    // process the employee object
                    Employee employee = (Employee) exchange.getIn().getBody();

                    exchange.getOut().setHeader(InfinispanConstants.OPERATION, constant(InfinispanOperation.PUT));
                    exchange.getOut().setHeader(InfinispanConstants.KEY, employee.getId());
                    exchange.getOut().setHeader(InfinispanConstants.VALUE, ow.writeValueAsString(employee));

                    exchange.getIn().getHeaders().clear();
                })
                .to("infinispanRemoteComponent://employee-cache");
    }
}
