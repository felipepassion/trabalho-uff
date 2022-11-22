package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.ConsultaDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import models.Consulta;
import javax.servlet.RequestDispatcher;
import models.Medico;
import models.Paciente;

@WebServlet(name = "consulta", urlPatterns = { "/consulta" })
public class consulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        Consulta consulta = new Consulta();
        ConsultaDAO consultaDAO = new ConsultaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    request.setAttribute("msgOperacaoRealizada", "");
                    ArrayList<Consulta> listaConsultas = consultaDAO.ListaDeConsultas();
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/views/listaConsultas.jsp");
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar consultas (Consulta) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {

                    int id = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.getConsulta(id);
                    consulta.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de consulta");
                }
                break;

        }

        PacienteDAO pacienteDAO = new PacienteDAO();
        ArrayList<Paciente> listaDePacientes = pacienteDAO.ListaDePacientes();
        request.setAttribute("listaDePacientes", listaDePacientes);

        MedicoDAO medicoDAO = new MedicoDAO();
        ArrayList<Medico> listaDeMedicos = medicoDAO.ListaDeMedicos();
        request.setAttribute("listaDeMedicos", listaDeMedicos);

        request.setAttribute("consulta", consulta);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formConsulta.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String descricao_user = request.getParameter("descricao");
        String data = request.getParameter("data");
        String idpaciente = request.getParameter("idpaciente");
        String idmedico = request.getParameter("idmedico");
        String realizada = request.getParameter("realizada");
        if(realizada == null)
            realizada = "N";
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (descricao_user.isEmpty() || data.isEmpty() || idpaciente.isEmpty() || idmedico.isEmpty()) {

            Consulta consulta = new Consulta();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        ConsultaDAO consultaDAO = new ConsultaDAO();
                        consulta = consultaDAO.getConsulta(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(
                                "Falha em uma query para cadastro de consulta - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("consulta", consulta);

            rd = request.getRequestDispatcher("/views/formConsulta.jsp");
            rd.forward(request, response);

        } else {
            System.out.println("data: " + data);
            Consulta consulta = new Consulta(data, descricao_user, realizada, Integer.parseInt(idmedico),
                    Integer.parseInt(idpaciente));
            consulta.setId(id);

            ConsultaDAO consultaDAO = new ConsultaDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        consultaDAO.Inserir(consulta);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        consultaDAO.Alterar(consulta);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        consultaDAO.Excluir(consulta);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Consulta> listaConsultas = consultaDAO.ListaDeConsultas();
                request.setAttribute("listaConsultas", listaConsultas);

                rd = request.getRequestDispatcher("/views/listaConsultas.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de consulta - " + ex.getMessage());
            }
        }
    }
}
