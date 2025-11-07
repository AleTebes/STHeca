package com.sth.stheca.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MongoInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String uri = System.getenv("MONGODB_URI");
        // Compatible con Java 8: evitar isBlank()
        if (uri == null || uri.trim().isEmpty()) uri = "mongodb://localhost:27017";

        MongoClient client = MongoClients.create(uri);
        MongoDatabase db = client.getDatabase("sthecaDB");

        // Guardar para usar en Servlets/DAOs
        sce.getServletContext().setAttribute("MONGO_CLIENT", client);
        sce.getServletContext().setAttribute("MONGO_DB", db);

        System.out.println("✅ MongoDB conectado: " + uri);

        // Inicializar datos base
        com.sth.stheca.config.InicializarDatos.cargarDatos(db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient client = (MongoClient) sce.getServletContext().getAttribute("MONGO_CLIENT");
        if (client != null) client.close();
        System.out.println("👋 MongoDB cerrado");
    }
}
