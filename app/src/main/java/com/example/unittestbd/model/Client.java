package com.example.unittestbd.model;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String Lastname;
    private String Firstname;

    private int Solde;
    ArrayList<String> anomalies = new ArrayList<>();

    public Client() {
    }

    public Client(String Lastname, String Firstname, int Solde) {
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.Solde = Solde;

        this.verifierRegleDeGestion();
    }

    /**
     * Gestion d'anomalies
     * <p>
     * Méthode appelée lorsqu'on désire vérifier que l'objet a toutes ses règles de gestion respectées.
     */
    public void verifierRegleDeGestion() {
        if (this.Lastname != null && this.Lastname.isEmpty() && !this.anomalies.contains("Nom vide")) {
            this.anomalies.add("Nom vide");
        }

        if (this.Firstname != null && this.Firstname.isEmpty() && !this.anomalies.contains("Prénom vide")) {
            this.anomalies.add("Prénom vide");
        }

        if (this.Lastname != null && this.Firstname != null && !this.Lastname.isEmpty() && !this.Firstname.isEmpty()) {
            this.anomalies.clear(); // Réinitialiser les anomalies si aucun problème n'est détecté
        }
    }

    /**
     * Gestion d'anomalies
     * <p>
     * Est-ce que cet objet a toutes les règles de gestion respectées pour être persistable ?
     *
     * @return boolean
     */
    public boolean isPersistable() {
        if (this.anomalies.size() > 0) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;

        this.verifierRegleDeGestion();
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;

        this.verifierRegleDeGestion();
    }

    public int getSolde() {
        return Solde;
    }

    public void setSolde(int Solde) {
        this.Solde = Solde;
    }

    @Override
    public String toString() {
        return "Client{" + "Lastname='" + Lastname + '\'' + ", Firstname='" + Firstname + '\'' + ", Solde=" + Solde + '}';
    }

    /**
     * Conversion du client au format JSONArray.
     * @return JSONArray
     */
    public JSONArray convertToJSONArray() {

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(this.Lastname);
        jsonArray.put(this.Firstname);
        jsonArray.put(String.valueOf(this.Solde));
        return jsonArray;
    }


    /**
     * Retourne true si l'objet n'a pas encore été persisté et false sinon.
     *
     * @return boolean
     */
    public boolean isNew() {
        if (this.id == 0) {
            return true;
        }
        return false;
    }

    /**
     * Retourne true si l'objet a été persisté et false sinon.
     *
     * @return
     */
    public boolean isNotNew() {
        return !isNew();
    }
}
