package com.vitalapp.modelo;

import java.time.LocalDateTime;

public class Reporte {

    private int idReporte;
    private int idPaciente;
    private LocalDateTime fechaGeneracion;
    private String descripcion;

    public Reporte() {
    }

    public Reporte(int idReporte, int idPaciente,
                   LocalDateTime fechaGeneracion,
                   String descripcion) {
        this.idReporte = idReporte;
        this.idPaciente = idPaciente;
        this.fechaGeneracion = fechaGeneracion;
        this.descripcion = descripcion;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "idReporte=" + idReporte +
                ", idPaciente=" + idPaciente +
                ", fechaGeneracion=" + fechaGeneracion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}