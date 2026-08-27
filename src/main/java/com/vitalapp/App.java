package com.vitalapp;

import com.vitalapp.dao.UsuarioDAO;
import com.vitalapp.modelo.Usuario;
import com.vitalapp.dao.MedicoDAO;
import com.vitalapp.modelo.Medico;

import java.util.List;

public class App {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("=== PRUEBA CRUD USUARIOS VITALAPP ===");
        // =====================================================
// PRUEBA CRUD MÉDICOS
// =====================================================

MedicoDAO medicoDAO = new MedicoDAO();

System.out.println("\n=== PRUEBA CRUD MÉDICOS VITALAPP ===");

// 1. INSERTAR
Medico medico = new Medico(
        0,
        1,
        "Medicina General"
);

boolean registradoMedico = medicoDAO.insertar(medico);

if (!registradoMedico) {
    System.out.println("No se pudo registrar el médico.");
    return;
}

System.out.println("ID del médico registrado: "
        + medico.getIdMedico());

// 2. CONSULTAR
System.out.println("\n--- LISTA DE MÉDICOS ---");

List<Medico> medicos = medicoDAO.listar();

for (Medico m : medicos) {
    System.out.println(m);
}

// 3. BUSCAR POR ID
System.out.println("\n--- BUSCAR MÉDICO POR ID ---");

Medico medicoEncontrado =
        medicoDAO.buscarPorId(medico.getIdMedico());

if (medicoEncontrado != null) {
    System.out.println(medicoEncontrado);
}

// 4. ACTUALIZAR
System.out.println("\n--- ACTUALIZAR MÉDICO ---");

medico.setEspecialidad("Medicina Interna");

boolean actualizadoMedico =
        medicoDAO.actualizar(medico);

if (actualizadoMedico) {
    System.out.println(
            "Prueba de actualización completada correctamente."
    );
}

// 5. ELIMINAR
System.out.println("\n--- ELIMINAR MÉDICO ---");

boolean eliminadoMedico =
        medicoDAO.eliminar(medico.getIdMedico());

if (eliminadoMedico) {
    System.out.println(
            "Prueba de eliminación completada correctamente."
    );
} else {
    System.out.println("No se pudo eliminar el médico.");
}

System.out.println("\n=== PRUEBA CRUD MÉDICOS FINALIZADA ===");

        // 1. INSERTAR
        Usuario usuario = new Usuario(
                0,
                "Maria Lopez",
                "maria.lopez@gmail.com",
                "123456",
                "Paciente"
        );

        boolean registrado = usuarioDAO.insertar(usuario);

        if (!registrado) {
            System.out.println("No se pudo registrar el usuario.");
            return;
        }

        System.out.println("ID del usuario registrado: "
                + usuario.getIdUsuario());

        // 2. CONSULTAR
        System.out.println("\n--- LISTA DE USUARIOS ---");

        List<Usuario> usuarios = usuarioDAO.listar();

        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        // 3. BUSCAR POR ID
        System.out.println("\n--- BUSCAR USUARIO POR ID ---");

        Usuario usuarioEncontrado =
                usuarioDAO.buscarPorId(usuario.getIdUsuario());

        if (usuarioEncontrado != null) {
            System.out.println(usuarioEncontrado);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- ACTUALIZAR USUARIO ---");

        usuario.setNombre("Maria Lopez Actualizada");
        usuario.setRol("Administrador");

        boolean actualizado = usuarioDAO.actualizar(usuario);

        if (actualizado) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        // 5. ELIMINAR
        System.out.println("\n--- ELIMINAR USUARIO ---");

        boolean eliminado =
                usuarioDAO.eliminar(usuario.getIdUsuario());

        if (eliminado) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }

        System.out.println("\n=== PRUEBA CRUD USUARIOS FINALIZADA ===");
    }
}