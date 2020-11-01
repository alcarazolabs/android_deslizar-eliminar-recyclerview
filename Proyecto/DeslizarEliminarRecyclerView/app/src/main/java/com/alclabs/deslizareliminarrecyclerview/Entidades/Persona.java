package com.alclabs.deslizareliminarrecyclerview.Entidades;

public class Persona {
    private String nombres;
    private String descripcion;
    private int imagenid;

    public Persona(){}
    public Persona(String nombres, String descripcion, int imagenid) {
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.imagenid = imagenid;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
