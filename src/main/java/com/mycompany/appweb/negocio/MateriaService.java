package com.mycompany.appweb.negocio;

import com.mycompany.appweb.entidades.Materia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class MateriaService {
    
    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
    
    public List<Materia> getMaterias() {
        
        Query query = entityManager.createQuery("SELECT m FROM Materia m ORDER BY m.id ASC");
        
        List<Materia> materias = query.getResultList();
        
        return materias;
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
