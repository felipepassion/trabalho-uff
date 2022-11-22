<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Dados do Usuário</title>
        <link href="bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <div class="col-sm-6 offset-3 mt-5">
                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <h3>Login no Sistema</h3>
                <form action="login" method="POST">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Usuário</label>
                        <input required type="text" name="login" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="endereco" class="form-label">Senha</label>
                        <input required type="text" name="password" class="form-control">
                    </div>
                    <div>
                        <button type="submit" value="Login" class="btn btn-primary">Login</button>
                        <a style="float:right" href="/paciente?acao=Inserir" target="_blank">Cadastrar</a>
                    </div>
                </form>
            </div> 
        </div>
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

