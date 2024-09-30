package com.redhat.camel.training.route;

import com.redhat.camel.training.model.Employee;
import org.apache.camel.Exchange;
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
                    .to("direct:query-employee")
                .post("/employee")
                    .to("direct:insert-employee");

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

        from("direct:insert-employee")
                .routeId("insert-mysql-table-employee")
                .log("calling insert-mysql-table-employee")
                .unmarshal()
                    .json(JsonLibrary.Jackson, Employee.class)
                .to("sql:INSERT INTO t_employee (gender, birthdate, id, firstname, lastname) " +
                        "VALUES (:#${body.gender}, :#${body.birthdate}, null, :#${body.firstname}, :#${body.lastname})")
                .log("sending response insert-mysql-table-employee to frontend")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
    }
}
