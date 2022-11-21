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
                <label for="pacienteid" class="form-label">Paciente</label>
                <select  name="pacienteid" class="form-control">
                    <option>Felipe Paixão</option>
                    <option>Márcio Silva</option>
                    <option>Aline Barros</option>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="medicoid" class="form-label">Médico</label>
                <select  name="medicoid" class="form-control">
                    <option>Dr House</option>
                    <option>Dr Iron Man</option>
                    <option>The Good Doctor</option>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="data" class="form-label">Data e Hora</label>
                <input type="datetime" name="cpf" class="form-control">
            </div>
            
            <div>
                <a href="inicio.jsp"><input type="buton" value="Salvar" class="btn btn-primary"></a>
            </div>
        </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</div>