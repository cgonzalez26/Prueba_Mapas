package com.example.prueba_mapas.models.denuncias;

public class Denuncia {
    /*********************** SPECIFICS ATTRIBUTES AND METHODS ***********************/
    private String Id = "";
    private String sNombre = "";
    private String sApellido = "";
    private String sNroDocumento = "";
    private String sMail = "";
    private String sTelefono = "";
    private String tRelato = "";
    private String TipoDenunciaId = "";
    private String sEntre_Calle = "";
    private String sDireccion = "";
    private String sLongitud = "";
    private String sLatitud = "";

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getsNroDocumento() {
        return sNroDocumento;
    }

    public void setsNroDocumento(String sNroDocumento) {
        this.sNroDocumento = sNroDocumento;
    }

    public String getsMail() {
        return sMail;
    }

    public void setsMail(String sMail) {
        this.sMail = sMail;
    }

    public String getsTelefono() {
        return sTelefono;
    }

    public void setsTelefono(String sTelefono) {
        this.sTelefono = sTelefono;
    }

    public String gettRelato() {
        return tRelato;
    }

    public void settRelato(String tRelato) {
        this.tRelato = tRelato;
    }

    public String getTipoDenunciaId() {
        return TipoDenunciaId;
    }

    public void setTipoDenunciaId(String tipoDenunciaId) {
        TipoDenunciaId = tipoDenunciaId;
    }

    public String getsEntre_Calle() {
        return sEntre_Calle;
    }

    public void setsEntre_Calle(String sEntre_Calle) {
        this.sEntre_Calle = sEntre_Calle;
    }

    public String getsDireccion() {
        return sDireccion;
    }

    public void setsDireccion(String sDireccion) {
        this.sDireccion = sDireccion;
    }

    public String getsLongitud() {
        return sLongitud;
    }

    public void setsLongitud(String sLongitud) {
        this.sLongitud = sLongitud;
    }

    public String getsLatitud() {
        return sLatitud;
    }

    public void setsLatitud(String sLatitud) {
        this.sLatitud = sLatitud;
    }
}
