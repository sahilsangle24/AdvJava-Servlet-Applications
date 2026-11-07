package com.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener,
        ServletContextAttributeListener {

    // 1. Called when application starts
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Context Object Initialized");

        ServletContext context = sce.getServletContext();

        System.out.println("Application is deployed into: "
                + context.getServerInfo());
    }

    // 2. Called when application stops
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("Context Object Destroyed");
    }

    // 3. Called when a new attribute is added
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {

        System.out.println("Attribute added on ContextObject");
        System.out.println("Attribute name: " + scae.getName());
        System.out.println("Attribute value: " + scae.getValue());
    }

    // 4. Called when an attribute is removed
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {

        System.out.println("Attribute removed from ContextObject");
        System.out.println("Attribute name: " + scae.getName());
    }

    // 5. Called when an existing attribute is replaced
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {

        System.out.println("Attribute replaced in ContextObject");
        System.out.println("Attribute name: " + scae.getName());
        System.out.println("Old value: " + scae.getValue());
    }
}