<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Liste des Emprunts</title>

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
                request.setAttribute("error", "Vous n'avez pas le droit d'accéder à cette page !");
                //TODO: rediriger dans la page .jsp et afficher les msg d'erreurs.
                    response.sendRedirect(request.getContextPath() + "/Connexion");
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
                    <li><a class="" href="${pageContext.request.contextPath}/BooksServlet">Gérer Livres</a></li>  
                    <li><a class="" href="${pageContext.request.contextPath}/listUserServlet">Gérer Utilisateurs</a></li>  
                    <li><a class="active" href="${pageContext.request.contextPath}/listEmpruntServlet">Gérer Emprunts</a></li>  
                    <li><a class="" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>       
                </ul>
            </nav>
        </div>

        <% }%>

        <div class="main">
            <section class="signup">
                <div class="container">

                    <!-- Search form -->
                    <!-- <form class="form-inline active-purple-4">
                      <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Rechercher un livre..."
                        aria-label="Search">
                      <i class="zmdi zmdi-search material-icons-name" aria-hidden="true"></i>
                    </form> -->

                    <!-- Collection d emprunt -->
                    <section>
                        <div class="signup-content">
                            <!-- row -->
                            <div class="row">
                                <c:forEach items= "${emprunts}" var = "elt">

                                    <div class="card-group">
                                        <div class="card">
                                            <img class="card-img-top" src="images/books/1.jpg" alt="Card image cap">
                                            <div class="card-body">
                                                <h5 style='overflow-wrap: break-word;' class="card-title">${elt.titreBook}</h5>
                                                <p class="card-text">${elt.auteurBook}</p>
                                                <p class="card-text"><small class="text-muted">${elt.prenomUser}</small></p>
                                                <p class="card-text"><small class="text-muted">${elt.nomUser}</small></p>
                                            </div>
                                            <div class="card-footer">
                                                <small class="text-success">${elt.dateEmprunt}</small>
                                                <form action="${pageContext.request.contextPath}/listEmpruntServlet" method="post">
                                                    <button type="submit" name="Retour" class="form-submit btn btn-success" value="${elt.idBook}">
                                                        <span class="zmdi zmdi-sign-in"></span>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div><!-- row -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">Précédent</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#">Suivant</a>
                                </li>
                            </ul>
                        </nav>
                    </section>
                    <!-- Collection d emprunt -->

                </div>
            </section>
        </div>

  <% }%>
</html>
