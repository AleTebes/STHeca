package com.sth.stheca.controller;

import com.sth.stheca.dao.TicketDAO;
import com.sth.stheca.dao.DispositivoDAO;
import com.sth.stheca.dao.UsuarioDAO;
import com.sth.stheca.model.Dispositivo;
import com.sth.stheca.model.Ticket;
import com.sth.stheca.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TicketServerlet", urlPatterns = {"/ticket"})
public class TicketServerlet extends HttpServlet {

    private TicketDAO dao;
    private MongoDatabase db;

    @Override
    public void init() throws ServletException {
        this.db = (MongoDatabase) getServletContext().getAttribute("MONGO_DB");
        if (db == null) throw new ServletException("MongoDatabase no inicializado en ServletContext");
        this.dao = new TicketDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) { action = "listar"; }
        switch (action) {
            case "listar":
                // añadir información sobre si existen dispositivos para controlar el modal en la UI
                DispositivoDAO dispDaoList = new DispositivoDAO(db);
                List<Dispositivo> dispositivosList = dispDaoList.listar();
                boolean hasDispositivos = dispositivosList != null && !dispositivosList.isEmpty();
                req.setAttribute("hasDispositivos", hasDispositivos);
                req.setAttribute("tickets", dao.listar());
                req.getRequestDispatcher("/WEB-INF/views/ticket/listar.jsp").forward(req, resp);
                break;
            case "agregar":
                // pasar listas para seleccionar dispositivo y usuario
                DispositivoDAO dispDao = new DispositivoDAO(db);
                UsuarioDAO userDao = new UsuarioDAO(db);
                List<Dispositivo> dispositivos = dispDao.listar();
                List<Usuario> usuarios = userDao.listar();

                // Si no hay dispositivos, pedir que se cree uno antes de permitir crear ticket
                if (dispositivos == null || dispositivos.isEmpty()) {
                    req.setAttribute("usuarios", usuarios);
                    req.setAttribute("mensaje", "No existen dispositivos. Debe crear al menos un dispositivo antes de crear un ticket.");
                    req.getRequestDispatcher("/WEB-INF/views/dispositivo/crear.jsp").forward(req, resp);
                    break;
                }

                req.setAttribute("dispositivos", dispositivos);
                req.setAttribute("usuarios", usuarios);
                req.getRequestDispatcher("/WEB-INF/views/ticket/agregar.jsp").forward(req, resp);
                break;
            case "editar":
                int idEditar = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("ticket", dao.buscarPorId(idEditar));
                // pasar listas para selects
                DispositivoDAO dispDao2 = new DispositivoDAO(db);
                UsuarioDAO userDao2 = new UsuarioDAO(db);
                req.setAttribute("dispositivos", dispDao2.listar());
                req.setAttribute("usuarios", userDao2.listar());
                req.getRequestDispatcher("/WEB-INF/views/ticket/editar.jsp").forward(req, resp);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(req.getParameter("id"));
                dao.eliminar(idEliminar);
                resp.sendRedirect(req.getContextPath() + "/ticket");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/ticket");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) action = "guardar";

        if (action.equals("guardar")) {
            String titulo = req.getParameter("titulo");
            String descripcion = req.getParameter("descripcion");
            String estado = req.getParameter("estado");

            String dispositivoIdStr = req.getParameter("dispositivoId");
            String usuarioIdStr = req.getParameter("usuarioId");

            Dispositivo d = null;
            Usuario u = null;
            if (dispositivoIdStr != null && !dispositivoIdStr.isEmpty()) {
                int did = Integer.parseInt(dispositivoIdStr);
                // buscar nombre/tipo reales
                DispositivoDAO dispDao3 = new DispositivoDAO(db);
                d = dispDao3.buscarPorId(did);
            }
            if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
                int uid = Integer.parseInt(usuarioIdStr);
                UsuarioDAO userDao3 = new UsuarioDAO(db);
                u = userDao3.buscarPorId(uid);
            }

            Ticket t = new Ticket(titulo, descripcion, estado, d, u);
            dao.agregar(t);
            resp.sendRedirect(req.getContextPath() + "/ticket");
            return;
        } else if (action.equals("actualizar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String titulo = req.getParameter("titulo");
            String descripcion = req.getParameter("descripcion");
            String estado = req.getParameter("estado");

            String dispositivoIdStr = req.getParameter("dispositivoId");
            String usuarioIdStr = req.getParameter("usuarioId");

            Dispositivo d = null;
            Usuario u = null;
            if (dispositivoIdStr != null && !dispositivoIdStr.isEmpty()) {
                int did = Integer.parseInt(dispositivoIdStr);
                DispositivoDAO dispDao4 = new DispositivoDAO(db);
                d = dispDao4.buscarPorId(did);
            }
            if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
                int uid = Integer.parseInt(usuarioIdStr);
                UsuarioDAO userDao4 = new UsuarioDAO(db);
                u = userDao4.buscarPorId(uid);
            }

            Ticket t = new Ticket(titulo, descripcion, estado, d, u);
            t.setId(id);
            dao.modificar(t);
            resp.sendRedirect(req.getContextPath() + "/ticket");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/ticket");
    }
}
