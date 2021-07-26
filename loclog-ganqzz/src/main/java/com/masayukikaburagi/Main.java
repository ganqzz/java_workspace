package com.masayukikaburagi;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final String webRoot = "src/main/webapp";

        // Heroku dynamic port
        String envPort = System.getenv("PORT");

        // 10080:ローカル環境
        int port = (envPort == null) ? 10080 : Integer.parseInt(envPort);

        // Create the server
        Server server = new Server(port);

        // Create a WebApp (directory)
        WebAppContext webApp = new WebAppContext();
        webApp.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(),
                new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration()
        });
        webApp.setContextPath("/");
        webApp.setExtraClasspath("./"); // for scan
        webApp.setResourceBase(webRoot);
        //webApp.setDescriptor(webRoot + "/WEB-INF/web.xml");
        webApp.setParentLoaderPriority(true);

        server.setHandler(webApp);
        server.start();
        server.join();
    }
}
