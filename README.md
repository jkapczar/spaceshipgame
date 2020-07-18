# spaceshipgame

Goal
----
* Kill as many red circles before they reach the spaceship. 
* To kill red circles you can shoot green circles.

Usage
-----
```
mvn package
docker build --tag db:1.0 .
docker run -p "3309:3306" db:1.0
mvn exec:java
```

Example
___
