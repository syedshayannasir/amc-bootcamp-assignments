# AMC Bootcamp Assignments
## REST Architecture and Java Servlet
### Assignment


##### Question

	
Ques. Taking up a list of potential scripts for a request to a resource whose sling:resource type is example/sample and the request selectors are "print.a4" and the request extension is "html". What would be the order in which these scripts would be called ?

0. GET.jsp

1. sample.jsp

2. html.jsp

3. print.jsp

4. print.html.jsp

5. print/a4.jsp

6. print/a4/html.jsp 

7. print/a4.html.jsp


#### Answer

The order (priority) in which the scripts will be called is:

1. print/a4.html.js	- ***(selector)selector + extension***
2. print/a4/html.jsp - ***(selector)node name + extension***
3. print/a4.jsp - ***(selector)selector***
4. print.html.jsp - ***selector + extension***
5. print.jsp - ***selector***
6. html.jsp - ***extension***
7. sample.jsp - ***node name***
8. GET.jsp - ***default GET priority***


**Reference Priority: selector+extension > selector > extension > node name > GET/POST**



#### Note
- The sling node hierarchy specified in the question is available as a zip package in 'sling-assignment-package' directory
- https://sling.apache.org/downloads.cgi ***Link to sling self-runnable starter jar***
