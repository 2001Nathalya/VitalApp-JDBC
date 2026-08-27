package com.vitalapp.modelo;

import java.time.LocalDateTime;

public class Alerta {

    private int idAlerta;
    private int idPaciente;
    private String tipoAlerta;
    private String descripcion;
    private LocalDateTime fechaAlerta;

    public Alerta() {
    }

    public Alerta(
            int idAlerta,
            int idPaciente,
            String tipoAlerta,
            String descripcion,
            LocalDateTime fechaAlerta) {

        this.idAlerta = idAlerta;
        this.idPaciente = idPaciente;
        this.tipoAlerta = tipoAlerta;
        this.descripcion = descripcion;
        this.fechaAlerta = fechaAlerta;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(LocalDateTime fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "idAlerta=" + idAlerta +
                ", idPaciente=" + idPaciente +
                ", tipoAlerta='" + tipoAlerta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlerta=" + fechaAlerta +
                '}';
    }
}