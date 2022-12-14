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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Consulta;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import models.Medico;
import models.Paciente;
import models.Usuario;

@WebServlet(name = "consulta", urlPatterns = {"/consulta"})
public class consulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        if (acao == null || acao == "") {
            acao = "Listar";
        }
        HttpSession session = request.getSession();
        String tipoConta = (String) session.getAttribute("tipoUsuario");
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        Consulta consulta = new Consulta();
        ConsultaDAO consultaDAO = new ConsultaDAO();
        RequestDispatcher rd;

        ArrayList<Paciente> listaDePacientes = new ArrayList<Paciente>();
        PacienteDAO pacienteDAO = new PacienteDAO();

        MedicoDAO medicoDAO = new MedicoDAO();
        ArrayList<Medico> listaDeMedicos = new ArrayList<Medico>();

        try {

            if (tipoConta == Enums.TipoConta.Paciente) {
                listaDeMedicos = medicoDAO.ListaDeMedicos();
                listaDePacientes.add(pacienteDAO.getPaciente(usuarioLogado.getId()));
            } else {
                listaDePacientes = pacienteDAO.ListaDePacientes();
                if (tipoConta == Enums.TipoConta.Medico) {
                    listaDeMedicos.add(medicoDAO.getMedico(usuarioLogado.getId()));
                } else {
                    listaDeMedicos = medicoDAO.ListaDeMedicos();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }

        String selectedMedico = (String) request.getParameter("idMedico");
        if (selectedMedico == null) {
            selectedMedico = "0";
        }
        request.setAttribute("selectedMedico", selectedMedico);
        request.setAttribute("listaDeMedicos", listaDeMedicos);
        request.setAttribute("listaDePacientes", listaDePacientes);

        switch (acao) {
            case "Listar":
                try {

                    ArrayList<Consulta> listaConsultas = new ArrayList<Consulta>();

                    if (tipoConta == Enums.TipoConta.Paciente) {
                        listaConsultas = consultaDAO.ListaDeConsultasPaciente(usuarioLogado.getId());
                    } else if (tipoConta == Enums.TipoConta.Medico) {
                        listaConsultas = consultaDAO.ListaDeConsultasMedico(usuarioLogado.getId());
                    } else {
                        if (selectedMedico != null && selectedMedico != "" && selectedMedico != "0") {
                            listaConsultas = consultaDAO.ListaDeConsultasMedico(Integer.parseInt(selectedMedico));
                        } else {
                            listaConsultas = consultaDAO.ListaDeConsultas();
                        }
                    }

                    request.setAttribute("msgOperacaoRealizada", "");

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

        if (realizada == null) {
            realizada = "N";
        }

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

                ArrayList<Consulta> listaConsultas = new ArrayList<Consulta>();
                HttpSession session = request.getSession();
                String tipoConta = (String) session.getAttribute("tipoUsuario");
                Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

                if (tipoConta == Enums.TipoConta.Paciente) {
                    listaConsultas = consultaDAO.ListaDeConsultasPaciente(usuarioLogado.getId());
                } else if (tipoConta == Enums.TipoConta.Medico) {
                    listaConsultas = consultaDAO.ListaDeConsultasMedico(usuarioLogado.getId());
                } else {
                    listaConsultas = consultaDAO.ListaDeConsultas();
                }

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
