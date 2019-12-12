/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.entities;

import java.util.Date;

/**
 *
 * @author quent
 */
public class User {
    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String dateNaissance;
    private String profil;
    private String password;
    private String email;

    
    public User(){}
    
    public User(Long id, String nom, String prenom, String login, String dateNaissance, String profil, String password, String email){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.dateNaissance = dateNaissance;
        this.profil = profil;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }
    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    public User(String password, String nom, String prenom, String profil) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.profil = profil;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", Nom=" + nom +  ", Prenom=" + prenom +"  , Login=" + login + ", Profil=" + profil+  ", Email=" + email+ '}';
    }
}

