package com.sth.stheca.controller;

import com.sth.stheca.dao.UsuarioDAO;
import com.sth.stheca.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
@WebServlet(name = "UsuarioServerlet", urlPatterns = {"/usuario"})

public class UsuarioServerlet extends HttpServlet {

    private UsuarioDAO dao;

    @Override
    public void init() throws ServletException {
        MongoDatabase db = (MongoDatabase) getServletContext().getAttribute("MONGO_DB");
        if (db == null) throw new ServletException("MongoDatabase no inicializado en ServletContext");
        this.dao = new UsuarioDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        switch (action) {
            case "listar":
                req.setAttribute("usuarios", dao.listar());
                req.getRequestDispatcher("/WEB-INF/views/usuario/listar.jsp").forward(req, resp);
                break;
            case "agregar":
                req.getRequestDispatcher("/WEB-INF/views/usuario/agregar.jsp").forward(req, resp);
                break;
            case "editar":
                int idEditar = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("usuario", dao.buscarPorId(idEditar));
                req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(req.getParameter("id"));
                dao.eliminar(idEliminar);
                resp.sendRedirect(req.getContextPath() + "/usuario");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/usuario");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) action = "guardar";

        if (action.equals("guardar")) {
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rol = req.getParameter("rol");

            Usuario u = new Usuario(nombre, email, password, rol);
            dao.agregar(u);
            resp.sendRedirect(req.getContextPath() + "/usuario");
            return;
        } else if (action.equals("actualizar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rol = req.getParameter("rol");

            Usuario u = new Usuario(nombre, email, password, rol);
            u.setId(id);
            dao.modificar(u);
            resp.sendRedirect(req.getContextPath() + "/usuario");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/usuario");
    }
}