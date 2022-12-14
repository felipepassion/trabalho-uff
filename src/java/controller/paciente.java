package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.PacienteDAO;
import DAO.TipoPlanoDAO;
import models.Paciente;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import models.TipoPlano;
import models.Usuario;

@WebServlet(name = "paciente", urlPatterns = { "/paciente" })
public class paciente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        Paciente paciente = new Paciente();
        PacienteDAO pacienteDAO = new PacienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Paciente> listaPacientes = pacienteDAO.ListaDePacientes();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaPacientes", listaPacientes);
                    rd = request.getRequestDispatcher("/views/listaPacientes.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar pacientes (Paciente) - " + ex.getMessage());
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.getPaciente(id);
                    paciente.setId(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de paciente");
                }
                break;

        }

        TipoPlanoDAO tipoPlanoDAO = new TipoPlanoDAO();
        ArrayList<TipoPlano> listaDePlanos = tipoPlanoDAO.ListaDeTipoPlanos();
        request.setAttribute("listaDePlanos", listaDePlanos);

        request.setAttribute("paciente", paciente);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/views/formPaciente.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String id_tipo_plano = request.getParameter("idtipoplano");
        String autorizado = request.getParameter("autorizado");

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || senha_user.isEmpty() || senha_user.isEmpty() || autorizado.isEmpty()
                || id_tipo_plano.isEmpty()) {

            Paciente paciente = new Paciente();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        PacienteDAO pacienteDAO = new PacienteDAO();
                        paciente = pacienteDAO.getPaciente(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de paciente - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("paciente", paciente);

            rd = request.getRequestDispatcher("/views/formPaciente.jsp");
            rd.forward(request, response);

        } else {

            Paciente paciente = new Paciente(nome_user, cpf_user, senha_user, Integer.parseInt(id_tipo_plano), autorizado);
            paciente.setId(id);

            PacienteDAO pacienteDAO = new PacienteDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        pacienteDAO.Inserir(paciente);
                        HttpSession session = request.getSession();
                        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                        if(usuarioLogado == null){
                            Usuario usuarioObtido = pacienteDAO.Logar(new Paciente(cpf_user, senha_user));
                            session.setAttribute("usuario", usuarioObtido);
                            rd = request.getRequestDispatcher("/");
                            rd.forward(request, response);
                        }
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        pacienteDAO.Alterar(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        pacienteDAO.Excluir(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Paciente> listaPacientes = pacienteDAO.ListaDePacientes();
                request.setAttribute("listaPacientes", listaPacientes);

                rd = request.getRequestDispatcher("/views/listaPacientes.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de paciente - " + ex.getMessage());
            }
        }
    }
}
