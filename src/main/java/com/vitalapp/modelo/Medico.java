package com.vitalapp.modelo;

public class Medico {

    private int idMedico;
    private int idUsuario;
    private String especialidad;

    public Medico() {
    }

    public Medico(int idMedico, int idUsuario, String especialidad) {
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", idUsuario=" + idUsuario +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}