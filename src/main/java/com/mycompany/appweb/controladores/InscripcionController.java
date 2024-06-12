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

import java.time.LocalDate;
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

    private Alumno alumno1;

    private Materia materia1;


    @PostConstruct
    public void cargarInscripciones() {
        inscripcionesList = servicio.getInscripciones();
    }

    public List<Inscripcion> getInscripcionesList() {
        return inscripcionesList;
    }

    public List<Alumno> autoCompletarAlumno(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Alumno> alumnos = alumnoService.getAlumnos();
        return alumnos.stream().filter(t -> t.getNombre().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public List<Materia> autoCompletarMateria(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Materia> materias = materiaService.getMaterias();
        return materias.stream().filter(t -> t.getNombre().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }


    public void guardarInscripcion() {
        if (inscripcion.getId() != null) {
            servicio.editInscripcion(inscripcion);
        } else {
            inscripcion.setFechaInscripcion(LocalDate.now());
            inscripcion.setAlumno(alumno1);
            inscripcion.setMateria(materia1);
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

	public AlumnoService getAlumnoService() {
		return alumnoService;
	}

	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	public InscripcionService getServicio() {
		return servicio;
	}

	public void setServicio(InscripcionService servicio) {
		this.servicio = servicio;
	}

	public MateriaService getMateriaService() {
		return materiaService;
	}

	public void setMateriaService(MateriaService materiaService) {
		this.materiaService = materiaService;
	}

	public Alumno getAlumno1() {
		return alumno1;
	}

	public void setAlumno1(Alumno alumno1) {
		this.alumno1 = alumno1;
	}

	public Materia getMateria1() {
		return materia1;
	}

	public void setMateria1(Materia materia1) {
		this.materia1 = materia1;
	}
}
