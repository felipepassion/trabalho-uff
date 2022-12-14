package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.EspecialidadeDAO;
import models.Especialidade;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "especialidade", urlPatterns = { "/especialidade" })
public class especialidade extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        if (acao == null || acao == "") {
            acao = "Listar";
        }

        Especialidade especialidade = new Especialidade();
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Especialidade> listaEspecialidades = especialidadeDAO.ListaDeEspecialidades();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaEspecialidades", listaEspecialidades);
                    rd = request.getRequestDispatcher("/views/listaEspecialidades.jsp");
                    rd.forward(request, response);
                    return;
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar especialidades (Especialidade) - " + ex.getMessage());
                }
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    especialidade = especialidadeDAO.getEspecialidade(id);
                    especialidade.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de especialidade");
                }
                break;

        }
        request.setAttribute("especialidade", especialidade);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        ArrayList<Especialidade> listaDePlanos = especialidadeDAO.ListaDeEspecialidades();
        request.setAttribute("listaDePlanos", listaDePlanos);

        rd = request.getRequestDispatcher("/views/formEspecialidade.jsp");
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

            Especialidade especialidade = new Especialidade();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
                        especialidade = especialidadeDAO.getEspecialidade(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de especialidade - " + ex.getMessage());
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("especialidade", especialidade);

            rd = request.getRequestDispatcher("/views/formEspecialidade.jsp");
            rd.forward(request, response);

        } else {

            Especialidade especialidade = new Especialidade(descricao_user);
            especialidade.setId(id);

            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        especialidadeDAO.Inserir(especialidade);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        especialidadeDAO.Alterar(especialidade);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        try {
                            especialidadeDAO.Excluir(especialidade);
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

                ArrayList<Especialidade> listaEspecialidades = especialidadeDAO.ListaDeEspecialidades();
                request.setAttribute("listaEspecialidades", listaEspecialidades);

                rd = request.getRequestDispatcher("/views/listaEspecialidades.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de especialidade - " + ex.getMessage());
            }
        }
    }
}
