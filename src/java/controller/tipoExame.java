package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.TipoExameDAO;
import models.TipoExame;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "tipoExame", urlPatterns = { "/tipoExame" })
public class tipoExame extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        TipoExame tipoExame = new TipoExame();
        TipoExameDAO tipoExameDAO = new TipoExameDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    request.setAttribute("msgOperacaoRealizada", "");
                    ArrayList<TipoExame> listaTipoExames = tipoExameDAO.ListaDeTipoExames();
                    request.setAttribute("listaTipoExames", listaTipoExames);
                    rd = request.getRequestDispatcher("/views/listaTipoExames.jsp");
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar tipoExames (TipoExame) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    tipoExame = tipoExameDAO.getTipoExame(id);
                    tipoExame.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de tipoExame");
                }
                break;

        }
        request.setAttribute("tipoExame", tipoExame);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formTipoExame.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String descricao_user = request.getParameter("descricao");

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (descricao_user.isEmpty()) {

            TipoExame tipoExame = new TipoExame();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        TipoExameDAO tipoExameDAO = new TipoExameDAO();
                        tipoExame = tipoExameDAO.getTipoExame(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(
                                "Falha em uma query para cadastro de tipoExame - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("tipoExame", tipoExame);

            rd = request.getRequestDispatcher("/views/formTipoExame.jsp");
            rd.forward(request, response);

        } else {

            TipoExame tipoExame = new TipoExame(descricao_user);
            tipoExame.setId(id);

            TipoExameDAO tipoExameDAO = new TipoExameDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        tipoExameDAO.Inserir(tipoExame);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        tipoExameDAO.Alterar(tipoExame);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        try {
                            tipoExameDAO.Excluir(tipoExame);
                        } catch (Exception ex) {
                            if (ex.getMessage().contains("foreign key constraint fails")) {
                                request.setAttribute("msgError", "Este paciente possui exames/consultas em seu nome.\n Exclua-as primeiro para poder eliminar o paciente.");
                                rd = request.getRequestDispatcher("/views/login.jsp");
                                rd.forward(request, response);
                            }
                        }
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<TipoExame> listaTipoExames = tipoExameDAO.ListaDeTipoExames();
                request.setAttribute("listaTipoExames", listaTipoExames);

                rd = request.getRequestDispatcher("/views/listaTipoExames.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de tipoExame - " + ex.getMessage());
            }
        }
    }
}
