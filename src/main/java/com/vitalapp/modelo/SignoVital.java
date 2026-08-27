package com.vitalapp.modelo;

import java.time.LocalDateTime;

public class SignoVital {

    private int idSigno;
    private int idPaciente;
    private LocalDateTime fechaRegistro;
    private int frecuenciaCardiaca;
    private int frecuenciaRespiratoria;
    private String presionArterial;
    private double temperatura;
    private double saturacionOxigeno;
    private double glucemia;

    public SignoVital() {
    }

    public SignoVital(
            int idSigno,
            int idPaciente,
            LocalDateTime fechaRegistro,
            int frecuenciaCardiaca,
            int frecuenciaRespiratoria,
            String presionArterial,
            double temperatura,
            double saturacionOxigeno,
            double glucemia) {

        this.idSigno = idSigno;
        this.idPaciente = idPaciente;
        this.fechaRegistro = fechaRegistro;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.presionArterial = presionArterial;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;
        this.glucemia = glucemia;
    }

    public int getIdSigno() {
        return idSigno;
    }

    public void setIdSigno(int idSigno) {
        this.idSigno = idSigno;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getGlucemia() {
        return glucemia;
    }

    public void setGlucemia(double glucemia) {
        this.glucemia = glucemia;
    }

    @Override
    public String toString() {
        return "SignoVital{" +
                "idSigno=" + idSigno +
                ", idPaciente=" + idPaciente +
                ", fechaRegistro=" + fechaRegistro +
                ", frecuenciaCardiaca=" + frecuenciaCardiaca +
                ", frecuenciaRespiratoria=" + frecuenciaRespiratoria +
                ", presionArterial='" + presionArterial + '\'' +
                ", temperatura=" + temperatura +
                ", saturacionOxigeno=" + saturacionOxigeno +
                ", glucemia=" + glucemia +
                '}';
    }
}