package controller;

import DAO.ConsultaDAO;
import DAO.ExameDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.ExameDAO;
import DAO.MedicoDAO;
import DAO.TipoExameDAO;
import models.Exame;
import javax.servlet.RequestDispatcher;
import models.Consulta;
import models.Exame;
import models.Medico;
import models.TipoExame;

@WebServlet(name = "exame", urlPatterns = { "/exame" })
public class exame extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        Exame exame = new Exame();
        ExameDAO exameDAO = new ExameDAO();
        RequestDispatcher rd;
        ArrayList<Exame> listaExames = exameDAO.ListaDeExames();
        request.setAttribute("listaExames", listaExames);

        switch (acao) {
            case "Listar":
                try {
                    request.setAttribute("msgOperacaoRealizada", "");
                    rd = request.getRequestDispatcher("/views/listaExames.jsp");
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar exames (Exame) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {

                    int id = Integer.parseInt(request.getParameter("id"));
                    exame = exameDAO.getExame(id);
                    exame.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de exame");
                }
                break;

        }

        TipoExameDAO tipoExameDAO = new TipoExameDAO();
        ArrayList<TipoExame> listaDeTipoExames = tipoExameDAO.ListaDeTipoExames();
        request.setAttribute("listaDeTipoExames", listaDeTipoExames);

        ConsultaDAO consultaDAO = new ConsultaDAO();
        ArrayList<Consulta> listaDeConsultas = consultaDAO.ListaDeConsultas();
        request.setAttribute("listaDeConsultas", listaDeConsultas);

        ArrayList<Exame> listaDeExames = exameDAO.ListaDeExames();
        request.setAttribute("listaDeExames", listaDeExames);

        request.setAttribute("exame", exame);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formExame.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String idtipoExame = request.getParameter("idtipoExame");
        String idexame = request.getParameter("idconsulta");

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (idtipoExame.isEmpty() || idexame.isEmpty()) {

            Exame exame = new Exame();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        ExameDAO exameDAO = new ExameDAO();
                        exame = exameDAO.getExame(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(
                                "Falha em uma query para cadastro de exame - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("exame", exame);

            rd = request.getRequestDispatcher("/views/formExame.jsp");
            rd.forward(request, response);

        } else {
            Exame exame = new Exame(Integer.parseInt(idtipoExame), Integer.parseInt(idexame));
            exame.setId(id);

            ExameDAO exameDAO = new ExameDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        exameDAO.Inserir(exame);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        exameDAO.Alterar(exame);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        exameDAO.Excluir(exame);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Exame> listaExames = exameDAO.ListaDeExames();
                request.setAttribute("listaExames", listaExames);

                rd = request.getRequestDispatcher("/views/listaExames.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de exame - " + ex.getMessage());
            }
        }
    }
}