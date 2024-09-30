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

## How to Build
```
$ mvn clean package -s settings.xml
```

## How to Test
```
$ curl -kv http://localhost:8080/api/employee/1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /api/employee/1 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 30 Sep 2024 02:40:10 GMT
<
* Connection #0 to host localhost left intact
[{"gender":"M","birthdate":1724814030000,"id":1,"firstname":"random","lastname":"name"}]  
```

```
$ curl -X POST -kv http://localhost:8080/api/employee -d '{"firstname":"gabriel","lastname":"batistuta","gender":"M","birthdate":"2024-08-20"}' -H "Content-Type: application/json"
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> POST /api/employee HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 84
>
< HTTP/1.1 201
< accept: */*
< user-agent: curl/8.4.0
< Content-Type: application/json
< Content-Length: 0
< Date: Mon, 30 Sep 2024 04:34:40 GMT
<
* Connection #0 to host localhost left intact
```