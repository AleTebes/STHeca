package com.sth.stheca.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sth.stheca.model.Usuario;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final MongoCollection<Document> usuarios;

    public UsuarioDAO(MongoDatabase db) {
        this.usuarios = db.getCollection("usuarios");
    }

    // Obtener próximo id (simple, no transaccional)
    private int nextId() {
        // Recorremos todos los documentos y calculamos el máximo id (manejo de id almacenado como String o number)
        int max = 0;
        for (Document d : usuarios.find()) {
            Object idField = d.get("id");
            int id = parseIdField(idField);
            if (id > max) max = id;
        }
        return max + 1;
    }

    // Intenta parsear distintos tipos que pueda devolver Document.get("id")
    private int parseIdField(Object idField) {
        if (idField == null) return 0;
        if (idField instanceof Integer) return (Integer) idField;
        if (idField instanceof Long) return ((Long) idField).intValue();
        if (idField instanceof Double) return ((Double) idField).intValue();
        if (idField instanceof String) {
            try {
                return Integer.parseInt((String) idField);
            } catch (NumberFormatException e) {
                try {
                    return (int) Double.parseDouble((String) idField);
                } catch (NumberFormatException ex) {
                    return 0;
                }
            }
        }
        return 0;
    }

    //Listar Datos
    public List<Usuario> listar() {
        List<Usuario> list = new ArrayList<>();
        for (Document d : usuarios.find()) {
            Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
            u.setId(parseIdField(d.get("id")));
            list.add(u);
        }
        return list;
    }

    //Buscar por ID
    public Usuario buscarPorId(int id) {
        Document d = usuarios.find(Filters.eq("id", id)).first();
        if (d == null) {
            // Intentar buscar si el id está guardado como String
            d = usuarios.find(Filters.eq("id", String.valueOf(id))).first();
        }
        if (d == null) return null;
        Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
        u.setId(parseIdField(d.get("id")));
        return u;
    }

    //Agregar Datos
    public void agregar(Usuario usuario) {
        int id = nextId();
        usuario.setId(id);
        Document d = new Document("id", id)
                .append("nombre", usuario.getNombre())
                .append("email", usuario.getEmail())
                .append("password", usuario.getPassword())
                .append("rol", usuario.getRol());
        usuarios.insertOne(d);
        System.out.println("Usuario insertado con id: " + id);
    }

    //Eliminar Datos
    public void eliminar(int id) {
        org.bson.Document deleted = usuarios.findOneAndDelete(Filters.eq("id", id));
        if (deleted == null) {
            // Intentar con id como String
            deleted = usuarios.findOneAndDelete(Filters.eq("id", String.valueOf(id)));
        }
        System.out.println("El usuario con id: " + id + " ha sido eliminado");
    }

    //Modificar Datos
    public void modificar(Usuario usuario) {
        Document update = new Document("nombre", usuario.getNombre())
                .append("email", usuario.getEmail())
                .append("password", usuario.getPassword())
                .append("rol", usuario.getRol());
        com.mongodb.client.result.UpdateResult res = usuarios.updateOne(Filters.eq("id", usuario.getId()), new Document("$set", update));
        if (res.getModifiedCount() == 0) {
            // Intentar con id guardado como String
            usuarios.updateOne(Filters.eq("id", String.valueOf(usuario.getId())), new Document("$set", update));
        }
        System.out.println("El usuario con id: " + usuario.getId() + " ha sido modificado");
    }

    // Autenticar usuario por email y password
    public Usuario autenticar(String email, String password) {
        Document d = usuarios.find(Filters.and(Filters.eq("email", email), Filters.eq("password", password))).first();
        if (d == null) return null;
        Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
        u.setId(parseIdField(d.get("id")));
        return u;
    }
}
