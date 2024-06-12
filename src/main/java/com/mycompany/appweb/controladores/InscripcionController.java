package com.mycompany.appweb.controladores;

import com.mycompany.appweb.entidades.Inscripcion;
import com.mycompany.appweb.negocio.InscripcionService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class InscripcionController {
    private List<Inscripcion> inscripcionesList = new ArrayList<>();

    private Inscripcion inscripcion = new Inscripcion();

    @EJB
    InscripcionService servicio;

    @PostConstruct
    public void cargarInscripciones() {
        inscripcionesList = servicio.getInscripciones();
    }

    public List<Inscripcion> getInscripcionesList() {
        return inscripcionesList;
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
