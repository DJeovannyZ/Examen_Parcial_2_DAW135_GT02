package com.mycompany.appweb.negocio;

import com.mycompany.appweb.entidades.Materia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class MateriaService {

    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
	private Map<Integer, Materia> materiasMap;

    public List<Materia> getMaterias() {

        Query query = entityManager.createQuery("SELECT m FROM Materia m ORDER BY m.id ASC");

        List<Materia> materias = query.getResultList();

        return materias;
    }

    public Map<Integer, Materia> getMateriasMap() {
        if (materiasMap == null) {
            materiasMap = getMaterias().stream().collect(Collectors.toMap(Materia::getId, materia -> materia));
        }
        return materiasMap;
    }

    @Transactional
    public void saveMateria(Materia materia) {
        entityManager.persist(materia);
    }

    @Transactional
    public void editMateria(Materia materia) {
        entityManager.merge(materia);
    }

    @Transactional
    public void deleteMateria(Materia materia) {
        Materia materiaEliminar = entityManager.find(Materia.class, materia.getId());
        entityManager.remove(materiaEliminar);
    }
}
