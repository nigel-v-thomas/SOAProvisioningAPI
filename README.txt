Intro
=====
This sample uses JAX-RS with Jersey and Spring framework. Enunciate is used to generate readable HTML markup.
Spring framework is used to handle dependency injection and security aspects, it is possible to declaratively configure parts of the api for authentication, and in future extend to support RBAC 
Some basic tests have been added, these tests the api as deployed in a container using the JerseyTest framework.

Deployment and run
==========
To deploy and run use:
$ mvn clean package cargo:run 

This will run some basic tests and generate the war file and run on the application on port 8080 using Jersey.
Note that provisioning/students requires authentication, login using (username:user, password:password).