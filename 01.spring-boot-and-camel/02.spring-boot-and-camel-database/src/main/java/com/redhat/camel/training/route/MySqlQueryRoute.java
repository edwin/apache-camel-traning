package com.redhat.camel.training.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  com.redhat.camel.training.route.MySqlQueryRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 30 Sep 2024 9:08
 */
@Component
public class MySqlQueryRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest()
                .get("/employee/{id}")
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .to("direct:query-employee");

        from("direct:query-employee")
                .routeId("query-mysql-table-employee")
                .log("calling query-mysql-table-employee")
                .to("sql:select * from t_employee where id = :#${header.id}?" +
                        "outputClass=com.redhat.camel.training.model.Employee")
                // process the response
                .process(exchange -> {
                    exchange.getIn().getHeaders().clear();
                    exchange.getOut().setBody(exchange.getIn().getBody());
                })
                .log("sending response query-mysql-table-employee to frontend as json")
                .marshal()
                .json(JsonLibrary.Jackson);
    }
}
