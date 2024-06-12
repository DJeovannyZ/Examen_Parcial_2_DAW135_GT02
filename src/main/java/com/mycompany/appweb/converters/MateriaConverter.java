package com.mycompany.appweb.converters;
import com.mycompany.appweb.entidades.Alumno;
import com.mycompany.appweb.entidades.Materia;
import com.mycompany.appweb.negocio.AlumnoService;
import com.mycompany.appweb.negocio.MateriaService;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "alumnoConverter", managed = true)
public class MateriaConverter implements Converter<Materia> {

    @EJB
    private MateriaService materiaService;

    @Override
    public Materia getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return materiaService.getMateriasMap().get(Integer.parseInt(value));
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Materia no valida."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Materia value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }
}
