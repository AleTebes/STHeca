package com.sth.stheca.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sth.stheca.model.TipoDispositivo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TipoDispositivoDAO {
    private final MongoCollection<Document> tipos;

    public TipoDispositivoDAO(MongoDatabase db) {
        this.tipos = db.getCollection("tipos_dispositivo");
    }

    private int nextId() {
        Document last = tipos.find().sort(new Document("id", -1)).first();
        if (last == null) return 1;
        return last.getInteger("id", 0) + 1;
    }

    // Listar todos
    public List<TipoDispositivo> listar() {
        List<TipoDispositivo> list = new ArrayList<>();
        for (Document d : tipos.find()) {
            TipoDispositivo t = new TipoDispositivo(d.getInteger("id", 0), d.getString("nombre"));
            t.setDescripcion(d.getString("descripcion"));
            list.add(t);
        }
        return list;
    }

    // Buscar por id
    public TipoDispositivo buscarPorId(int id) {
        Document d = tipos.find(Filters.eq("id", id)).first();
        if (d == null) return null;
        TipoDispositivo t = new TipoDispositivo(d.getInteger("id", 0), d.getString("nombre"));
        t.setDescripcion(d.getString("descripcion"));
        return t;
    }

    // Agregar
    public void agregar(TipoDispositivo tipo) {
        int id = nextId();
        tipo.setId(id);
        Document d = new Document("id", id)
                .append("nombre", tipo.getNombre())
                .append("descripcion", tipo.getDescripcion());
        tipos.insertOne(d);
        System.out.println("TipoDispositivo insertado con id: " + id);
    }

    // Eliminar
    public void eliminar(int id) {
        tipos.deleteOne(Filters.eq("id", id));
        System.out.println("El TipoDispositivo con id: " + id + " ha sido eliminado");
    }

    // Modificar
    public void modificar(TipoDispositivo tipo) {
        Document update = new Document("nombre", tipo.getNombre())
                .append("descripcion", tipo.getDescripcion());
        tipos.updateOne(Filters.eq("id", tipo.getId()), new Document("$set", update));
        System.out.println("El TipoDispositivo con id: " + tipo.getId() + " ha sido modificado");
    }
}

