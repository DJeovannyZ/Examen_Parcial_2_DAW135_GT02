package com.mycompany.appweb.controladores;

import com.mycompany.appweb.entidades.Alumno;
import com.mycompany.appweb.entidades.Inscripcion;
import com.mycompany.appweb.entidades.Materia;
import com.mycompany.appweb.negocio.AlumnoService;
import com.mycompany.appweb.negocio.InscripcionService;
import com.mycompany.appweb.negocio.MateriaService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class InscripcionController {
    private List<Inscripcion> inscripcionesList = new ArrayList<>();

    private Inscripcion inscripcion = new Inscripcion();

    @EJB
    private AlumnoService alumnoService;

    @EJB
    InscripcionService servicio;

    @EJB
    MateriaService materiaService;

    @PostConstruct
    public void cargarInscripciones() {
        inscripcionesList = servicio.getInscripciones();
    }

    public List<Inscripcion> getInscripcionesList() {
        return inscripcionesList;
    }

    public List<String> autoCompletarAlumno(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> listNombreAlumnos = new ArrayList<>();
        List<Alumno> alumnos = alumnoService.getAlumnos();
        for (Alumno alumno : alumnos) {
            listNombreAlumnos.add(alumno.getNombre());
        }
        return listNombreAlumnos.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase))
                .collect(Collectors.toList());
    }

    public List<String> autoCompletarMateria(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> listNombreMaterias = new ArrayList<>();
        List<Materia> materias = materiaService.getMaterias();
        for (Materia materia : materias) {
            listNombreMaterias.add(materia.getNombre());
        }
        return listNombreMaterias.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase))
                .collect(Collectors.toList());
    }

    public void guardarInscripcion() {
        if (inscripcion.getId() != null) {
            servicio.editInscripcion(inscripcion);
        } else {
            servicio.saveInscripcion(inscripcion);
        }

        inscripcion = new Inscripcion();
        cargarInscripciones();
    }

    public void llenarFormEditar(Inscripcion inscripcion) {
        this.inscripcion.setId(inscripcion.getId());
        this.inscripcion.setAlumno(inscripcion.getAlumno());
        this.inscripcion.setMateria(inscripcion.getMateria());
        this.inscripcion.setCiclo(inscripcion.getCiclo());
        this.inscripcion.setAnio(inscripcion.getAnio());
        this.inscripcion.setFechaInscripcion(inscripcion.getFechaInscripcion());
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        servicio.deleteInscripcion(inscripcion);
        cargarInscripciones();
    }

    // Getters and Setters
    public void setInscripcionesList(List<Inscripcion> inscripcionesList) {
        this.inscripcionesList = inscripcionesList;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }
}
