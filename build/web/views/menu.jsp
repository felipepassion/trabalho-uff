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
                <div class="nav-item px-3">
                    <a class="nav-link active" href="paciente" Match="NavLinkMatch.All">
                        <span class="oi oi-person" aria-hidden="true"></span> Pacientes
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="medico" Match="NavLinkMatch.All">
                        <span class="oi oi-medical-cross" aria-hidden="true"></span>  M�dicos
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="especialidade" Match="NavLinkMatch.All">
                        <span class="oi oi-graph" aria-hidden="true"></span> Especialidades
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="tipoPlano" Match="NavLinkMatch.All">
                        <span class="oi oi-book" aria-hidden="true"></span> Planos de Sa�de
                    </a>
                </div>
                <div class="nav-item px-3">
                    <a class="nav-link" href="usuarios" Match="NavLinkMatch.All">
                        <span class="oi oi-people" aria-hidden="true"></span> Criar Administradores
                    </a>
                </div>
            </nav>
        </div>
    </div>

</div>