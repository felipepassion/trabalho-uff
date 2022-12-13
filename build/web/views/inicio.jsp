<%@page import="models.Usuario"%>


<%
    String tipoConta = (String) session.getAttribute("tipoUsuario");
    Usuario usuarioLogado = null;
// testar se estï¿½ logado
    HttpSession sessao = request.getSession(false);
    if (sessao != null) {
        usuarioLogado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogado != null) { %>

<%  } else { %>


<%}
    }%>

<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>
        <jsp:include page="header.jsp" />
            
        
        
        <%if (usuarioLogado == null) {%>

        <<article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-person me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="login">Login de Pacientes</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-person me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="login">Login de Médicos</a>
                    <p>Cadastrar outros ADMs, Médicos, Pacientes, Planos de Saúde e Especialidades Médicos</p>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-person me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="login">Login de Administradores</a>
                    <p>Cadastrar outros ADMs, Médicos, Pacientes, Planos de Saúde e Especialidades Médicos</p>
                </span>
            </div>
        </article>
        <% } %>

        <%if (tipoConta == Enums.TipoConta.Medico || tipoConta == Enums.TipoConta.Paciente) {%>
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="consulta?acao=Incluir">Marcar Consulta</a>
                </span>
            </div>
        </article>
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="consulta">Consultas realizadas</a>
                </span>
            </div>
        </article>
        <% } %>
        <%if (tipoConta == Enums.TipoConta.Medico) {%>
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="exame">Solicitar Exame</a>
                </span>
            </div>
        </article>
        <% } %>
        
        <%if (tipoConta == Enums.TipoConta.Adm) {%>


        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-graph me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="especialidade">Especialidades Médicas</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-medical-cross me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="medico">Médicos</a>
                </span>
            </div>
        </article>

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
                <span class="oi oi-book me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="tipoPlano">Planos de súde</a>
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
                <span class="oi oi-people me-2" aria-hidden="true"></span>
                <span class="text-nowrap">
                    <a href="usuario">Administradores</a>
                </span>
            </div>
        </article>
        <%}%> 

    </main>
</div>