
![Logo](https://raw.githubusercontent.com/ranveer-kumar/graphql-examples/master/src/main/resources/1.png)




Backend API Service of the Spark CMS


## Tech Stack


**Server:** Java 11, Spring Boot 2.6.4, GraphQL, Tomcat

**Database:** MongoDB 5.x

**Client:** Angular

**Conatainer:** Docker

## Installation

On Terminal use following commands from project directory:

```bash
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


## Documentation

[Documentation](https://linktodocumentation)

To be updated



## API Reference

#### GraphQL QUERY UI
[http://localhost:8888/graphiql?path=/graphql](http://localhost:8888/graphiql?path=/graphql)

#### Save article

```http
   POST http://localhost:8888/graphql

```

| Parameter      | Type     | Description                         |
|:---------------| :------- |:------------------------------------|
| `articleInput` | `Object` | **Required**. `articleInput` object |

Sample payload to save the article:

```
mutation {
  saveArticle(
    articleInput: {
      domainId:1
      title: "title of the artilce"
      articleType: ARTICLE
      summary: "Here the summary will go"
      lastModifiedByUserName: "Ranveer"
      leadMedia: {
        mediaType: "image"
        images: { caption: "caption of the image", imageCredit: "ANI" }
        videos: { url: "https://youtu.be/OoV_YUswOIA", body: "body text of the video" }
      }
      metaData: {
        metaTitle: "meta title"
        authorEmail: "dummy@domain.com"
        url: "https://www.dummy.com/sample"
        canonicalUrl: "hello1"
      }
    }
  ) {
    id
    domainId
    title
    articleType
    summary
    createdDate
    lastModifiedByUserName
    lastPublishedDate

    leadMedia {
      mediaType
      images {
        caption
        imageCredit
      }
      videos {
        url
        body
      }
    }

    metaData {
      metaTitle
      authorEmail
      url
      canonicalUrl
    }
  }
}

```


#### Update article

```http
  POST http://localhost:8888/graphql
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. `id` |
| `clientId` | `Int` | **Required**. `clientId` |

Sample payload to update the article:

```
mutation {
  updateArticle(
    articleInput: {
      id:47651
      clientId: 1
      title: "title-update title-web soc update-final-22"
      articleType: ARTICLE
      summary: "summary-web sco-1"
      lastModifiedByUserName: "Ranveer-uupdate"
      leadMedia: {
        mediaType: "video"
        images: { caption: "caption iage-u", imageCredit: "ANI-u" }
        videos: { url: "https://youtu.be/OoV_YUswOIA", body: "body text of the video" }
      }
      metaData: {
        metaTitle: "meta title from metaData1-u"
        authorEmail: "abc@yahoo.com1-u"
        url: "https://www.google.com11-u"
        canonicalUrl: "hello1-u"
      }
    }
  ) {
    id
    domainId
    title
    articleType
    summary
    createdDate
    lastModifiedByUserName
    lastPublishedDate

    leadMedia {
      mediaType
      images {
        caption
        imageCredit
      }
      videos {
        url
        body
      }
    }

    metaData {
      metaTitle
      authorEmail
      url
      canonicalUrl
    }
  }
}


```


#### Get article by ID

```http
  POST http://localhost:8888/graphql
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `articleID` | `Long` | **Required**. `articleId` |

Sample payload to get the article by ID:

```
query {
  getArticleById(articleId:82476) {
    id
    domainId
    title
    summary
    createdBy
    articleType
    lastModifiedByUserName
    lastPublishedDate

    metaData {
      metaTitle
      authorEmail
      canonicalUrl
      url
    }
    leadMedia {
      mediaType
      images {
        caption
        imageCredit
      }
      videos {
        url
        body
      }
    }

  }
}


```

#### Get All Articles

```http
  POST http://localhost:8888/graphql
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `page` | `Int` | **Optional**. `page` default is: `0` |
| `size` | `Int` | **Optional**. `size` default is: `0` |
| `page` | `String` | **Optional**. `sortBy` default is: `createdDate` (field) |
| `sortDirection` | `String` | **Optional**. `sortDirection` default is: `ASC` |


Sample payload to get the allArticle with pagination:

```
query{
  allArticles(page:0, size:10,sortBy: "createdDate",  sortDirection:"ASC"){
    id
    title
    leadMedia{
      mediaType
      images{
        caption
        name
        anchorTag
      }
    }
    metaData{
      metaTitle
      url
    }
  }
}


```


#### Delete Article by ID

```http
  POST http://localhost:8888/graphql
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `articleId` | `Long` | **Required**. `articleId` |

Sample payload to delete the article by ID

```
mutation{
  deleteArticle(articleId: 478) 
}


```


#### FileUpload

```http
  POST http://localhost:8888/graphql
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `articleId` | `Long` | **Required**. `articleId` |

Sample payload to uploadFile

```
curl --location --request POST 'http://localhost:8888/graphql' \
--form 'operations="{\"query\": \"mutation { uploadFile }\", \"variables\":{}}"' \
--form 'file=@"/Users/ranveerkumar/Desktop/Screenshot 2022-02-25 at 9.20.48 AM.png"' \
--form 'file=@"/Users/ranveerkumar/Desktop/Screenshot 2022-02-17 at 12.08.32 PM.png"' \
--form 'file=@"/Users/ranveerkumar/Desktop/Screenshot 2022-02-17 at 11.57.18 AM.png"'

```

