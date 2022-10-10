<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>
        
    <jsp:include page="header.jsp" />

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-person me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="pacientes.jsp">Cadastrar Paciente</a>
            </span>
        </div>
        </article>
        
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-graph me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="especialidades.jsp">Cadastrar Especialidade M�dica</a>
            </span>
        </div>
        </article>
        
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-medical-cross me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="medicos.jsp">Cadastrar M�dico</a>
            </span>
        </div>
        </article>
        
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-book me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="planos_de_saude.jsp">Cadastrar Planos de sa�de</a>
            </span>
        </div>
        </article>
        
        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-people me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="administradores.jsp">Cadastrar Administradores</a>
            </span>
        </div>
        </article>
    </main>
</div>