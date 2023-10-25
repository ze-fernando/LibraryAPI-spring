## Api

### Routes

* /book/find
* /book/find/{id}
* /book/save{type}
* /book/del/{id}
* /book/rent/{id}
* /book/return/{id}

####

* /publisher/find
* /publisher/find/{id}
* /publisher/save
* /publisher/del/{id}



### Json

*For rent a book do you sent*

```yml
{
    "type": "CLIENT"
}
```

*and the Api return*
```yaml
{
    "message": "Book rented",
    "rented": dateRented,
    "term": dateReturn
}
```

*For save a book*
```yml
{
    "name": "Código Limpo",
    "author": "Robert Cecil Martin",
    "year": 2008,
    "status": "AVAILABLE",
    "publisher": {
        "id": 8,
        "name": "Alta Books"
    }   
}
```

*For save a publisher*
```yml
{
    "name":"Alta Books"
}
```

***The other routes do not require json***


### Tech

- Docker
- Java 21
- Spring
- Lombok
- MySQL
- Drivers for MySQL


  ### Run
  **Required**
  - Git
  - Jdk21
  - Docker
  - Maven
 
  `git clone https://github.com/ze-fernando/LibraryAPI-spring`
  
  `cd LibraryAPI-spring`
  
  `docker-compose up`
  
  `mvn spring-boot:run`

  >By ze-fernando
