# Java Grep App
## Introduction
The Java Grep App is a tex retrieval application. Through passing certain arguments, users can search for a text pattern
recursively in a given directory, and output matched lines to a file.
## Usage
The app takes three arguments:
    regex -- a special text string for describing a search pattern (more powerful than wildcard)
    rootPath -- the  root directory path
    outFile -- the output file name
The logic is similar to the command line "egrep -r {regex} {rootPath} > {outFile}" in linux.
For example:
When user passes ".*data.*", "~/dev/jrvs/bootcamp/linux_sql" and "/tmp/grep.out" to the main function, the app will 
search all files in "~/dev/jrvs/bootcamp/linux_sql" directory, and output lines contain "data" keyword to the output 
file "/tmp/grep.out".
## Design and Implementation
- Pseudo code:
matchedLines = []
for file in listFiles(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
- Diagrams
![image](https://github.com/zhenzhangca/Linux_Usage_Agent/blob/master/img-folder/grepApp.jpg)

# Java JDBC App
## Introduction
## Enhancements and Issues

# Twitter CLI App
## Introduction
The Twitter CLI App is

## Usage

## Enhancements and Issues