# PDF Library for the Robot Framework (Java)
Introduction
------------
A Robot Framework library for verifying PDFs.

* More information about this library can be found in the
  [Keyword Documentation]().
* For keyword completion you can download the
  [Library Specs]().

Usage
-----
Use the [robotframework-maven-plugin](http://robotframework.org/MavenPlugin/) and add the library to your pom.xml:

    <dependency>
        <groupId>com.github.justinclagg</groupId>
        <artifactId>robotframework-pdflibrary</artifactId>
        <version>0.0.1</version>
    </dependency>
        
Import the library in Robot:

|                    |                                 |
| ----------------   | ------------------------------- | 
| *** Settings ***   |                                 |                 
| Library            | PDFLibrary                      |   
   
