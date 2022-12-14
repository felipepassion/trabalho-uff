package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.MedicoDAO;
import DAO.EspecialidadeDAO;
import models.Medico;
import javax.servlet.RequestDispatcher;
import models.Especialidade;

@WebServlet(name = "medico", urlPatterns = {"/medico"})
public class medico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        Medico medico = new Medico();
        MedicoDAO medicoDAO = new MedicoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Medico> listaMedicos = medicoDAO.ListaDeMedicos();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaMedicos", listaMedicos);
                    rd = request.getRequestDispatcher("/views/listaMedicos.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar medicos (Medico) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.getMedico(id);
                    medico.setId(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de medico");
                }
                break;

        }

        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        ArrayList<Especialidade> listaDeEspecialidade = especialidadeDAO.ListaDeEspecialidades();
        request.setAttribute("listaDeEspecialidade", listaDeEspecialidade);

        request.setAttribute("medico", medico);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formMedico.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String crm = request.getParameter("crm");
        String crm_estado = request.getParameter("crmestado");
        String id_especialidade = request.getParameter("idespecialidade");
        String autorizado = request.getParameter("autorizado");

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || senha_user.isEmpty() || senha_user.isEmpty()
                || id_especialidade.isEmpty() || crm.isEmpty() || crm_estado.isEmpty() || autorizado.isEmpty()) {

            Medico medico = new Medico();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        MedicoDAO medicoDAO = new MedicoDAO();
                        medico = medicoDAO.getMedico(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de medico - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("medico", medico);

            rd = request.getRequestDispatcher("/views/formMedico.jsp");
            rd.forward(request, response);

        } else {

            Medico medico = new Medico(nome_user, cpf_user, senha_user, crm, crm_estado,
                    Integer.parseInt(id_especialidade), autorizado);
            medico.setId(id);

            MedicoDAO medicoDAO = new MedicoDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        medicoDAO.Inserir(medico);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        medicoDAO.Alterar(medico);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        try {
                            medicoDAO.Excluir(medico);
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

                ArrayList<Medico> listaMedicos = medicoDAO.ListaDeMedicos();
                request.setAttribute("listaMedicos", listaMedicos);

                rd = request.getRequestDispatcher("/views/listaMedicos.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de medico - " + ex.getMessage());
            }
        }
    }
}
