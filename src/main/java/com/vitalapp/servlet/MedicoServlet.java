package com.vitalapp.servlet;

import com.vitalapp.dao.MedicoDAO;
import com.vitalapp.modelo.Medico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/medicos")
public class MedicoServlet extends HttpServlet {

private static final long serialVersionUID = 1L;

private MedicoDAO medicoDAO;

@Override
public void init() {
    medicoDAO = new MedicoDAO();
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
            eliminarMedico(request, response);
            break;

        case "listar":
        default:
            listarMedicos(request, response);
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

        insertarMedico(request, response);

    } else if ("actualizar".equals(accion)) {

        actualizarMedico(request, response);

    } else {

        response.sendRedirect(
                request.getContextPath() + "/medicos"
        );
    }
}

// ============================
// LISTAR MÉDICOS
// ============================
private void listarMedicos(HttpServletRequest request,
                           HttpServletResponse response)
        throws ServletException, IOException {

    List<Medico> medicos = medicoDAO.listar();

    request.setAttribute("medicos", medicos);

    request.getRequestDispatcher(
            "/medicos/listar.jsp"
    ).forward(request, response);
}

// ============================
// MOSTRAR FORMULARIO NUEVO
// ============================
private void mostrarFormularioNuevo(HttpServletRequest request,
                                    HttpServletResponse response)
        throws ServletException, IOException {

    request.setAttribute("titulo", "Registrar Médico");
    request.setAttribute("accion", "insertar");

    request.getRequestDispatcher(
            "/medicos/formulario.jsp"
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

        Medico medico = medicoDAO.buscarPorId(id);

        if (medico != null) {

            request.setAttribute("medico", medico);
            request.setAttribute("titulo", "Editar Médico");
            request.setAttribute("accion", "actualizar");

            request.getRequestDispatcher(
                    "/medicos/formulario.jsp"
            ).forward(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/medicos"
            );
        }

    } catch (NumberFormatException e) {

        response.sendRedirect(
                request.getContextPath() + "/medicos"
        );
    }
}

// ============================
// INSERTAR MÉDICO
// ============================
private void insertarMedico(HttpServletRequest request,
                            HttpServletResponse response)
        throws IOException {

    try {

        int idUsuario = Integer.parseInt(
                request.getParameter("idUsuario")
        );

        String especialidad = request.getParameter("especialidad");

        Medico medico = new Medico();

        medico.setIdUsuario(idUsuario);
        medico.setEspecialidad(especialidad);

        medicoDAO.insertar(medico);

    } catch (Exception e) {

        System.out.println(
                "Error al insertar médico: " + e.getMessage()
        );
    }

    response.sendRedirect(
            request.getContextPath() + "/medicos"
    );
}

// ============================
// ACTUALIZAR MÉDICO
// ============================
private void actualizarMedico(HttpServletRequest request,
                              HttpServletResponse response)
        throws IOException {

    try {

        int idMedico = Integer.parseInt(
                request.getParameter("idMedico")
        );

        int idUsuario = Integer.parseInt(
                request.getParameter("idUsuario")
        );

        String especialidad = request.getParameter("especialidad");

        Medico medico = new Medico();

        medico.setIdMedico(idMedico);
        medico.setIdUsuario(idUsuario);
        medico.setEspecialidad(especialidad);

       boolean resultado = medicoDAO.actualizar(medico);

System.out.println("=================================");
System.out.println("ACTUALIZACION DE MEDICO");
System.out.println("ID Médico: " + idMedico);
System.out.println("ID Usuario: " + idUsuario);
System.out.println("Especialidad: " + especialidad);
System.out.println("Resultado: " + resultado);
System.out.println("=================================");

    } catch (Exception e) {

        System.out.println(
                "Error al actualizar médico: " + e.getMessage()
        );
    }

    response.sendRedirect(
            request.getContextPath() + "/medicos"
    );
}

// ============================
// ELIMINAR MÉDICO
// ============================
private void eliminarMedico(HttpServletRequest request,
                            HttpServletResponse response)
        throws IOException {

    try {

        int idMedico = Integer.parseInt(
                request.getParameter("id")
        );

        medicoDAO.eliminar(idMedico);

    } catch (NumberFormatException e) {

        System.out.println(
                "ID de médico inválido."
        );
    }

    response.sendRedirect(
            request.getContextPath() + "/medicos"
    );
}


}
