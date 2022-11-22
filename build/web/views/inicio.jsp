<%@page import="models.Usuario"%>


<%
    String tipoConta = (String)session.getAttribute("tipoUsuario");
    Usuario usuarioLogado = null;
// testar se est� logado
    HttpSession sessao = request.getSession(false);
    if (sessao != null) {
        usuarioLogado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogado != null) { %>

<%  } else { %>


<%}}%>

<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>

        <jsp:include page="header.jsp" />
<h1><%= tipoConta %></h1>
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-person me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="paciente">Pacientes</a>
                </span>
            </div>
        </article>

        <%if (usuarioLogado != null) {%>
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-graph me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="tipoPlano">Planos de Sa�de</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-graph me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="especialidade">Especialidades M�dicas</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-medical-cross me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="medico">M�dicos</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="tipoPlano">Planos de sa�de</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="tipoExame">Tipos de Exame</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="exame">Marcar Exame</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="consulta">Marcar Consulta</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-people me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="usuario">Administradores</a>
                </span>
            </div>
        </article>
        <%}%> 
    </main>
</div>