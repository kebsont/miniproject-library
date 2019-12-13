<div>
    <%

            // Verifier si le user a les droits
            String prenom = null;
            String nom = null;
            String profil = null;

            if (session.getAttribute("prenomFromSession") == null) {
                // redirect to connexion page
                System.out.println("Ton username session est vide");

               response.sendRedirect("/WEB-INF/connexion.jsp");


            } else if (session.getAttribute("prenomFromSession") != null) {
                System.out.println("Ton username session n'est pas vide");
                prenom = (String) session.getAttribute("prenomFromSession");
                nom = (String) session.getAttribute("nomFromSession");
                profil = (String) session.getAttribute("profilFromSession");

                if (profil.equals("User")) {
                    // menu pour agent
        %>
        <div id = "thenav" > 
            <nav class ="thenav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Deconnexion"><i class="zmdi zmdi-power material-icons-name"></i></a></li>
                    <li><a href="${pageContext.request.contextPath}/modifUserServlet"><%= nom + " " + prenom%></a></li>
                    <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>       
                </ul>
            </nav>
        </div>
        <% } else if (profil.equals("Agent")) {
        %>
        <div id = "thenav" > 
            <nav class ="thenav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Deconnexion"><i class="zmdi zmdi-power material-icons-name"></i></a></li>
                    <li><a href="${pageContext.request.contextPath}/modifUserServlet"><%= nom + " " + prenom%></a></li>
                    <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Gérer Livres</a></li>  
                    <li><a class="active" href="${pageContext.request.contextPath}/listUserServlet">Gérer Utilisateurs</a></li>  
                    <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Gérer Emprunts</a></li>  
                    <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>       
                </ul>
            </nav>
        </div>

        <% }}%>

</div>