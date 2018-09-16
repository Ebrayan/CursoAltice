package com.example.thebryan.personalsecurityapp.Models;

public class Contact {

    public Contact(String name, String contact_Data, String trustedContacOf, String parentesco, String mapa ) {
        this.name = name;
        this.contact_Data = contact_Data;
        TrustedContacOf = trustedContacOf;
        this.parentesco = parentesco;
        this.mapa =mapa;
    }
    public Contact(String name, String contact_Data, String trustedContacOf, String parentesco, String mapa, String UserID) {
        this.name = name;
        this.contact_Data = contact_Data;
        TrustedContacOf = trustedContacOf;
        this.parentesco = parentesco;
        this.mapa =mapa;
        this.contact_id_user = UserID;
    }
    public Contact(String name, String contact_Data) {
        this.name = name;
        this.contact_Data = contact_Data;



    }
    public Contact(){

    }




    String idUniqueCOntact;
    String name;
    String contact_Data;
    String contact_id_user;
    String TrustedContacOf;
    String URLcontact_Image;
    String mapa;
    public String parentesco;


    public String getContact_id_user() {
        return contact_id_user;
    }
    public String getIdUniqueCOntact() {
        return idUniqueCOntact;
    }
    public void setIdUniqueCOntact(String idUniqueCOntact) {
        this.idUniqueCOntact = idUniqueCOntact;
    }
    public void setContact_id_user(String contact_id_user) {
        this.contact_id_user = contact_id_user;
    }
    public String getTrustedContacOf() {
        return TrustedContacOf;
    }
    public void setTrustedContacOf(String trustedContacOf) {
        TrustedContacOf = trustedContacOf;
    }
    public String getMapa() {
        return mapa;
    }
    public String getName() {
        return name;
    }
    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact_Data() {
        return contact_Data;
    }
    public void setContact_Data(String contact_Data) {
        this.contact_Data = contact_Data;
    }
}
