package com.vitalapp.modelo;

import java.time.LocalDate;

public class Paciente {

    private int idPaciente;
    private int idUsuario;
    private LocalDate fechaNacimiento;
    private String sexo;

    public Paciente() {
    }

    public Paciente(int idPaciente, int idUsuario, LocalDate fechaNacimiento, String sexo) {
        this.idPaciente = idPaciente;
        this.idUsuario = idUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", idUsuario=" + idUsuario +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}