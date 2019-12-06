<%-- 
    Document   : index
    Created on : 6 déc. 2019, 16:03:13
    Author     : kebson
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Bibliotheque Doudouxnnn</title>
    
    <jsp:include page="header.jsp"/>


</head>
<body>

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
              <input type="text" placeholder="Rechercher un livre...">
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
                      <img class="card-img-top" src="images/books/1.jpg" alt="Card image cap">
                      <div class="card-body">
                        <h5 class="card-title">${elt.titre}</h5>
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

   
</html>
