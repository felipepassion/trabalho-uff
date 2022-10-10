<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <div class="col-sm-6 offset-3 mt-5">

        <h3>Dados do Paciente</h3>
        <form action="enviarDados" method="POST">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" name="nome" class="form-control">
            </div>
            <div class="mb-3">
                <label for="endereco" class="form-label">CPF</label>
                <input type="text" name="cpf" class="form-control">
            </div>
            <div class="mb-3">
                <label for="idtipoplano" class="form-label">Tipo do Plano de Saúde</label>
                <select  name="idtipoplano" class="form-control">
                    <option>Amil</option>
                    <option>Golden Cross</option>
                    <option>Bradesco Saúde</option>
                </select>
            </div>
            <div>
                <a href="inicio.jsp"><input type="buton" value="Salvar" class="btn btn-primary"></a>
            </div>
        </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</div>