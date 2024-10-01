package com.redhat.camel.training.route;

import com.redhat.camel.training.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * <pre>
 *  com.redhat.camel.training.route.MySqlQueryRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 01 Oct 2024 8:56
 */
@ApplicationScoped
public class MySqlQueryRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/api")
                .get("/employee/{id}")
                    .produces("application/json")
                    .to("direct:query-employee")
                .post("/employee")
                    .to("direct:insert-employee");

        from("direct:query-employee")
                .routeId("query-mysql-table-employee")
                .log("calling query-mysql-table-employee")
                .to("sql:select * from t_employee where id = :#${header.id}?" +
                        "outputClass=com.redhat.camel.training.model.Employee")
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
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .setBody(simple(null));

    }
}
