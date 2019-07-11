# Java Grep App

## Introduction
The Java Grep App is a text retrieval application. Through passing certain arguments, users can search for a text pattern recursively in a given directory, and output matched lines to a file.

## Usage
The app takes three arguments:

    regex -- a special text string for describing a search pattern (more powerful than wildcard)
    rootPath -- the  root directory path
    outFile -- the output file name
The logic is similar to the command line in Linux:
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`egrep -r {regex} {rootPath} > {outFile}`
For example:
When user passes ".*data.*", "~/dev/jrvs/bootcamp/linux_sql" and "/tmp/grep.out" to the main function, the app will 
search all files in "~/dev/jrvs/bootcamp/linux_sql" directory, and output lines which contain "data" keyword to the output file "/tmp/grep.out".

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

You can save any file of the workspace to **Google Drive**, **Dropbox** or **GitHub** by opening the **Synchronize** sub-menu and clicking **Save on**. Even if a file in the workspace is already synced, you can save it to another location. StackEdit can sync one file with multiple locations and accounts.

## Design and Implementation

- Diagram

![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/TwitterCLIApp.jpg)

