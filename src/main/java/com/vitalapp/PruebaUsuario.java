package com.vitalapp;

import com.vitalapp.dao.UsuarioDAO;
import com.vitalapp.modelo.Usuario;

import java.util.List;

public class PruebaUsuario {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD USUARIOS - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR USUARIO ---");

        Usuario usuario = new Usuario(
                0,
                "Maria Lopez",
                "maria.lopez.prueba@gmail.com",
                "123456",
                "Paciente"
        );

        boolean registrado = usuarioDAO.insertar(usuario);

        if (!registrado) {
            System.out.println("ERROR: No se pudo insertar el usuario.");
            return;
        }

        System.out.println("ID generado: " + usuario.getIdUsuario());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR USUARIOS ---");

        List<Usuario> usuarios = usuarioDAO.listar();

        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR USUARIO ---");

        Usuario encontrado =
                usuarioDAO.buscarPorId(usuario.getIdUsuario());

        if (encontrado != null) {
            System.out.println("Usuario encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("ERROR: Usuario no encontrado.");
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR USUARIO ---");

        usuario.setNombre("Maria Lopez Actualizada");
        usuario.setRol("Administrador");

        boolean actualizado =
                usuarioDAO.actualizar(usuario);

        if (actualizado) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("ERROR: No se pudo actualizar.");
        }

        // 5. COMPROBAR ACTUALIZACIÓN
        System.out.println("\n--- COMPROBAR ACTUALIZACION ---");

        Usuario actualizadoEncontrado =
                usuarioDAO.buscarPorId(usuario.getIdUsuario());

        if (actualizadoEncontrado != null) {
            System.out.println(actualizadoEncontrado);
        }

        // 6. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR USUARIO ---");

        boolean eliminado =
                usuarioDAO.eliminar(usuario.getIdUsuario());

        if (eliminado) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("ERROR: No se pudo eliminar.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD USUARIOS FINALIZADA");
        System.out.println("======================================");
    }
}