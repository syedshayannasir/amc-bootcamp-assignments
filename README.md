# AMC Bootcamp Assignments

### Maven and OSGI Assessment


1. Using Sling's Maven archetype, generate a Sling Maven Project. (You'll need to find the artifact id and group id for the latest version of the Sling archetype)
2. Deploy the project bundle using the already created maven profile autoInstallBundle. (You need to find how to pass a profile param to the maven command)
3. Create 2 services:

	1. TechBlogConfig: Contains the following configurations:
		a) Blog Category: Defaults to 'Technology' (String)
		b) Rank: Defaults to 1 (Integer)

	2) TechBlog: 
		a) Logs the message "TechBlog started" as soon as the current service is active.
		b) Fetches the configurations from the TechBlogConfig
		c) Contains a non-static method "getBlogCategory()" that returns the name of the current blog category.
		c) Contains a non-static method 'getRank()' that returns the configured rank

	3) MarketBlogConfig: Contains the following configurations:
		a) Blog Category: Defaults to 'Marketing' (String)
		b) Rank: Defaults to 2 (Integer)

	2) MarketBlog: 
		a) Logs the message "MarketBlog started" as soon as the current service is active
		b) Fetches the configurations from the MarketBlogConfig
		c) Contains a non-static method "getBlogCategory()" that returns the name of the current blog category.
		d) Contains a non-static method 'getRank()' that returns the configured rank

	3) BlogsCollector: As soon as this service is active:
		a) Log the message "BlogsCollector".
		b) Calls the 'getBlogCategory()' and 'getRank()' methods created above and logs the returned values for each of the above blog services.
	



Please zip your log files and the complete project and submit it to your mentors.

