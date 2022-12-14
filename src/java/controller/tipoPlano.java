package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.TipoPlanoDAO;
import models.TipoPlano;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "tipoPlano", urlPatterns = { "/tipoPlano" })
public class tipoPlano extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        TipoPlano tipoPlano = new TipoPlano();
        TipoPlanoDAO tipoPlanoDAO = new TipoPlanoDAO();
        RequestDispatcher rd;
        ArrayList<TipoPlano> listaTipoPlanos = tipoPlanoDAO.ListaDeTipoPlanos();
        request.setAttribute("listaTipoPlanos", listaTipoPlanos);
        switch (acao) {
            case "Listar":
                try {
                    request.setAttribute("msgOperacaoRealizada", "");

                    rd = request.getRequestDispatcher("/views/listaTipoPlanos.jsp");
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar tipoPlanos (TipoPlano) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    tipoPlano = tipoPlanoDAO.getTipoPlano(id);
                    tipoPlano.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de tipoPlano");
                }
                break;

        }

        request.setAttribute("tipoPlano", tipoPlano);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formTipoPlano.jsp");
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

            TipoPlano tipoPlano = new TipoPlano();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        TipoPlanoDAO tipoPlanoDAO = new TipoPlanoDAO();
                        tipoPlano = tipoPlanoDAO.getTipoPlano(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(
                                "Falha em uma query para cadastro de tipoPlano - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("tipoPlano", tipoPlano);

            rd = request.getRequestDispatcher("/views/formTipoPlano.jsp");
            rd.forward(request, response);

        } else {

            TipoPlano tipoPlano = new TipoPlano(descricao_user);
            tipoPlano.setId(id);

            TipoPlanoDAO tipoPlanoDAO = new TipoPlanoDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        tipoPlanoDAO.Inserir(tipoPlano);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        tipoPlanoDAO.Alterar(tipoPlano);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":                        
                        try {
                            tipoPlanoDAO.Excluir(tipoPlano);
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

                ArrayList<TipoPlano> listaTipoPlanos = tipoPlanoDAO.ListaDeTipoPlanos();
                request.setAttribute("listaTipoPlanos", listaTipoPlanos);

                rd = request.getRequestDispatcher("/views/listaTipoPlanos.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de tipoPlano - " + ex.getMessage());
            }
        }
    }
}
