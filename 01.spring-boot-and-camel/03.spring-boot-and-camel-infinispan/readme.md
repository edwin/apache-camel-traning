# Remote Infinispan, Spring Boot, and Apache Camel 4

## Java Version
Java version being used is Java 17

## Infinispan Version
Using Infinispan version 14.0.31.Final

## How to Build
```
$ mvn clean package -s settings.xml
```

## Infinispan Configuration
```json
{
  "replicated-cache": {
    "mode": "SYNC",
    "statistics": true,
    "encoding": {
      "media-type": "text/plain"
    }
  }
}
```


## How to Test
```
$  curl -X POST -kv http://localhost:8080/api/add-cache -d '{"firstname":"cahyadi","lastname":"cucu","gender":"F","birthdate":"2024-08-20","id":3}' -H "Content-Type: application/json"
*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> POST /api/add-cache HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 86
>
< HTTP/1.1 204 No Content
<
* Connection #0 to host localhost left intact
```

```
$  curl -kv http://localhost:8080/api/get-cache/2
*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> GET /api/get-cache/2 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json
<
{
  "gender" : "F",
  "birthdate" : 1724112000000,
  "id" : 2,
  "firstname" : "lele",
  "lastname" : "dumbo"
* Connection #0 to host localhost left intact
}                                      
```
