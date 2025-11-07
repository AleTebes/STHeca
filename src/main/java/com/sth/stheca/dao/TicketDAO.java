package com.sth.stheca.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sth.stheca.model.Dispositivo;
import com.sth.stheca.model.Ticket;
import com.sth.stheca.model.TipoDispositivo;
import com.sth.stheca.model.Usuario;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private final MongoCollection<Document> tickets;

    public TicketDAO(MongoDatabase db) {
        this.tickets = db.getCollection("tickets");
    }

    private int nextId() {
        Document last = tickets.find().sort(new Document("id", -1)).first();
        if (last == null) return 1;
        return last.getInteger("id", 0) + 1;
    }

    //Listar Datos
    public List<Ticket> listar() {
        List<Ticket> list = new ArrayList<>();
        for (Document d : tickets.find()) {
            Document dispDoc = (Document) d.get("dispositivo");
            Document userDoc = (Document) d.get("usuario");

            Dispositivo disp = null;
            Usuario user = null;
            if (dispDoc != null) {
                // El campo "tipo" se guarda como subdocumento de TipoDispositivo (o puede ser solo el nombre).
                // Intentamos construir un TipoDispositivo a partir del subdocumento si existe,
                // o a partir del nombre si "tipo" es un String.
                TipoDispositivo tipo = null;
                Object tipoField = dispDoc.get("tipo");
                if (tipoField instanceof Document) {
                    Document tipoDoc = (Document) tipoField;
                    tipo = new TipoDispositivo(tipoDoc.getInteger("id", 0), tipoDoc.getString("nombre"));
                } else if (tipoField instanceof String) {
                    tipo = new TipoDispositivo((String) tipoField);
                }
                disp = new Dispositivo(dispDoc.getInteger("id", 0), dispDoc.getString("nombre"), tipo);
            }
            if (userDoc != null) {
                user = new Usuario(userDoc.getString("nombre"), userDoc.getString("email"), userDoc.getString("password"), userDoc.getString("rol"));
            }

            Ticket t = new Ticket(d.getString("titulo"), d.getString("descripcion"), d.getString("estado"), disp, user);
            t.setId(d.getInteger("id", 0));
            list.add(t);
        }
        return list;
    }

    //Buscar por ID
    public Ticket buscarPorId(int id) {
        Document d = tickets.find(Filters.eq("id", id)).first();
        if (d == null) return null;

        Document dispDoc = (Document) d.get("dispositivo");
        Document userDoc = (Document) d.get("usuario");

        Dispositivo disp = null;
        Usuario user = null;
        if (dispDoc != null) {
            TipoDispositivo tipo = null;
            Object tipoField = dispDoc.get("tipo");
            if (tipoField instanceof Document) {
                Document tipoDoc = (Document) tipoField;
                tipo = new TipoDispositivo(tipoDoc.getInteger("id", 0), tipoDoc.getString("nombre"));
            } else if (tipoField instanceof String) {
                tipo = new TipoDispositivo((String) tipoField);
            }
            disp = new Dispositivo(dispDoc.getInteger("id", 0), dispDoc.getString("nombre"), tipo);
        }
        if (userDoc != null) {
            user = new Usuario(userDoc.getString("nombre"), userDoc.getString("email"), userDoc.getString("password"), userDoc.getString("rol"));
        }

        Ticket t = new Ticket(d.getString("titulo"), d.getString("descripcion"), d.getString("estado"), disp, user);
        t.setId(d.getInteger("id", 0));
        return t;
    }

    //Agregar Datos
    public void agregar(Ticket ticket) {
        int id = nextId();
        ticket.setId(id);

        Document dispDoc = null;
        if (ticket.getDispositivo() != null) {
            dispDoc = new Document("id", ticket.getDispositivo().getId())
                    .append("nombre", ticket.getDispositivo().getNombre());
            if (ticket.getDispositivo().getTipo() != null) {
                TipoDispositivo t = ticket.getDispositivo().getTipo();
                Document tipoDoc = new Document("id", t.getId())
                        .append("nombre", t.getNombre())
                        .append("descripcion", t.getDescripcion());
                dispDoc.append("tipo", tipoDoc);
            } else {
                dispDoc.append("tipo", null);
            }
        }

        Document userDoc = null;
        if (ticket.getUsuario() != null) {
            userDoc = new Document("id", ticket.getUsuario().getId())
                    .append("nombre", ticket.getUsuario().getNombre())
                    .append("email", ticket.getUsuario().getEmail())
                    .append("rol", ticket.getUsuario().getRol());
        }

        Document d = new Document("id", id)
                .append("titulo", ticket.getTitulo())
                .append("descripcion", ticket.getDescripcion())
                .append("estado", ticket.getEstado())
                .append("dispositivo", dispDoc)
                .append("usuario", userDoc);

        tickets.insertOne(d);
        System.out.println("Ticket insertado con id: " + id);
    }

    //Eliminar Datos
    public void eliminar(int id) {
        tickets.deleteOne(Filters.eq("id", id));
        System.out.println("El ticket con id: " + id + " ha sido eliminado");
    }

    //Modificar Datos
    public void modificar(Ticket ticket) {
        Document dispDoc = null;
        if (ticket.getDispositivo() != null) {
            dispDoc = new Document("id", ticket.getDispositivo().getId())
                    .append("nombre", ticket.getDispositivo().getNombre());
            if (ticket.getDispositivo().getTipo() != null) {
                TipoDispositivo t = ticket.getDispositivo().getTipo();
                Document tipoDoc = new Document("id", t.getId())
                        .append("nombre", t.getNombre())
                        .append("descripcion", t.getDescripcion());
                dispDoc.append("tipo", tipoDoc);
            } else {
                dispDoc.append("tipo", null);
            }
        }

        Document userDoc = null;
        if (ticket.getUsuario() != null) {
            userDoc = new Document("id", ticket.getUsuario().getId())
                    .append("nombre", ticket.getUsuario().getNombre())
                    .append("email", ticket.getUsuario().getEmail())
                    .append("rol", ticket.getUsuario().getRol());
        }

        Document update = new Document("titulo", ticket.getTitulo())
                .append("descripcion", ticket.getDescripcion())
                .append("estado", ticket.getEstado())
                .append("dispositivo", dispDoc)
                .append("usuario", userDoc);

        tickets.updateOne(Filters.eq("id", ticket.getId()), new Document("$set", update));
        System.out.println("El ticket con id: " + ticket.getId() + " ha sido modificado");
    }
}
