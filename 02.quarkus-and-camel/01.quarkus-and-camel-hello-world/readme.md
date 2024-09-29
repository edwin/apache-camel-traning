# Hello World using Spring Boot and Apache Camel 4

## Camel Version
We are using `Apache Camel` version `4.4.0`
```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.redhat.camel.springboot.platform</groupId>
                <artifactId>camel-spring-boot-bom</artifactId>
                <version>4.4.0.redhat-00014</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

## Java Version
Java version being used is Java 17

## How to Build
```
$ mvn clean package -s settings.xml
```

## How to Test
```
$ curl -kv http://localhost:8080/api/hello-world
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /api/hello-world HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200
< accept: */*
< user-agent: curl/8.4.0
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 14 Jul 2024 05:34:37 GMT
<
* Connection #0 to host localhost left intact

{"hello":"world"}         
```