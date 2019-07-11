# Java Grep App

## Introduction
The Java Grep App is a text retrieval application. Through passing certain arguments, users can search for a text pattern recursively in a given directory, and output matched lines to a file.

## Usage
The app takes three arguments:

    regex -- a special text string for describing a search pattern (more powerful than wildcard)
    rootPath -- the  root directory path
    outFile -- the output file name
The logic is similar to the `egrep -r {regex} {rootPath} > {outFile}`command line in Linux:  
For example:
When user passes `".*data.*", "~/dev/jrvs/bootcamp/linux_sql" "/tmp/grep.out"` to the main function, the app will 
search all files in `"~/dev/jrvs/bootcamp/linux_sql"` directory, and output lines which contain "data" keyword to the output file `"/tmp/grep.out"`.

## Design and Implementation
- Pseudo code:  
matchedLines = []  
for file in listFiles(rootDir)  
&#8195;&#8195;for line in readLines(file)  
&#8195;&#8195;&#8195;&#8195;if containsPattern(line)  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;matchedLines.add(line)  
writeToFile(matchedLines)  
- Diagrams

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/grepApp.jpg)

# Java JDBC App

## Introduction
The JDBC App is a data manipulation application. Users can implement create, read, update and delete data (CRUD) from relational databases such as PostgresSQL using Java Database Connectivity (JDBC) in applications programmed with Java.

## Design and Implementation

This project was created based on the DAO design pattern.  I created two DTOs (Data Transfer Object) such as Customer and Order, then through the manipulation of DTO between DAO and relational database to implement data CRUD.
Important libraries:
&#8195;&#8195;java.sql.DriverManager;  
&#8195;&#8195;java.sql.Connection;
&#8195;&#8195;java.sql.PreparedStatement;
&#8195;&#8195;java.sql.ResultSet;
- Java Code diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/JDBCApp01.jpg)

- ER diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/JDBCApp02.png)

# Twitter CLI App

## Introduction

Twitter CLI App is a tweet manipulation application. Through passing certain arguments, users can create, read and delete tweets(CRD).

## Usage
- Create a tweet on your timeline:
 
&#8195;&#8195;- USAGE: TwitterCLIApp post "tweet_text" "latitude:longitude"  
&#8195;&#8195;- Description: Create a tweet with a geotag and output the created tweet object(simplifeid version) in JSON  format.
&#8195;&#8195;- Arguments: tweet_text(cannot exceed 140 UTF-8 encoded characters), latitude:longitude(Geo location).

- Read/Show a tweet by ID

&#8195;&#8195;- USAGE: TwitterCLIApp show tweet_id [field1,fields2]  
&#8195;&#8195;&#8195;&#8195;e.g.  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;TwitterCLI show 1097607853932564480 "id,text,retweet_count"  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;Response:  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;{  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;   "id": 1097607853932564480,  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;    "text": "test with loc223",  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;    "retweet_count": 0  
&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;}   
&#8195;&#8195;- Description: Lookup a tweet by ID and print the tweet object in JSON format. Show all fields in JSON document if [field1,fields2] is empty. Otherwise, only show user specified [fields] in the JSON document.  
&#8195;&#8195;- Arguments: tweet_id(Tweet ID) which is same as id_str in the tweet object, [field1,fields2] (A comma-separated list of top-level fields from the tweet object which is similar to SELECT clause in SQL).

-  Delete a tweet by tweet ID  

&#8195;&#8195;- USAGE: TwitterCLIApp delete tweet_ids  
&#8195;&#8195;- Description: Delete a list of tweets by id, and output deleted tweet id, then print deleted tweet object.  
&#8195;&#8195;- Arguments: tweet_ids(A comma-separated list of tweets).
 
## Design and Implementation

- Diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/TwitterCLIApp.jpg)

- Component design

&#8195;&#8195;- HttpHelper: Making HTTP requests (GET/PUT/DELETE) and handle auth  
&#8195;&#8195;- Dao: Data Access Object which handles tweet object (Dao depends on HttpHelper)  
&#8195;&#8195;- Service: Business logic. In other words, it depends on Dao, and manipulate twitter object according to application requirements (e.g. select certain fields when showing tweet object)  
&#8195;&#8195;- Runner: Parse user CLI inputs and then calls the corresponding service methods  
&#8195;&#8195;- Main: Create above components and start applications


