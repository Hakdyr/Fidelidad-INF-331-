package com.empresa.fidelidad.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private int puntos;
    private Nivel nivel;
    private List<Compra> compras;

    public Cliente(String id, String nombre, String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = Nivel.BRONCE;
        this.compras = new ArrayList<>();
    }

    public void actualizarNivel() {
        if (puntos >= 3000) {
            nivel = Nivel.PLATINO;
        } else if (puntos >= 1500) {
            nivel = Nivel.ORO;
        } else if (puntos >= 500) {
            nivel = Nivel.PLATA;
        } else {
            nivel = Nivel.BRONCE;
        }
    }

    public void agregarCompra(Compra compra) {
        this.compras.add(compra);
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.correo = correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public List<Compra> getCompras() {
        return compras;
    }
}
