<%@page import="java.util.ArrayList"%>
<%@page import="models.TipoPlano"%>
<%@page import="models.Medico"%>

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
                <select class="form-select d-block w-100 form-control" id="idtipoplano" name="idtipoplano" required>
                    <option value="">Escolha o plano.</option>
                    <%
                    ArrayList<TipoPlano> listaDePlanos = (ArrayList<TipoPlano>) request.getAttribute("listaDePlanos");
                        for (TipoPlano tipoPlano : listaDePlanos) {
                            out.println("<option value= '"+tipoPlano.getId()+"'>");
                                out.println(tipoPlano.getDescricao());
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