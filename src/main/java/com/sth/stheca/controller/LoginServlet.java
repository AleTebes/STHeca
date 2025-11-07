package com.sth.stheca.controller;

import com.sth.stheca.dao.UsuarioDAO;
import com.sth.stheca.model.Usuario;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        MongoDatabase db = (MongoDatabase) getServletContext().getAttribute("MONGO_DB");
        if (db == null) throw new ServletException("MongoDatabase no inicializado en ServletContext");
        this.usuarioDAO = new UsuarioDAO(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mostrar formulario de login
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Usuario usuario = usuarioDAO.autenticar(email, password);
        if (usuario != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("usuario", usuario);
            // Redirect al menú
            resp.sendRedirect(req.getContextPath() + "/menu");
        } else {
            req.setAttribute("error", "Credenciales inválidas");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
