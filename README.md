# UselessFactChecker
UselessFactChecker

The project is built on Quarkus and Java. 
Please read the documentation here : https://quarkus.io/guides/

# To run the project please clone the repo and use the following steps

## Bash
```
git clone [git@github.com:theNinthElement/UselessFactChecker.git](https://github.com/theNinthElement/UselessFactChecker.git)

cd fact-service
```

## Packaging and running the application

Packaging the application 
```
./mvn clean package
```

To run the application 
```
./mvnw quarkus:dev
```

To run only the test cases 
```
./mvnw quarkus:test
```

## Here are the example API calls that can be used 
```
POST : http://localhost:8080/facts
GET : http://localhost:8080/facts
GET : http://localhost:8080/facts/710787075 (change the with the shortURL output from the post)
GET : http://localhost:8080/facts/710787075/redirect (change the with the shortURL output from the post)
GET : http://localhost:8080/admin/statistics

```
