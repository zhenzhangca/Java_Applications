# Java Grep App

## Introduction
The Java Grep App is a text retrieval application. Users can search for a text pattern recursively in a given directory, and output matched lines to a file by passing certain arguments.

## Usage
The app takes three arguments:
```
    regex -- a special text string for describing a search pattern (more powerful than wildcard)
    rootPath -- the  root directory path
    outFile -- the output file name
```
The logic is similar to the command line `egrep -r {regex} {rootPath} > {outFile}` in Linux:  
For example:  
When users pass `".*data.*", "~/dev/jrvs/bootcamp/linux_sql" "/tmp/grep.out"` to the main function, the app will search all files in the `"~/dev/jrvs/bootcamp/linux_sql"` directory, and output lines which contain "data" keyword to the output file `"/tmp/grep.out"`.

## Design and Implementation
- Pseudo code:  
```
matchedLines = []  
for file in listFiles(rootDir)  
    for line in readLines(file)  
        if containsPattern(line)  
           matchedLines.add(line)  
writeToFile(matchedLines) 
```  

- Diagrams

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/grepApp.jpg)   

## Enhancements and Issues
- When the amount of files is very large, read lines would affect the performance of memory. Should add a file filter.
- Can not support multiple regex.
- Add the statistical function for the matched lines.

# Java JDBC App

## Introduction
The JDBC App is a data-processing application. This app implements CRUD(create, read, update and delete) functionality with JDBC(Java Database Connectivity) APIs, which allows users to create, read, update and delete data in PostgreSQL RDBMS.

## Design and Implementation

This project was created based on the DAO(Data Access Object) design pattern which allows us to isolate the application/business layer from the persistence layer (PostgreSQL database).  Create an abstract class DataAccessObject which is extended by two implementation classes `CustomerDAO` and `OrderDAO`. These two classes are used to transfer DTOs (Data Transfer Object) Customer and Order to PostgreSQL database to perform CRUD operations.  
- Important libraries: 
```
   java.sql.DriverManager;  
   java.sql.Connection;  
   java.sql.PreparedStatement;  
   java.sql.ResultSet;  
```
- Java Code diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/JDBCApp01.jpg)

- ER diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/JDBCApp02.png)  

## Enhancements and Issues
- SQL statements are hard-code, should write a class for generating SQL statements by passing certain arguments.
- Exist duplicated code on getting Connection object, should write a util class for getting Connection Object in DAO layer.
- Explore transactions.


# Twitter CLI App

## Introduction

The Twitter CLI App is a tweet manipulation application. Through passing certain arguments, users can create, read and delete tweets(CRD). In this project, I use the Spring and Spring Boot framework to manage different components' dependencies, and implement the business logic through three approaches--Spring Bean Approach, Spring Annotation Approach, and Spring Boot Approach.

## Usage
- Create a tweet:
```
  - USAGE: TwitterCLIApp "post" "tweet_text" "latitude:longitude" 
  - Description: Create a tweet with a geotag and output the created tweet object(simplifeid version) in JSON  format.
  - Arguments: "tweet_text"(cannot exceed 140 UTF-8 encoded characters), "latitude:longitude"(Geo location).
```
- Read/Show a tweet by ID
```
  - USAGE: TwitterCLIApp "show" "tweet_id" "[field1,fields2]" 
        e.g.  
           TwitterCLIApp "show" "1097607853932564480" "id,text,retweet_count" 
           Response:  
                    {  
                      "id": 1097607853932564480,  
                      "text": "test with loc223",  
                      "retweet_count": 0  
                    } 
  - Description: Lookup a tweet by ID and print the tweet object in JSON format. Show all fields in JSON document if `"[field1,fields2]"` is empty. Otherwise, only show user specified fields in the JSON document.  
  - Arguments: "tweet_id"(Tweet ID) which is same as id_str in the tweet object, "[field1,fields2]" (A comma-separated list of top-level fields from the tweet object which is similar to SELECT clause in SQL).
```
-  Delete a tweet by tweet ID  
```
  - USAGE: TwitterCLIApp "delete" "tweet_ids" 
  - Description: Delete a list of tweets by id, and output deleted tweet id, then print deleted tweet object.  
  - Arguments: "tweet_ids"(A comma-separated list of tweets).
```
## Design and Implementation

- Diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/TwitterCLIApp.jpg)

- Component design
```
  - HttpHelper: Making HTTP requests (GET/PUT/DELETE) and handle auth  
  - Dao: Data Access Object which handles tweet object (Dao depends on HttpHelper)  
  - Service: Business logic. In other words, it depends on Dao, and manipulate twitter object according to application requirements (e.g. select certain fields when showing tweet object)  
  - Runner: Parse user CLI inputs and then calls the corresponding service methods  
  - Main: Create above components and start applications
```
## Enhancements and Issues
- Can not update existed tweets.
- Can not create a new tweet in image, video, etc. only text format.
- Can not create tweets with duplicated status.





