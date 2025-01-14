<%@page import="java.util.ArrayList"%>
<%@page import="models.Especialidade"%>
<%@page import="models.Medico"%>
<% 
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
                Medico medico = (Medico) request.getAttribute("medico");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Medico</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Medico</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Medico</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="medico" method="POST">
                <input type="hidden" name="id" value="<%=medico.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getNome()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getCpf()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getSenha()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="crm" class="form-label">CRM</label>
                    <input type="text" name="crm" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getCrm()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="crmestado" class="form-label">Estado do CRM</label>
                    <input type="text" name="crmestado" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getEstadoCrm()%>" class="form-control">
                </div>
                <%if (tipoConta == Enums.TipoConta.Adm) {%>
                <div class="mb-3">
                    <label for="autorizado" class="form-label">Autorizado?</label>
                    <input type="text" name="autorizado" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                           value="<%=medico.getAutorizado()%>" class="form-control">
                </div>
                <%} else { %>
                <input type="hidden" name="autorizado" value="<%=medico.getAutorizado()%>" class="form-control">
                <%}%>
                <select class="form-select d-block w-100 form-control" id="idespecialidade" name="idespecialidade" required>
                    <option value="">Escolha a Especialidade do M�dico.</option>
                    <%
                    ArrayList<Especialidade> listaDeEspecialidade = (ArrayList<Especialidade>) request.getAttribute("listaDeEspecialidade");
                        for (Especialidade especialidade : listaDeEspecialidade) {
                            String str = "";
                            if (medico != null && medico.getIdEspecialidade() == especialidade.getId()){
                                str = "selected";
                            }
                            out.println("<option "+str+" value= '"+especialidade.getId()+"'>");
                                out.println(especialidade.getDescricao());
                                out.println("</option>");
                            }
                    %>
                </select>
                <div class="btn-submit-container">
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="medico?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>