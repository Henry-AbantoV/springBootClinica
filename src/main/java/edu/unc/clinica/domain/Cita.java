/*
 * @file Cita.java;
 * @Autor Daniela Torres (c)2024
 * @Created 4 mar 2024,23:45:41
 */
package edu.unc.clinica.domain;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * La clase Cita representa una cita médica en el sistema.
 * Esta clase está marcada con la anotación @Entity para indicar que es una entidad de base de datos.
 * También utiliza las anotaciones Lombok @Data para generar automáticamente los métodos getter, setter, toString, equals y hashCode.
 * La anotación @JsonIdentityInfo se utiliza para manejar referencias circulares durante la serialización JSON.
 */
@Entity
@Data
public class Cita extends  RepresentationModel<Cita>{
	
	// Identificador único de la cita.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long idCita;

    // Fecha y hora de la cita.
    private Date fechaHoraCita;

    // Motivo de la cita.
    private String motivoCita;

    // Estado de la cita (true si está confirmada, false si está pendiente o cancelada).
    private String estadoCita;

    // Factura asociada a la cita
    @ManyToOne
    @JoinColumn(name="id_Factura")
   	private Factura factura;
   
    // Paciente asociado a la cita
    @ManyToOne
    @JoinColumn(name="id_Paciente")
    private Paciente paciente;    
}
