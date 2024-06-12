package com.mycompany.appweb.controladores;

import com.mycompany.appweb.entidades.Materia;
import com.mycompany.appweb.negocio.MateriaService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class MateriaController {
    private List<Materia> materiasList = new ArrayList<>();
    
    private Materia materia = new Materia();
    
    @EJB 
    MateriaService servicio;
    
    @PostConstruct
    public void cargarMaterias() {
        materiasList = servicio.getMaterias();
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void guardarMateria() {
        if(materia.getId() != null) {
            servicio.editMateria(materia);
        } else {
            servicio.saveMateria(materia);
        }
        
        materia = new Materia();
        cargarMaterias();
    }
    
    public void llenarFormEditar(Materia materia) {
        this.materia.setId(materia.getId());
        this.materia.setNombre(materia.getNombre());
        this.materia.setCodigo(materia.getCodigo());
        this.materia.setDescripcion(materia.getDescripcion());
    }
    
    public void eliminarMateria(Materia materia) {
        servicio.deleteMateria(materia);
        cargarMaterias();
    }
    
    //Getters and Setters
    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
