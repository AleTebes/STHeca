package com.sth.stheca.controller;

import com.sth.stheca.dao.TipoDispositivoDAO;
import com.sth.stheca.model.TipoDispositivo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;

@WebServlet(name = "TipoDispositivoServlet", urlPatterns = {"/tipodispositivo"})
public class TipoDispositivoServlet extends HttpServlet {
    private TipoDispositivoDAO dao;

    @Override
    public void init() throws ServletException {
        MongoDatabase db = (MongoDatabase) getServletContext().getAttribute("MONGO_DB");
        if (db == null) throw new ServletException("MongoDatabase no inicializado en ServletContext");
        this.dao = new TipoDispositivoDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "listar";
        switch (action) {
            case "listar":
                req.setAttribute("tipos", dao.listar());
                req.getRequestDispatcher("/WEB-INF/views/tipodispositivo/listar.jsp").forward(req, resp);
                break;
            case "agregar":
                req.getRequestDispatcher("/WEB-INF/views/tipodispositivo/agregar.jsp").forward(req, resp);
                break;
            case "editar":
                int idEditar = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("tipo", dao.buscarPorId(idEditar));
                req.getRequestDispatcher("/WEB-INF/views/tipodispositivo/editar.jsp").forward(req, resp);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(req.getParameter("id"));
                dao.eliminar(idEliminar);
                resp.sendRedirect(req.getContextPath() + "/tipodispositivo");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/tipodispositivo");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) action = "guardar";

        if (action.equals("guardar")) {
            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            TipoDispositivo t = new TipoDispositivo(0, nombre);
            t.setDescripcion(descripcion);
            dao.agregar(t);
            resp.sendRedirect(req.getContextPath() + "/tipodispositivo");
            return;
        } else if (action.equals("actualizar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            TipoDispositivo t = new TipoDispositivo(id, nombre);
            t.setDescripcion(descripcion);
            dao.modificar(t);
            resp.sendRedirect(req.getContextPath() + "/tipodispositivo");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/tipodispositivo");
    }
}

