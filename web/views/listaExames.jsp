<%@page import="java.util.ArrayList"%>
<%@page import="models.Exame"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<head>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/site.css" rel="stylesheet" type="text/css"/>
    <link href="bootstrap/ionic.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<div class="page">
    <jsp:include page="menu.jsp" />
    <main>

        <jsp:include page="header.jsp" />

        <article class="content px-4">
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Exames</h2>

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
        if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>

                <a href="exame?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Tipo do Exame</th>
                                <th scope="col">Consulta</th>
                                <th scope="col">Exame Realizado?</th>
                                <th scope="col">Açoes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Exame> listaExames = (ArrayList<Exame>) request.getAttribute("listaExames");

                                for (Exame exame : listaExames) {
                                    out.println("<tr>");
                                    out.println("<th>" + exame.getId() + "</th>");
                                    out.println("<td>" + exame.getExame() + "</td>");
                                    out.println("<td>" + exame.getConsulta() + "</td>");%>
                        <td><a href="exame?acao=Alterar&id=<%=exame.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="exame?acao=Excluir&id=<%=exame.getId()%>" class="btn btn-danger">Excluir</a></td>
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </article>
    </main>
</div>