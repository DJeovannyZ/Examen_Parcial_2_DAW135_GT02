package com.mycompany.appweb.converters;
import com.mycompany.appweb.entidades.Alumno;
import com.mycompany.appweb.negocio.AlumnoService;

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
public class AlumnoConverter implements Converter<Alumno> {

    @EJB
    private AlumnoService alumnoService;

    @Override
    public Alumno getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return alumnoService.getAlumnosMap().get(Integer.parseInt(value));
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Alumno no valido."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Alumno value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }
}
