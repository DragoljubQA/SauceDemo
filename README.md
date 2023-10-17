# SauceDemo

SauceDemo is a website for testing purposes where the main goal is to add items to cart and finish shopping

## Dependencies
Run on Windows 10 OS
IDE for this project is IntelliJ Idea Community Edition 2023.2.2
Browsers needed is Chrome as mandatory but also preferred are Firefox, Edge and Safari

## Instalation

Open terminal in IDE and git clone the repository

```
git clone https://github.com/DragoljubQA/SauceDemo.git
```
java version "21" 2023-09-19 LTS
Apache Maven 3.9.5

## Executing program
Run all tests from terminal with: 
```
mvn test
```
Run specific class from terminal with: 
```
mvn test -Dtest="LoginTest"
```
Run specific xml file from terminal with:
```
mvn clean test -DsuiteXmlFile="testng.xml"
```
## Framework Walkthorugh
Packages:
* Base - Contains classes used through the app
* Enums - Contains enum classes
* Helpers - Contains classes that could be useful for DDT
* Pages - Contains classes for each page
* Tests - Contains test classes

FIles:
* end-processes - Used in AfterClass to end all processes in the background
* pom.xml - Contains all dependencies used in the project (last updated: 10.10.2023.)
* TestData.xlsx - Excel file used to read some data for DDT as an alternative
* testng.xml - Basic xml file to run all test classes
* testngParallel.xml - File used to run parallel tests by classes
