<%@page import="models.Usuario"%>
<div class="top-row px-4 auth">
    <%
        // testar se est� logado
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
            if (usuarioLogado != null) { %>
    <a href="#">Ol�, <%= usuarioLogado.getNome() %>!</a>
    <a href="#" target="_blank">Sobre</a>
    <a href="/login?acao=Logout" target="_blank">Logout</a>
    <%  } else { %>
    <a href="#">Acesso P�blico</a>
    <a href="#" target="_blank">Sobre</a>
    <a href="/login" target="_blank">Login</a>

    <%    }
        }%>

</div>