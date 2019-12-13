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
public class Emprunt {

    private Long id;
    private Long idBook;

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }
    private String titreBook;
    private String auteurBook;
    private String nomUser;
    private String prenomUser;
    private Date dateEmprunt;

    public Emprunt() {
    }

    public Emprunt(Long id,Long idBook, String titreBook, String auteurBook, String nomUser, String prenomUser, Date dateEmprunt) {
        this.id = id;
        this.idBook=idBook;
        this.titreBook = titreBook;
        this.auteurBook = auteurBook;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.dateEmprunt = dateEmprunt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getTitreBook() {
        return titreBook;
    }

    public void setTitreBook(String titreBook) {
        this.titreBook = titreBook;
    }

    public String getAuteurBook() {
        return auteurBook;
    }

    public void setAuteurBook(String auteurBook) {
        this.auteurBook = auteurBook;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    @Override
    public String toString() {
        return "Emprunt{" + "id=" + id + ", titreBook=" + titreBook + ", auteurBook=" + auteurBook + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", dateEmprunt=" + dateEmprunt + '}';
    }

}
