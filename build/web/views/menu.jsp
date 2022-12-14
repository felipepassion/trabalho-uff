<%@page import="models.Usuario"%>

<%
    HttpSession sessao = request.getSession(false);
    String tipoConta = (String) session.getAttribute("tipoUsuario");
    Usuario usuarioLogado = null;
// testar se estï¿½ logado
    if (sessao != null) {
        usuarioLogado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogado != null) { %>

<%  } else { %>


<%}
    }%>

<div class="page">

    <div class="sidebar">
        <div class="top-row ps-3 navbar navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="">Trabalho UFF</a>
                <button title="Navigation menu" class="navbar-toggler" @onclick="ToggleNavMenu">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>

        <div class="">
            <nav class="flex-column">
                <div class="nav-item px-3">
                    <a class="nav-link active" href="/" Match="NavLinkMatch.All">
                        <span class="oi oi-home" aria-hidden="true"></span> Painel Inicial
                    </a>
                </div>
                <%if (usuarioLogado == null) {%>


                <div class="nav-item px-3">
                    <a class="nav-link active" href="paciente" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Login de Pacientes
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link active" href="paciente" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Login de Médicos
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link active" href="login" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Login de Administradores
                    </a>
                </div>
                <% } %>

                <%if (usuarioLogado != null) {%>

                <%if (tipoConta == Enums.TipoConta.Adm) {%>
                <div class="nav-item px-3">
                    <a class="nav-link" href="medico" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Médicos
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="paciente" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Pacientes
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="especialidade" Match="NavLinkMatch.All">
                        <span class="oi oi-graph" aria-hidden="true"></span> Especialidades Médicas
                    </a>
                </div>

                <div class="nav-item px-3">
                    <a class="nav-link active" href="tipoPlano" Match="NavLinkMatch.All">
                        <span class="oi oi-book" aria-hidden="true"></span> Planos de Saúde
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="tipoExame" Match="NavLinkMatch.All">
                        <span class="oi oi-book" aria-hidden="true"></span>  Tipos de Exame
                    </a>
                </div>

                <div class="nav-item px-3">
                    <a class="nav-link" href="usuario" Match="NavLinkMatch.All">
                        <span class="oi oi-people" aria-hidden="true"></span> Criar Administradores
                    </a>
                </div>
                <% } %>

                <div class="nav-item px-3">
                    <a class="nav-link" href="consulta" Match="NavLinkMatch.All">
                        <span class="oi oi-medical-cross" aria-hidden="true"></span> Consultas realizadas
                    </a>
                </div>
                
                <%if (tipoConta == Enums.TipoConta.Paciente || tipoConta == Enums.TipoConta.Medico) {%>
                 
                <div class="nav-item px-3">
                    <a class="nav-link" href="consulta?acao=Incluir" Match="NavLinkMatch.All">
                        <span class="oi oi-medical-cross" aria-hidden="true"></span> Marcar Consulta
                    </a>
                </div>  

                <%if (tipoConta == Enums.TipoConta.Medico) {%>
                <div class="nav-item px-3">
                    <a class="nav-link" href="exame" Match="NavLinkMatch.All">
                        <span class="oi oi-medical-cross" aria-hidden="true"></span> Solicitar Exame
                    </a>
                </div>
                <% } %>

                <%}%> 
                <%}%> 

            </nav>
        </div>
    </div>

</div>