package com.mycompany.appweb.negocio;

import com.mycompany.appweb.entidades.Inscripcion;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class InscripcionService {
    
    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
    
    public List<Inscripcion> getInscripciones() {
        
        Query query = entityManager.createQuery("SELECT i FROM Inscripcion i ORDER BY i.id ASC");
        
        List<Inscripcion> inscripciones = query.getResultList();
        
        return inscripciones;
    }
    
    @Transactional
    public void saveInscripcion(Inscripcion inscripcion) {
        entityManager.persist(inscripcion);
    }
    
    @Transactional
    public void editInscripcion(Inscripcion inscripcion) {
        entityManager.merge(inscripcion);
    }
    
    @Transactional
    public void deleteInscripcion(Inscripcion inscripcion) {
        Inscripcion inscripcionEliminar = entityManager.find(Inscripcion.class, inscripcion.getId());
        entityManager.remove(inscripcionEliminar);
    }
}
