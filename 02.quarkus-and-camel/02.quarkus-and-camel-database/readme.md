# MySql, Spring Boot and Apache Camel 4

## Table
```sql
create table t_employee
(
    gender    varchar(1)   null,
    birthdate datetime(6)  null,
    id        bigint auto_increment
        primary key,
    firstname varchar(100) null,
    lastname  varchar(100) null
);

INSERT INTO test_db.t_employee (gender, birthdate, id, firstname, lastname) VALUES ('M', '2024-08-30 05:05:27.000000', 3, 'nama', 'satu');
INSERT INTO test_db.t_employee (gender, birthdate, id, firstname, lastname) VALUES ('F', '2024-08-29 15:06:32.000000', 2, 'some', 'random');
INSERT INTO test_db.t_employee (gender, birthdate, id, firstname, lastname) VALUES ('M', '2024-08-28 10:00:30.000000', 1, 'random', 'name');
```

## Java Version
Java version being used is Java 17

## How to Run
```
$ mvn quarkus:dev -s settings.xml
```

## How to Test
```
$ curl -kv http://localhost:8080/api/employee/2
*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> GET /api/employee/2 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json
<
* Connection #0 to host localhost left intact
[{"gender":"F","birthdate":1724918792000,"id":2,"firstname":"some","lastname":"random"}]
```

```
$ curl -X POST -kv http://localhost:8080/api/employee -d '{"firstname":"miss bloody","lastname":"valentine","gender":"F","birthdate":"2011-12-21"}' -H "Content-Type: application/json"
*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> POST /api/employee HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 88
>
< HTTP/1.1 201 Created
< Accept: */*
< User-Agent: curl/8.4.0
< transfer-encoding: chunked
< Content-Type: application/json
<
* Connection #0 to host localhost left intact
```