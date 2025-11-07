package com.sth.stheca.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class InicializarDatos {

    public static void cargarDatos(MongoDatabase db) {
        try {
            // Asegurar que las colecciones existen (crearlas vacías si es necesario)
            List<String> existing = new ArrayList<>();
            db.listCollectionNames().into(existing);

            if (!existing.contains("usuarios")) {
                db.createCollection("usuarios");
                System.out.println(" Colección 'usuarios' creada vacía");
            }
            if (!existing.contains("dispositivos")) {
                db.createCollection("dispositivos");
                System.out.println(" Colección 'dispositivos' creada vacía");
            }
            if (!existing.contains("tickets")) {
                db.createCollection("tickets");
                System.out.println(" Colección 'tickets' creada vacía");
            }

            MongoCollection<Document> usuarios = db.getCollection("usuarios");
            MongoCollection<Document> dispositivos = db.getCollection("dispositivos");
            MongoCollection<Document> tickets = db.getCollection("tickets");

            // Tocar las colecciones para evitar advertencias de variables no usadas
            // (y asegurar que son accesibles aunque estén vacías)
            dispositivos.countDocuments();
            tickets.countDocuments();

            // Insertar solo un usuario admin por defecto si no existe
            Document admin = new Document("id", 1)
                    .append("nombre", "Administrador")
                    .append("email", "admin@sth.local")
                    .append("password", "admin")
                    .append("rol", "ADMIN");

            Document found = usuarios.find(new Document("email", "admin@sth.local")).first();
            if (found == null) {
                usuarios.insertOne(admin);
                System.out.println("✅ Usuario admin insertado");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe, no se inserta");
            }

            // Eliminar cualquier usuario llamado "Alexander" para evitar que aparezca en la app
            try {
                long removed = usuarios.deleteMany(new Document("nombre", "Alexander")).getDeletedCount();
                if (removed > 0) {
                    System.out.println("ℹ️ Se eliminaron " + removed + " documentos con nombre 'Alexander' durante la inicialización");
                }
            } catch (Exception ex) {
                System.err.println("Error al eliminar usuarios 'Alexander': " + ex.getMessage());
            }

            // Las colecciones 'dispositivos' y 'tickets' quedan vacías (si fueron creadas arriba)
            System.out.println("✅ Inicialización mínima completada: colecciones disponibles y admin garantizado");

        } catch (Exception e) {
            System.err.println("Error inicializando datos: " + e.getMessage());
        }
    }
}
