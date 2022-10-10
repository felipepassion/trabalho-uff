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
            <span class="oi oi-person me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="novo_paciente.jsp">Novo Paciente</a>
            </span>
        </div>
        </article>
        
        <br/><br/><br/>
        
        <h3>Lista de Pacientes</h3>

        <table style="display:block" class="table">
           <thead>
               <tr>
                   <th>Nome</th>
                   <th>CPF</th>
                   <th>Plano de Saúde</th>
               </tr>
           </thead><!--!-->
           <tbody>
               <tr>
                   <td>Maria</td>
                   <td>937.397.160-37</td>
                   <td>Amil</td>
               </tr>
               <tr>
                   <td>Felipe Paixão da Silva</td>
                   <td>222.222.222-02</td>
                   <td>Golden Cross</td>
               </tr>
           </tbody>
       </table>

        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </div>
    
</div>