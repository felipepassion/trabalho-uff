<%@page import="models.Especialidade"%>
<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>

        <jsp:include page="header.jsp" />
        <article>
            <%
                Especialidade especialidade = (Especialidade) request.getAttribute("especialidade");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Especialidade</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Especialidade</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Especialidade</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="especialidade" method="POST">
                <input type="hidden" name="id" value="<%=especialidade.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="descricao" class="form-label">Nome</label>
                    <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=especialidade.getDescricao()%>" class="form-control">
                </div>
                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="especialidade?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>