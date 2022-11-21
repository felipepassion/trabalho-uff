<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <div class="col-sm-6 offset-1 mt-5">

        <h3>Dados do Médico</h3>
        <form action="enviarDados" method="POST">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" name="nome" class="form-control">
            </div>
            <div class="mb-3">
                <label for="cpf" class="form-label">CPF</label>
                <input type="text" name="cpf" class="form-control">
            </div>
            <div class="mb-3">
                <label for="crm" class="form-label">CRM</label>
                <input type="text" name="crm" class="form-control">
            </div>
            <div class="mb-3">
                <label for="autorizado" class="form-label">Autorizado</label>
                <input type="text" name="autorizado" class="form-control">
            </div>
            <div class="mb-3">
                <label for="crm" class="form-label">Estado CRM</label>
                <select  name="tipo-especialidade" class="form-control">
                    <option>RJ</option>
                    <option>SP</option>
                    <option>MG</option>
                    <option>PR</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" name="senha" class="form-control">
            </div>
            <div class="mb-3">
                <label for="idespecialidade" class="form-label">Tipo de Especialidade</label>
                <select  name="idespecialidade" class="form-control">
                    <option>CARDIOLOGIA</option>
                    <option>NEUROLOGIA</option>
                    <option>GASTROLOGISTA</option>
                    <option>PNEUMOLOGIA</option>
                </select>
            </div>
            <div>
                <a href="inicio.jsp"><input type="buton" value="Salvar" class="btn btn-primary"></a>
            </div>
        </form>
    </div>

    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    
    
</div>