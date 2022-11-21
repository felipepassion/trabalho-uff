<%@page import="java.util.ArrayList"%>
<%@page import="models.Paciente"%>
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
                <h2>Lista de Usuários</h2>

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
        if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>

                <a href="paciente?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Açoes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) request.getAttribute("listaPacientes");

                                for (Paciente paciente : listaPacientes) {
                                    out.println("<tr>");
                                    out.println("<th>" + paciente.getId() + "</th>");
                                    out.println("<td>" + paciente.getNome() + "</td>");
                                    out.println("<td>" + paciente.getCpf() + "</td>");%>
                        <td><a href="paciente?acao=Alterar&id=<%=paciente.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="paciente?acao=Excluir&id=<%=paciente.getId()%>" class="btn btn-danger">Excluir</a></td>
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