<%@page import="models.Usuario"%>
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
                Usuario usuario = (Usuario) request.getAttribute("usuario");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Usuário</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Usuário</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Usuário</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="usuario" method="POST">
                <input type="hidden" name="id" value="<%=usuario.getId()%>" class="form-control">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getNome()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label" >CPF</label>
                    <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getCpf()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label" >endereço</label>
                    <input type="text" name="endereco" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getEndereco()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label" >Senha</label>
                    <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getSenha()%>" class="form-control">
                </div>
                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="usuario?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>