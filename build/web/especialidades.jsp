<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <div class="col-sm-6 offset-1 mt-5">

        <article class="content px-4">
            <div class="alert alert-secondary mt-4">
            <span class="oi oi-graph me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="nova_especialidade.jsp">Cadastrar Especialidade</a>
            </span>
        </div>
        </article>
        
        <br/><br/><br/>
        
        <h3>Lista de Médicos</h3>

        <table style="display:block" class="table">
           <thead>
               <tr>
                   <th>Descrição</th>
               </tr>
           </thead><!--!-->
           <tbody>
               <tr>
                   <td>CARDIOLOGIA</td>
               </tr>
               <tr>
                   <td>NEUROLOGIA</td>
               </tr>
               <tr>
                   <td>GASTROLOGISTA</td>
               </tr>
               <tr>
                   <td>PNEUMOLOGIA</td>
               </tr>
           </tbody>
       </table>

        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </div>
    
</div>