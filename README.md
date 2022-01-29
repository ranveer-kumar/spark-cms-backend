# Getting Started

#### General setup


On Terminal use following commands from project directory:

```
mkdir -p ~/dev/docker/mongodb5  or mkdir -p /home/{uses_home_dir}/dev/docker/mongodb5
git clone https://github.com/ranveer-kumar/spark-cms-backend.git
cd spark-cms-backend
git checkout development
git pull
docker-compose up
mvn clean package
mvn spring-boot:run
or
java -jar ./target/spark-cms-0.0.1-SANPSHOT.jar
```

#### Swagger
[Swagger API URL](http://localhost:8888/swagger-ui/index.html)
#### API


[POST Aritcle API](http://localhost:8888/swagger-ui/index.html#/article-controller/saveArticle)

[GET Article API](http://localhost:8888/swagger-ui/index.html#/article-controller/getArticle)
