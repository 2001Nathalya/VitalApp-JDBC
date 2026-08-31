package com.vitalapp;

import com.vitalapp.dao.SignoVitalDAO;
import com.vitalapp.modelo.SignoVital;

import java.time.LocalDateTime;
import java.util.List;

public class PruebaSignoVital {

    public static void main(String[] args) {

        SignoVitalDAO signoVitalDAO = new SignoVitalDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD SIGNOS VITALES - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR SIGNO VITAL ---");

        SignoVital signoVital = new SignoVital(
                0,
                1,
                LocalDateTime.now(),
                75,
                18,
                "120/80",
                36.5,
                98.0,
                90.0
        );

        boolean registrado =
                signoVitalDAO.insertar(signoVital);

        if (!registrado) {
            System.out.println("ERROR: No se pudo insertar el signo vital.");
            return;
        }

        System.out.println("ID generado: "
                + signoVital.getIdSigno());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR SIGNOS VITALES ---");

        List<SignoVital> signosVitales =
                signoVitalDAO.listar();

        for (SignoVital s : signosVitales) {
            System.out.println(s);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR SIGNO VITAL ---");

        SignoVital encontrado =
                signoVitalDAO.buscarPorId(
                        signoVital.getIdSigno()
                );

        if (encontrado != null) {
            System.out.println("Signo vital encontrado:");
            System.out.println(encontrado);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR SIGNO VITAL ---");

        signoVital.setFrecuenciaCardiaca(80);
        signoVital.setTemperatura(36.8);

        boolean actualizado =
                signoVitalDAO.actualizar(signoVital);

        if (actualizado) {
            System.out.println("Signo vital actualizado correctamente.");
        }

        // 5. COMPROBAR ACTUALIZACIÓN
        System.out.println("\n--- COMPROBAR ACTUALIZACIÓN ---");

        SignoVital actualizadoEncontrado =
                signoVitalDAO.buscarPorId(
                        signoVital.getIdSigno()
                );

        if (actualizadoEncontrado != null) {
            System.out.println(actualizadoEncontrado);
        }

        // 6. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR SIGNO VITAL ---");

        boolean eliminado =
                signoVitalDAO.eliminar(
                        signoVital.getIdSigno()
                );

        if (eliminado) {
            System.out.println("Signo vital eliminado correctamente.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD SIGNOS VITALES FINALIZADA");
        System.out.println("======================================");
    }
}