<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ajout utilisateur BDD</title>
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
     <section class="signup" id="signup">
          <div class="container">
              <div class="signup-content">
                  <div class="signup-form">
                      <h2 class="form-title">S'inscrire</h2>
                      <form method="POST" class="register-form" id="register-form">
                          <div class="form-group">
                              <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                              <input type="text" name="nom" id="name" placeholder="Nom"/>
                          </div>
                          <div class="form-group">
                              <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                              <input type="text" name="prenom" id="name" placeholder="Prénom"/>
                          </div>
                           <div class="form-group">
                              <label for="dateNaiss"><i class="zmdi zmdi-calendar"></i></label>
                              <input type="date" name="dateNaiss" id="pass" placeholder="Date de Naissance"/>
                          </div>
                          <div class="form-group">
                              <label for="email"><i class="zmdi zmdi-email"></i></label>
                              <input type="email" name="email" id="email" placeholder="Email"/>
                          </div>
                          
                          <div class="form-group">
                              <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                              <input type="password" name="password" id="pass" placeholder="Mot de passe"/>
                          </div>
                          <div class="form-group">
                            <select class="form-select" name="typeUser">
                              <option value="Agent">Agent</option>
                              <option value="Administrateur">Administrateur</option>
                              <option value="User">Utilisateur</option>
                            </select>
                          </div>
                          <div class="form-group form-button">
                              <input type="submit" name="valider"  class="form-submit" value="S'inscrire"/>
                          </div>
                      </form>
                  </div>
                  <div class="signup-image">
                      <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                      <a href="#signin" class="signup-image-link">J'ai déjà un compte</a>
                  </div>
              </div>
          </div>
      </section>
    </div>

    <% }%>
</html>
