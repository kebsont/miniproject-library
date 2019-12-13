/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.entities;

import java.util.Date;

/**
 *
 * @author kebson
 */
public class Book {
    private Long id;
    private String titre;
    private String auteur;
    private String edition;
    private Date dateParution;
    private Byte disponibilite;
    public Book(){}
    
    public Book(Long id, String titre, String auteur, String edition, Date dateParution, Byte disponibilite){
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.dateParution = dateParution;
        this.disponibilite=disponibilite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }
    public Byte getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Byte disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", titre=" + titre + '}';
    }
    
    
    
    
}
