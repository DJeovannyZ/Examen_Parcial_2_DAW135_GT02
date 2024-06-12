package com.mycompany.appweb.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscripciones_id_seq")
    @SequenceGenerator(name = "inscripciones_id_seq", sequenceName = "inscripciones_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "alumnoid", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "materiaid", nullable = false)
    private Materia materia;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "fechaInscripcion")
    private LocalDate fechaInscripcion;

    public Inscripcion() {

    }

    public Inscripcion(Integer id, String ciclo, Integer anio) {
        this.id = id;
        this.ciclo = ciclo;
        this.anio = anio;

    }

    public Inscripcion(Alumno alumno, Materia materia, String ciclo, int anio, LocalDate fechaInscripcion) {
        this.alumno = alumno;
        this.materia = materia;
        this.ciclo = ciclo;
        this.anio = anio;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Inscripcion(Integer id, Alumno alumno, Materia materia, String ciclo, int anio, LocalDate fechaInscripcion) {
        this.id = id;
        this.alumno = alumno;
        this.materia = materia;
        this.ciclo = ciclo;
        this.anio = anio;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // hashCode, equals, toString

    @Override
    public int hashCode() {
        return Objects.hash(id, ciclo, anio, fechaInscripcion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inscripcion other = (Inscripcion) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", alumno=" + alumno +
                ", materia=" + materia +
                ", ciclo='" + ciclo + '\'' +
                ", anio=" + anio +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
