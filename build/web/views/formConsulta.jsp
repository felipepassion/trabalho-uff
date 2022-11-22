<%@page import="models.Medico"%>
<%@page import="models.Medico"%>
<%@page import="models.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Consulta"%>
<%
    HttpSession sessao = request.getSession(false);
    String tipoConta = (String) session.getAttribute("tipoUsuario");
%>
<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css" />
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>

        <jsp:include page="header.jsp" />
        <article>
            <%
                Consulta consulta = (Consulta) request.getAttribute("consulta");
                String acao = (String) request.getAttribute("acao");
                if (acao == null || acao
                        == "") {
                    acao = "Incluir";
                }
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Consulta</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Consulta</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Consulta</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError
                        != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="consulta" method="POST">
                <input type="hidden" name="id" value="<%=consulta.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=consulta.getDescricao()%>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="data" class="form-label">Data</label>
                    <input type="datetime-local" name="data" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=consulta.getData()%>" class="form-control">
                </div>

                <%if (tipoConta == Enums.TipoConta.Medico || tipoConta == Enums.TipoConta.Adm) {%>
                <div class="mb-3">
                    <label for="data" class="form-label">Consulta Realizada?</label>
                    <select class="form-select d-block w-100 form-control" id="realizada" name="realizada" required>
                        <option value="S">Sim</option>
                        <option value="N">Não</option>
                    </select>
                </div>
                <%}%> 
                <div class="mb-3">
                    <label for="data" class="form-label">Paciente</label>
                    <select class="form-select d-block w-100 form-control" id="idpaciente" name="idpaciente" required>
                        <option value="">Escolha o Paciente</option>
                        <%
                            ArrayList<Paciente> listaDePaciente = (ArrayList<Paciente>) request.getAttribute("listaDePacientes");
                            for (Paciente paciente : listaDePaciente) {
                                String str = "";
                                if (consulta != null && consulta.getIdPaciente() == paciente.getId()) {
                                    str = "selected";
                                }
                                out.println("<option " + str + " value= '" + paciente.getId() + "'>");
                                out.println(paciente.getNome());
                                out.println("</option>");
                            }
                        %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="data" class="form-label">Médico</label>
                    <select class="form-select d-block w-100 form-control" id="idmedico" name="idmedico" required>
                        <option value="">Escolha o Médico</option>
                        <%
                            ArrayList<Medico> listaDeMedico = (ArrayList<Medico>) request.getAttribute("listaDeMedicos");
                            for (Medico medico : listaDeMedico) {
                                String str = "";
                                if (consulta != null && consulta.getIdMedico() == medico.getId()) {
                                    str = "selected";
                                }
                                out.println("<option " + str + " value= '" + medico.getId() + "'>");
                                out.println(medico.getNome());
                                out.println("</option>");
                            }
                        %>
                    </select>
                </div>

                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="consulta?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>