package com.ttn.training;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.jcr.Node;

//Question 2. Create a sling servlet to list out all the blogs in such a way so that we can display the blogs
// along with creation date and sort it by date in either way,
// (both option should be there to sort in ascending and descending order).

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=" + "/libs/sorted-phones",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
})
public class SortedPhones extends SlingSafeMethodsServlet {

    String name;
    String description;
    String price;
    String createdDate;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        ResourceResolver resourceResolver = request.getResourceResolver();

        Iterable<Resource> blogs = resourceResolver.getResource("/content/phones").getChildren();

        List<Phone> phones = new ArrayList<Phone>();

        for (Resource blog : blogs) {
            Node node = blog.adaptTo(Node.class);

            try {
                name = node.getProperty("jcr:title").getValue().toString();
                description = node.getProperty("jcr:description").getValue().toString();
                price = node.getProperty("price").getValue().toString();
                createdDate = node.getProperty("jcr:created").getValue().toString();

            } catch (RepositoryException e) {
                e.printStackTrace();
            }

            phones.add(new Phone(name, description, price, createdDate));

        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Collections.sort(phones);
        out.println("<h3>Ascending order</h3>");
        out.println(phones);

        Collections.sort(phones, new Comparator<Phone>() {
            @Override
            public int compare(Phone phone, Phone t1) {
                return ~phone.createdDate.compareTo(t1.getCreatedDate());
            }
        });
        out.println("<h3>Descending order</h3>");
        out.println(phones);


    }

}
