<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ajout livre</title>
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
     <section class="signup" id="signup">
          <div class="container">
              <div class="signup-content">
                  <div class="signup-form">
                      <h2 class="form-title">Modifier le livre</h2>
                      <form method="POST" class="register-form" id="register-form">
                        <div class="form-group">
                          <input type="file" name="addLivre" value="Ajouter auteur"/>
                        </div>
                          <div class="form-group">
                              <label for="name"><i class="zmdi zmdi-format-color-text material-icons-name"></i></label>
                              <input type="text" name="titre" id="name" placeholder="Titre"/>
                          </div>
                          <div class="form-group">
                              <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                              <input type="text" name="auteur" id="name" placeholder="Auteur"/>
                          </div>
                          <div class="form-group">
                              <label for="email"><i class="zmdi zmdi-home"></i></label>
                              <input type="text" name="editeur" id="email" placeholder="Editeur"/>
                          </div>
                          <div class="form-group">
                              <label for="pass"><i class="zmdi zmdi-calendar"></i></label>
                              <input type="date" name="parution" id="pass" placeholder="Date de parution"/>
                          </div>
                          <div class="form-group form-button">
                              <input type="submit" name="ajouter"  class="form-submit" value="Modifier"/>
                          </div>
                      </form>
                  </div>
                  <div class="signup-image">
                      <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                  </div>
              </div>
          </div>
      </section>
    </div>

   
</html>
