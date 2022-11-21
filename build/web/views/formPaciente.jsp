<%@page import="java.util.ArrayList"%>
<%@page import="models.TipoPlano"%>
<%@page import="models.Paciente"%>

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
                Paciente paciente = (Paciente) request.getAttribute("paciente");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Paciente</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Paciente</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Paciente</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="paciente" method="POST">
                <input type="hidden" name="id" value="<%=paciente.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                        value="<%=paciente.getNome()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                        value="<%=paciente.getCpf()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%>
                        value="<%=paciente.getSenha()%>" class="form-control">
                </div>
                <select class="form-select d-block w-100 form-control" id="idtipoplano" name="idtipoplano" required>
                    <option value="">Escolha o plano.</option>
                    <%
                    ArrayList<TipoPlano> listaDePlanos = (ArrayList<TipoPlano>) request.getAttribute("listaDePlanos");
                        for (TipoPlano tipoPlano : listaDePlanos) {
                            String str = "";
                            if (paciente != null && paciente.getIdTipoPlano() == tipoPlano.getId()){
                                str = "selected";
                            }
                            out.println("<option "+str+" value= '"+tipoPlano.getId()+"'>");
                                out.println(tipoPlano.getDescricao());
                                out.println("</option>");
                            }
                            %>
                </select>
                <div class="btn-submit-container">
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="paciente?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>