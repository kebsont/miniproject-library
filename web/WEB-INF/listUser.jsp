<%-- 
    Document   : listUser
    Created on : 10 déc. 2019, 21:59:49
    Author     : quent
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Liste Utilisateur</title>

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
         <jsp:include page= "nav.jsp"/>
        <div class="main">

            <section class="signup">
                <div class="container">

                    <!-- Search form -->
                    <!-- <form class="form-inline active-purple-4">
                      <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Rechercher un livre..."
                        aria-label="Search">
                      <i class="zmdi zmdi-search material-icons-name" aria-hidden="true"></i>
                    </form> -->
                    <form method="POST" class="register-form" id="register-form">
                        <div class="form-group col-md-5">
                            <input type="text" placeholder="Rechercher un utilisateur...">
                            <label for="name"><i class="zmdi zmdi-search material-icons-name"></i></label>
                        </div>
                    </form>

                    <!-- Collection de User -->
                    <section>
                        <div class="signup-content">
                            <!-- row -->
                            <div class="row">
                                <c:forEach items= "${users}" var = "elt">
                                    <div class="card-group">
                                        <div class="card">
                                            <img class="card-img-top" src="images/Users/${elt.profil}.png" alt="Card image cap">
                                            <div class="card-body">
                                                <h5 class="card-title">${elt.nom}</h5>
                                                <p class="card-text">${elt.prenom}</p>
                                                <p class="card-text"><small class="text-muted">${elt.dateNaissance}</small></p>
                                                <p class="card-text"><small class="text-muted">${elt.email}</small></p>
                                            </div>
                                           <div class="card-footer">
                                                <form action="${pageContext.request.contextPath}/listUserServlet" method="post">
                                                    <button type="submit" name="Modifier" class="form-submit btn btn-primary" value=${elt.id}>
                                                        <span class="zmdi zmdi-border-color"></span>
                                                    </button>
                                                   <button type="submit" name="Supprimer" class="form-submit btn btn-danger" value=${elt.id}>
                                                        <span class="zmdi zmdi-delete"></span>
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
                    <!-- Collection de users -->

                </div>
            </section>
        </div>
    </body>
</html>
