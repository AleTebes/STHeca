package com.sth.stheca.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sth.stheca.model.Dispositivo;
import com.sth.stheca.model.TipoDispositivo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO {
    private final MongoCollection<Document> dispositivos;

    public DispositivoDAO(MongoDatabase db) {
        this.dispositivos = db.getCollection("dispositivos");
    }

    private int nextId() {
        Document last = dispositivos.find().sort(new Document("id", -1)).first();
        if (last == null) return 1;
        return last.getInteger("id", 0) + 1;
    }

    //Listar Datos
    public List<Dispositivo> listar() {
        List<Dispositivo> list = new ArrayList<>();
        for (Document d : dispositivos.find()) {
            // El campo "tipo" puede venir como subdocumento o como String (compatibilidad con datos antiguos)
            Object tipoField = d.get("tipo");
            TipoDispositivo tipo = null;
            if (tipoField instanceof Document) {
                Document tipoDoc = (Document) tipoField;
                tipo = new TipoDispositivo(tipoDoc.getInteger("id", 0), tipoDoc.getString("nombre"));
                tipo.setDescripcion(tipoDoc.getString("descripcion"));
            } else if (tipoField instanceof String) {
                tipo = new TipoDispositivo((String) tipoField);
            }
            Dispositivo disp = new Dispositivo(d.getInteger("id", 0), d.getString("nombre"), tipo);
            list.add(disp);
        }
        return list;
    }

    //Buscar por ID
    public Dispositivo buscarPorId(int id) {
        Document d = dispositivos.find(Filters.eq("id", id)).first();
        if (d == null) return null;
        // Manejar "tipo" como Document o String
        Object tipoField = d.get("tipo");
        TipoDispositivo tipo = null;
        if (tipoField instanceof Document) {
            Document tipoDoc = (Document) tipoField;
            tipo = new TipoDispositivo(tipoDoc.getInteger("id", 0), tipoDoc.getString("nombre"));
            tipo.setDescripcion(tipoDoc.getString("descripcion"));
        } else if (tipoField instanceof String) {
            tipo = new TipoDispositivo((String) tipoField);
        }
        return new Dispositivo(d.getInteger("id", 0), d.getString("nombre"), tipo);
    }

    //Agregar Datos
    public void agregar(Dispositivo dispositivo) {
        int id = nextId();
        dispositivo.setId(id);
        Document d = new Document("id", id)
                .append("nombre", dispositivo.getNombre());
        if (dispositivo.getTipo() != null) {
            Document tipoDoc = new Document("id", dispositivo.getTipo().getId())
                    .append("nombre", dispositivo.getTipo().getNombre())
                    .append("descripcion", dispositivo.getTipo().getDescripcion());
            d.append("tipo", tipoDoc);
        } else {
            d.append("tipo", null);
        }
        dispositivos.insertOne(d);
        System.out.println("Dispositivo insertado con id: " + id);
    }

    //Eliminar Datos
    public void eliminar(int id) {
        dispositivos.deleteOne(Filters.eq("id", id));
        System.out.println("El dispositivo con id: " + id + " ha sido eliminado");
    }

    //Modificar Datos
    public void modificar(Dispositivo dispositivo) {
        Document update = new Document("nombre", dispositivo.getNombre());
        if (dispositivo.getTipo() != null) {
            Document tipoDoc = new Document("id", dispositivo.getTipo().getId())
                    .append("nombre", dispositivo.getTipo().getNombre())
                    .append("descripcion", dispositivo.getTipo().getDescripcion());
            update.append("tipo", tipoDoc);
        } else {
            update.append("tipo", null);
        }
        dispositivos.updateOne(Filters.eq("id", dispositivo.getId()), new Document("$set", update));
        System.out.println("El dispositivo con id: " + dispositivo.getId() + " ha sido modificado");
    }
}
