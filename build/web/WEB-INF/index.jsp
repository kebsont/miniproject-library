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
        <title>Bibliotheque Doudoux</title>

        <jsp:include page="header.jsp"/>
        <style>
            .container{
                width: 1030px;
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
            #wrapper {
                display: flex;
                justify-content: center;
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
        <div class="main">
            <section class="signup">
                <div class="container">


                    <form method="POST" class="register-form" id="register-form">
                        <div class="form-group col-md-5">
                            <input type="text" placeholder="Rechercher un livre..." name="fctSearch">
                            <label for="name"><i class="zmdi zmdi-search material-icons-name"></i></label>
                        </div>
                    </form>


                    <!-- Collection de livres -->
                    <section>
                        <div class="signup-content">
                            <!-- row -->
                            <div class="row">
                                <c:forEach items= "${livres}" var = "elt">

                                    <div class="card-group">
                                        <div class="card">
                                            <img class="card-img-top" src="images/books/${elt.id}.jpg" alt="Card image cap">
                                            <div class="card-body">
                                                <h5 style='overflow-wrap: break-word;' class="card-title">${elt.titre}</h5>
                                                <p class="card-text">${elt.auteur}</p>
                                                <p class="card-text"><small class="text-muted">${elt.dateParution}</small></p>
                                            </div>
                                            <div class="card-footer">
                                                <form action="${pageContext.request.contextPath}/BooksServlet" method="post">
                                                    <div class="row">


                                                        <c:if test="${elt.disponibilite==1}">
                                                            <button type="submit" name="Emprunter" class="form-submit btn btn-success" value="${elt.id}">
                                                                <span class="zmdi zmdi-shopping-basket"></span>
                                                            </button>
                                                        </c:if> 
                                                    </div>
                                                </form>
                                                <c:if test="${elt.disponibilite==0}">
                                                    <small class="text-danger">Indisponible</small>
                                                </c:if>
                                                <c:if test="${elt.disponibilite==1}">
                                                    <small class="text-success">Disponible</small>
                                                </c:if>
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
                    <!-- Collection de livres -->

                </div>
            </section>
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
                    <li><a class="active" href="${pageContext.request.contextPath}/listEmpruntServlet">Gérer Emprunts</a></li>  
                    <li><a class="active" href="${pageContext.request.contextPath}/BooksServlet">Accueil</a></li>       
                </ul>
            </nav>
        </div>

        <% }%>
        <div class="main">
            <section class="signup">
                <div class="container">


                    <form method="POST" class="register-form" id="register-form">
                        <div class="form-group col-md-5">
                            <input type="text" placeholder="Rechercher un livre..." name="fctSearch">
                            <label for="name"><i class="zmdi zmdi-search material-icons-name"></i></label>
                        </div>
                    </form>
                    <form action="${pageContext.request.contextPath}/BooksServlet" method="post">
                        <div class="row">
                            <button type="submit" name="Ajouter" class="form-submit btn btn-success" value="Ajout">Ajouter un livre
                            </button>
                        </div>
                    </form>

                    <!-- Collection de livres -->
                    <section>
                        <div class="signup-content">
                            <!-- row -->
                            <div class="row">
                                <c:forEach items= "${livres}" var = "elt">

                                    <div class="card-group">
                                        <div class="card">
                                            <img class="card-img-top" src="images/books/${elt.id}.jpg" alt="Card image cap">
                                            <div class="card-body">
                                                <h5 style='overflow-wrap: break-word;' class="card-title">${elt.titre}</h5>
                                                <p class="card-text">${elt.auteur}</p>
                                                <p class="card-text"><small class="text-muted">${elt.dateParution}</small></p>
                                            </div>
                                            <div class="card-footer">
                                                <form action="${pageContext.request.contextPath}/BooksServlet" method="post">
                                                    <div id="wrapper">

                                                        <button type="submit" name="Modifier" class="form-submit btn btn-primary" value="${elt.id}">
                                                            <span class="zmdi zmdi-border-color"></span>
                                                        </button> &nbsp;
                                                        <button type="submit" name="Supprimer" class="form-submit btn btn-danger" value="${elt.id}">
                                                            <span class="zmdi zmdi-delete"></span>
                                                        </button>&nbsp;
                                                        <c:if test="${elt.disponibilite==1}">
                                                            <button type="submit" name="Emprunter" class="form-submit btn btn-success" value="${elt.id}">
                                                                <span class="zmdi zmdi-shopping-basket"></span>
                                                            </button>
                                                        </c:if> 
                                                    </div>
                                                </form>
                                                <c:if test="${elt.disponibilite==0}">
                                                    <small class="text-danger">Indisponible</small>
                                                </c:if>
                                                <c:if test="${elt.disponibilite==1}">
                                                    <small class="text-success">Disponible</small>
                                                </c:if>
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
                    <!-- Collection de livres -->

                </div>
            </section>
        </div>
        <% }%>
</html>
