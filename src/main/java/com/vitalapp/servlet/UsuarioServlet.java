package com.vitalapp.servlet;

import com.vitalapp.dao.UsuarioDAO;
import com.vitalapp.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    // ============================
    // MÉTODO GET
    // ============================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminarUsuario(request, response);
                break;

            case "listar":
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    // ============================
    // MÉTODO POST
    // ============================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {

            insertarUsuario(request, response);

        } else if ("actualizar".equals(accion)) {

            actualizarUsuario(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/usuarios"
            );
        }
    }

    // ============================
    // LISTAR USUARIOS
    // ============================
    private void listarUsuarios(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> usuarios = usuarioDAO.listar();

        request.setAttribute("usuarios", usuarios);

        request.getRequestDispatcher(
                "/usuarios/listar.jsp"
        ).forward(request, response);
    }

    // ============================
    // MOSTRAR FORMULARIO NUEVO
    // ============================
    private void mostrarFormularioNuevo(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("titulo", "Registrar Usuario");
        request.setAttribute("accion", "insertar");

        request.getRequestDispatcher(
                "/usuarios/formulario.jsp"
        ).forward(request, response);
    }

    // ============================
    // MOSTRAR FORMULARIO EDITAR
    // ============================
    private void mostrarFormularioEditar(HttpServletRequest request,
                                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            Usuario usuario = usuarioDAO.buscarPorId(id);

            if (usuario != null) {

                request.setAttribute("usuario", usuario);
                request.setAttribute("titulo", "Editar Usuario");
                request.setAttribute("accion", "actualizar");

                request.getRequestDispatcher(
                        "/usuarios/formulario.jsp"
                ).forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/usuarios"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/usuarios"
            );
        }
    }

    // ============================
    // INSERTAR USUARIO
    // ============================
    private void insertarUsuario(HttpServletRequest request,
                                  HttpServletResponse response)
            throws IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);

        usuarioDAO.insertar(usuario);

        response.sendRedirect(
                request.getContextPath() + "/usuarios"
        );
    }

    // ============================
    // ACTUALIZAR USUARIO
    // ============================
    private void actualizarUsuario(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("idUsuario")
            );

            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String rol = request.getParameter("rol");

            Usuario usuario = new Usuario();

            usuario.setIdUsuario(id);
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setContrasena(contrasena);
            usuario.setRol(rol);

            usuarioDAO.actualizar(usuario);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de usuario inválido."
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/usuarios"
        );
    }

    // ============================
    // ELIMINAR USUARIO
    // ============================
    private void eliminarUsuario(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            usuarioDAO.eliminar(id);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de usuario inválido."
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/usuarios"
        );
    }
}