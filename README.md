# CreditApp Rest API
Bu kredi başvuru rest API uygulaması Spring Boot , MongoDB ve PrimeFaces kullanılarak geliştirilmiştir. Uygulama docker container lar üzerinde çalışmaktadır.


## Requirements
- Java 8
- Maven 3.6.3
- Docker 1.2.13
- Docker-compose 1.26.2

## Build ve Çalıştırma
Build almak ve docker imajı oluşturmak için aşağıdaki komut çalıştırılmalıdır.
 
```mvn clean package```
 
Aşağıdaki komut ile uygulama çalıştırabilir.

```docker-compose up```

## FrontEnd Erişimi

Aşağıdaki url ile Uygulama ekranına ulaşılabilir.

http://localhost:8080/creditservice/index.xhtml

## Swagger Api Documentation

Aşağıdaki url ile Swagger Apı dokümantasyonuna ulaşılabilir.

http://localhost:8080/creditservice/swagger-ui.html

## Author
Merve Tuncel Kaya

