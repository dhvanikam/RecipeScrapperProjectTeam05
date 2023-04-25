# Recipe Scrapper Project
## Table of contents
* [General info](#general-info)
* [Tools and Technologies](#tools-and-technologies)
* [FrameWork](#framework)
* [Running Project](#running-project)
* [Reporting](#reporting)

## General info
* Introduction : Scraper project 

## Tools and Technologies
Project is created with:
* Maven - Dependency management
* Java
* Selenium Webdriver
* TestNG - Unit framework
* log4j - Logging

## Framework
```mermaid
flowchart TD
    src-->test
    test-->driverFactory
    test-->tests
    test-->utilities
    src-->resources
    resources-->config
    resources-->InputExcelData
    resources-->OutputRecipeExcelData
    resources-->log4j
```
## Running Project
To run this project, 
Open terminal (MAC OS) or command prompt / power shell (for windows OS) and navigate to the project directory type mvn clean test command to run features
```
$ cd <Project Directory>

$ mvn clean test
```

## Reporting
Allure Report: 

Report will be generated into temp folder. Web server with results will start appearing in your default browser. 

```
$ cd <Project Directory>

$ allure serve allure-results
```

HTML Report: 

Report will be generated tо directory: test-output/index.html


