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
        Document last = usuarios.find().sort(new Document("id", -1)).first();
        if (last == null) return 1;
        return last.getInteger("id", 0) + 1;
    }

    //Listar Datos
    public List<Usuario> listar() {
        List<Usuario> list = new ArrayList<>();
        for (Document d : usuarios.find()) {
            Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
            u.setId(d.getInteger("id", 0));
            list.add(u);
        }
        return list;
    }

    //Buscar por ID
    public Usuario buscarPorId(int id) {
        Document d = usuarios.find(Filters.eq("id", id)).first();
        if (d == null) return null;
        Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
        u.setId(d.getInteger("id", 0));
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
        usuarios.deleteOne(Filters.eq("id", id));
        System.out.println("El usuario con id: " + id + " ha sido eliminado");
    }

    //Modificar Datos
    public void modificar(Usuario usuario) {
        Document update = new Document("nombre", usuario.getNombre())
                .append("email", usuario.getEmail())
                .append("password", usuario.getPassword())
                .append("rol", usuario.getRol());
        usuarios.updateOne(Filters.eq("id", usuario.getId()), new Document("$set", update));
        System.out.println("El usuario con id: " + usuario.getId() + " ha sido modificado");
    }

    // Autenticar usuario por email y password
    public Usuario autenticar(String email, String password) {
        Document d = usuarios.find(Filters.and(Filters.eq("email", email), Filters.eq("password", password))).first();
        if (d == null) return null;
        Usuario u = new Usuario(d.getString("nombre"), d.getString("email"), d.getString("password"), d.getString("rol"));
        u.setId(d.getInteger("id", 0));
        return u;
    }
}
