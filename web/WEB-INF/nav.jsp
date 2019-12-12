<div id="thenav">
    <nav class="thenav">
        <%
            String userName = null;
            String nom = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                 for (Cookie cookie : cookies) {
                System.out.println("le cookie: " + cookies);
                if (cookie.getName().equals("user")) {
                    //nom = cookies[2].getValue();
                    System.out.println("cookie: " + userName);
                } else {
                    System.out.println("YAA RIEENEN ICI");
                }
                 }
            }
            if (userName == null) {  %>
        <ul>
            <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>
            <li><a href="#">Réservation</a></li>
            <li><a href="#">Compte</a></li></ul>
                <% } else {%>

        <ul>
            <li class="dropdown ml-auto">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= userName %></a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a href="#" class="dropdown-item">Mon Compte</a>
                    <div class="dropdown-divider"></div>
                    <a href="${pageContext.request.contextPath}/BooksServlet" class="dropdown-item">Se déconnecter</a>
                </div>
            </li>
            <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>
            <li><a href="#">Réservation</a></li>
            <li><a href="#">Compte</a></li>
                <% }%>
        </ul>
    </nav>

</div>