# introsde-2016-assignment-1

Introduction to service design and engineering Assignment 01: Reading/Writing objects to and from XML and JSON
Name: Getachew De. Abebe    ID: 174359

The code is organized as the following: There are four packages: healthProfile, JaxbClass, model and 

People.generated. The first package contain HealthProfileReader Java class. The second one have all the classes 

that are doing the marshalling and unmarshalling tasks. Health profile and person class are in the model 

packages. In the last one i have people and ObjectFactory classes. Besides the packages, there are xml, xml 

schema and json files.

In order to do run tasks number 1 to 3, first i have created people.xml file manually.This file consists the 

profile of Arsenal football club players. And then, i created a package called model and put two java classes

(health profile and person) in this package. There is a HealthProfileReader java class under healthProfile 

package which implements tasks 1-3. For tasks number 4 to 6 (marshaling and un-marshaling),I have created three 

persons using java object and then marshaling to xml file by JAXBMarshaller class and put in the same directory 

with the folders as PersonsMarsh.xml. For the un-marshaling i have changed the PersonMarsh.xml to 

PersonsMarsh.xsd for validation and did the un-marshal by JAXBUNMarshaller class. Finally, i created the 

PersonsMarsh.json file by using JaxbJson class from java objects. All the marshaling and un-marshaling tasks 

corresponds to java classes which are found in jaxbClass, people.generated and model packages.


To execute the code, first go to my public repository( introsde-2016-assignment-1) click clone or download the 

repository to your local machine. Second, Open your shell and change the current directory to the downloaded assignment 

folder.Then finally, write ant compile and hit enter. Once it finishes downlading and compiling , write ant 

execute.evaluation and hit enter again. It will displays all the results.
