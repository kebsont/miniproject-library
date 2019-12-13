<%-- 
    Document   : index
    Created on : 6 déc. 2019, 16:03:13
    Author     : kebson
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Livre recherche</title>

        <jsp:include page="header.jsp"/>
        <style>
            .container{
                width: 1058px;
            }
            #thenav{
                background-color: #e58e26;
                height: 50px;
                width: auto;
            }
            .thenav{
                display: block;
                color: white;

            }
            ul{
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;

            }
            li{
                float: right;
            }
            li a{
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;

            }
            li hover:not(.active){
                background-color: #0c2461;
            }
            p{
                text-align: center;
            }
            h5{
                text-align: center;
            }
            card-img{
                width:150px;
                margin-right: center;
                margin-left: center;
            }
            card{
                width: 300px;
            }

        </style>
    </head>
    <body>
        <%

            // Verifier si le user a les droits
            String prenom = null;
            String nom = null;
            String profil = null;

            if (session.getAttribute("prenomFromSession") == null) {
                // redirect to connexion page
                System.out.println("Ton username session est vide");
                response.sendRedirect(request.getContextPath() + "/Connexion");

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

        <% }%>

        <div class="main">
            <section class="signup">
                <div class="container">

                    <section>
                        <div class="signup-content">
                            <!-- row -->
                            <div class="row">
                                <c:forEach items= "${livres}" var = "elt">

                                    <div class="card-group">
                                        <div class="card">
                                            <img class="card-img-top" src="images/books/1.jpg" alt="Card image cap">
                                            <div class="card-body">
                                                <h5 style='overflow-wrap: break-word;' class="card-title">${elt.titre}</h5>
                                                <p class="card-text">${elt.auteur}</p>
                                                <p class="card-text"><small class="text-muted">${elt.dateParution}</small></p>
                                            </div>
                                            <div class="card-footer">
                                                <small class="text-success">Disponible</small>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div><!-- row -->

                    </section>
                    <!-- Collection de livres -->

                </div>
            </section>
        </div>

 <% }%>
</html>
