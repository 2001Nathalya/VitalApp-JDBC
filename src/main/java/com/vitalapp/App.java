package com.vitalapp;

import com.vitalapp.dao.SignoVitalDAO;
import com.vitalapp.modelo.SignoVital;

import java.time.LocalDateTime;
import java.util.List;

public class App {

    public static void main(String[] args) {

        SignoVitalDAO signoVitalDAO = new SignoVitalDAO();

        System.out.println("=== PRUEBA CRUD SIGNOS VITALES ===");

        // 1. INSERTAR
        SignoVital signoVital = new SignoVital(
                0,
                1,
                LocalDateTime.now(),
                80,
                18,
                "120/80",
                36.5,
                98.0,
                90.0
        );

        boolean registrado = signoVitalDAO.insertar(signoVital);

        if (!registrado) {
            System.out.println("No se pudo registrar el signo vital.");
            return;
        }

        System.out.println("ID del signo vital registrado: "
                + signoVital.getIdSigno());

        // 2. CONSULTAR
        System.out.println("\n--- LISTA DE SIGNOS VITALES ---");

        List<SignoVital> signosVitales = signoVitalDAO.listar();

        for (SignoVital s : signosVitales) {
            System.out.println(s);
        }

        // 3. ACTUALIZAR
        System.out.println("\n--- ACTUALIZAR SIGNO VITAL ---");

        signoVital.setFrecuenciaCardiaca(85);
        signoVital.setTemperatura(37.0);

        boolean actualizado = signoVitalDAO.actualizar(signoVital);

        if (actualizado) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        // 4. ELIMINAR
        System.out.println("\n--- ELIMINAR SIGNO VITAL ---");

        boolean eliminado = signoVitalDAO.eliminar(
                signoVital.getIdSigno()
        );

        if (eliminado) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el signo vital.");
        }

        System.out.println("\n=== PRUEBA CRUD FINALIZADA ===");
    }
}