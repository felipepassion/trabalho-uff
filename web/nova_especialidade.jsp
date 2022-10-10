<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <div class="col-sm-6 offset-3 mt-5">

        <h3>Dados da Especialidade</h3>
        <form action="enviarDados" method="POST">
            <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" name="descricao" class="form-control">
            </div>
            <div>
                <a href="inicio.jsp"><input type="buton" value="Salvar" class="btn btn-primary"></a>
            </div>
        </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</div>