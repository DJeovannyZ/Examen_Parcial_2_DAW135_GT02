package com.mycompany.appweb.negocio;

import com.mycompany.appweb.entidades.Alumno;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class AlumnoService {

    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
    private Map<Integer, Alumno> alumnosMap;

    public List<Alumno> getAlumnos() {

        Query query = entityManager.createQuery("SELECT e FROM Alumno e ORDER BY e.id ASC");

        List<Alumno> alumnos = query.getResultList();

        return alumnos;
    }

    public Map<Integer, Alumno> getAlumnosMap() {
        if (alumnosMap == null) {
            alumnosMap = getAlumnos().stream().collect(Collectors.toMap(Alumno::getId, alumno -> alumno));
        }
        return alumnosMap;
    }

    @Transactional
    public void saveAlumno(Alumno alumno) {
        entityManager.persist(alumno);
    }

    @Transactional
    public void editAlumno(Alumno alumno) {
        entityManager.merge(alumno);
    }

    @Transactional
    public void deleteAlumno(Alumno alumno) {
        Alumno alumnoEliminar = entityManager.find(Alumno.class, alumno.getId());
        entityManager.remove(alumnoEliminar);
    }
}
