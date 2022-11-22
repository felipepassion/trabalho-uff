<%@page import="models.Consulta"%>
<%@page import="models.Consulta"%>
<%@page import="models.TipoExame"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Exame"%>

<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css" />
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css" />
</head>

<div class="page">
    <jsp:include page="menu.jsp" />
    <main>

        <jsp:include page="header.jsp" />

        <article>
            <%
                Exame exame = (Exame) request.getAttribute("exame");
                String acao = (String) request.getAttribute("acao");
                if(acao == null || acao == "")
                    acao = "Incluir";
                switch (acao) {
                    case "Incluir":
                        out.println("<h1>Incluir Exame</h1>");
                        break;
                    case "Alterar":
                        out.println("<h1>Alterar Exame</h1>");
                        break;
                    case "Excluir":
                        out.println("<h1>Excluir Exame</h1>");
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
                if ((msgError != null) && (!msgError.isEmpty())) {%>
            <div class="alert alert-danger" role="alert">
                <%= msgError%>
            </div>
            <% }%>

            <form action="exame" method="POST">
                <input type="hidden" name="id" value="<%=exame.getId()%>" class="form-control">

                <div class="mb-3">
                    <label for="data" class="form-label">Tipo do Exame</label>
                    <select class="form-select d-block w-100 form-control" id="idtipoExame" name="idtipoExame" required>
                        <option value="">Escolha o TipoExame</option>
                        <%
                    ArrayList<TipoExame> listaDeTipoExame = (ArrayList<TipoExame>) request.getAttribute("listaDeTipoExames");
                        for (TipoExame tipoExame : listaDeTipoExame) {
                            String str = "";
                            if (exame != null && exame.getIdTipoExame() == tipoExame.getId()){
                                str = "selected";
                            }
                            out.println("<option "+str+" value= '"+tipoExame.getId()+"'>");
                                out.println(tipoExame.getDescricao());
                                out.println("</option>");
                            }
                            %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="data" class="form-label">Consulta</label>
                    <select class="form-select d-block w-100 form-control" id="idconsulta" name="idconsulta" required>
                        <option value="">Escolha a Consulta</option>
                        <%
                    ArrayList<Consulta> listaDeConsulta = (ArrayList<Consulta>) request.getAttribute("listaDeConsultas");
                        for (Consulta consulta : listaDeConsulta) {
                            String str = "";
                            if (exame != null && exame.getId() == consulta.getId()){
                                str = "selected";
                            }
                            out.println("<option "+str+" value= '"+consulta.getId()+"'>");
                                out.println(consulta.getDescricao());
                                out.println("</option>");
                            }
                            %>
                    </select>
                </div>

                <div>
                    <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                    <a href="exame?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </article>
    </main>
</div>