/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UsuarioDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = "Login";
        RequestDispatcher rd;
        switch (acao) {
            case "Login": // chama form de login

                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("/views/login.jsp");
                rd.forward(request, response);
                break;

            case "Logout":

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("login");
        String senha_user = request.getParameter("password");

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/login.jsp");
            rd.forward(request, response);

        } else {
            Usuario usuarioObtido;
            Usuario usuario = new Usuario(cpf_user, senha_user);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                usuarioObtido = usuarioDAO.Logar(usuario);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar - " + ex.getMessage());
            }

            if (usuarioObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioObtido);

                rd = request.getRequestDispatcher("/");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/login.jsp");
                rd.forward(request, response);

            }
        }
    }
}
