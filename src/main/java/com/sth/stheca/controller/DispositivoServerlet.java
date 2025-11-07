package com.sth.stheca.controller;

import com.sth.stheca.dao.DispositivoDAO;
import com.sth.stheca.dao.TipoDispositivoDAO;
import com.sth.stheca.model.Dispositivo;
import com.sth.stheca.model.TipoDispositivo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;

@WebServlet(name = "DispositivoServerlet", urlPatterns = {"/dispositivo"})
public class DispositivoServerlet extends HttpServlet {

    private DispositivoDAO dao;
    private TipoDispositivoDAO tipoDao;

    @Override
    public void init() throws ServletException {
        MongoDatabase db = (MongoDatabase) getServletContext().getAttribute("MONGO_DB");
        if (db == null) throw new ServletException("MongoDatabase no inicializado en ServletContext");
        this.dao = new DispositivoDAO(db);
        this.tipoDao = new TipoDispositivoDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) { action = "listar"; }
        switch (action) {
            case "listar":
                req.setAttribute("dispositivos", dao.listar());
                req.getRequestDispatcher("/WEB-INF/views/dispositivo/listar.jsp").forward(req, resp);
                break;
            case "agregar":
                // pasar tipos para el select
                req.setAttribute("tipos", tipoDao.listar());
                req.getRequestDispatcher("/WEB-INF/views/dispositivo/agregar.jsp").forward(req, resp);
                break;
            case "editar":
                int idEditar = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("dispositivo", dao.buscarPorId(idEditar));
                req.setAttribute("tipos", tipoDao.listar());
                req.getRequestDispatcher("/WEB-INF/views/dispositivo/editar.jsp").forward(req, resp);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(req.getParameter("id"));
                dao.eliminar(idEliminar);
                resp.sendRedirect(req.getContextPath() + "/dispositivo");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/dispositivo");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) action = "guardar";

        if (action.equals("guardar")) {
            String nombre = req.getParameter("nombre");
            String tipoIdStr = req.getParameter("tipoId");

            TipoDispositivo tipo = null;
            if (tipoIdStr != null && !tipoIdStr.isEmpty()) {
                int tipoId = Integer.parseInt(tipoIdStr);
                tipo = tipoDao.buscarPorId(tipoId);
            }

            Dispositivo d = new Dispositivo(0, nombre, tipo);
            dao.agregar(d);
            resp.sendRedirect(req.getContextPath() + "/dispositivo");
            return;
        } else if (action.equals("actualizar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String nombre = req.getParameter("nombre");
            String tipoIdStr = req.getParameter("tipoId");

            TipoDispositivo tipo = null;
            if (tipoIdStr != null && !tipoIdStr.isEmpty()) {
                int tipoId = Integer.parseInt(tipoIdStr);
                tipo = tipoDao.buscarPorId(tipoId);
            }

            Dispositivo d = new Dispositivo(id, nombre, tipo);
            dao.modificar(d);
            resp.sendRedirect(req.getContextPath() + "/dispositivo");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/dispositivo");
    }
}
