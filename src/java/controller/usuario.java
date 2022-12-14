package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UsuarioDAO;
import models.Usuario;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "usuario", urlPatterns = {"/usuario"})
public class usuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if(acao == null || acao == "")
            acao = "Listar";
        
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Usuario> listaUsuarios = usuarioDAO.ListaDeUsuarios();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaUsuarios", listaUsuarios);
                    rd = request.getRequestDispatcher("/views/listaUsuarios.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar usuarios (Usuario) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    usuario = usuarioDAO.getUsuario(id);
                    usuario.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;

        }

        request.setAttribute("usuario", usuario);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formUsuario.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || senha_user.isEmpty()) {

            Usuario usuario = new Usuario();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        UsuarioDAO usuarioDAO = new UsuarioDAO();
                        usuario = usuarioDAO.getUsuario(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de usuario - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("usuario", usuario);

            rd = request.getRequestDispatcher("/views/formUsuario.jsp");
            rd.forward(request, response);

        } else {

            Usuario usuario = new Usuario(nome_user, cpf_user, senha_user);
            usuario.setId(id);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        usuarioDAO.Inserir(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        try {
                            usuarioDAO.Alterar(usuario);
                        } catch (Exception ex) {
                            if (ex.getMessage().contains("foreign key constraint fails")) {
                                request.setAttribute("msgError", "Este paciente possui exames/consultas em seu nome.\n Exclua-as primeiro para poder eliminar o paciente.");
                                rd = request.getRequestDispatcher("/views/login.jsp");
                                rd.forward(request, response);
                            }
                        }
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        usuarioDAO.Excluir(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Usuario> listaUsuarios = usuarioDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);

                rd = request.getRequestDispatcher("/views/listaUsuarios.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario - " + ex.getMessage());
            }
        }
    }
}
