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
            <span class="oi oi-medical-cross me-2" aria-hidden="true"></span>
            <span class="text-nowrap">
                <a href="novo_medico.jsp">Cadastrar Médico</a>
            </span>
        </div>
        </article>
        
        <br/><br/><br/>
        
        <h3>Lista de Médicos</h3>

        <table style="display:block" class="table">
           <thead>
               <tr>
                   <th>Nome</th>
                   <th>CPF</th>
                   <th>CRM</th>
                   <th>Estado CRM</th>
                   <th>Autorizado</th>
                   <th>Especialidade</th>
               </tr>
           </thead><!--!-->
           <tbody>
               <tr>
                   <td>Dr. House</td>
                   <td>111.111.111-01</td>
                   <td>11111111111</td>
                   <td>RJ</td>
                   <td>S</td>
                   <td>CARDIOLOGIA</td>
               </tr>
               <tr>
                   <td>Marcos</td>
                   <td>381.585.150-53</td>
                   <td>1234</td>
                   <td>SP</td>
                   <td>N</td>
                   <td>PNEUMOLOGIA</td>
               </tr>
           </tbody>
       </table>

        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </div>
    
</div>