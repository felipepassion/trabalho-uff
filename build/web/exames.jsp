<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <div class="col-sm-6 offset-3 mt-5">

        <h3>Dados do Exame</h3>
        <form action="enviarDados" method="POST">
            <div class="mb-3">
                <label for="idtipoexame" class="form-label">Tipo do Exame</label>
                <select  name="idtipoexame" class="form-control">
                    <option>CORAÇÃO</option>
                    <option>PULMÃO</option>
                    <option>ABDOME</option>
                    <option>CRÂNIO</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="idconsulta" class="form-label">Consulta</label>
                <select  name="idconsulta" class="form-control">
                    <option>Consulta Felipe com Dr House</option>
                </select>
            </div>
            <div>
                <a href="inicio.jsp"><input type="buton" value="Salvar" class="btn btn-primary"></a>
            </div>
        </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</div>