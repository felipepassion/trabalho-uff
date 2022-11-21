<%@page import="models.TipoPlano"%>
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
                TipoPlano tipoPlano = (TipoPlano) request.getAttribute("tipoPlano");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Plano</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Plano</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Plano</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="tipoPlano" method="POST">
                <input type="hidden" name="id" value="<%=tipoPlano.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="descricao" class="form-label">Nome</label>
                    <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=tipoPlano.getDescricao()%>" class="form-control">
                </div>
                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="tipoPlano?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>