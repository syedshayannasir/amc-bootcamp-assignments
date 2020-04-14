package com.ttn.training;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

// Question 1. Create a servlet and register it by path.

@Component(service = Servlet.class, property ={
        "sling.servlet.paths=" + "/libs/path-servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
})
public class PathServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>This servlet is registered via Path </h2>");
        out.flush();
    }
}
