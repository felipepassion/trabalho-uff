<%@page import="models.TipoExame"%>
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
                TipoExame tipoExame = (TipoExame) request.getAttribute("tipoExame");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Exame</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Exame</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Exame</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="tipoExame" method="POST">
                <input type="hidden" name="id" value="<%=tipoExame.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="descricao" class="form-label">Nome</label>
                    <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=tipoExame.getDescricao()%>" class="form-control">
                </div>
                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="tipoExame?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>