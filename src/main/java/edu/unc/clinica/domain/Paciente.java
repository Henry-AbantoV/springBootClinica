/*
 * @file Paciente.java;
 * @Autor Daniela Torres (c)2024
 * @Created 9 mar 2024,19:05:09
 */
package edu.unc.clinica.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idPaciente")
public class Paciente extends RepresentationModel<Paciente>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    // Nombres del paciente.
	@NotNull(message="No puede ser nulo el campo")
	@NotBlank
    private String nombres;

    // Apellidos del paciente.
	@NotNull
	@NotBlank
    private String apellidos;

    // Fecha de nacimiento del paciente   
    private Date fechaNacimiento;

    //Género del paciente. 
    @NotNull
	@NotBlank
    private String genero;

    // DNI del paciente.
    private String dni;

    // Dirección del paciente.    
    private String direccion;

    // Número de teléfono del paciente.
    private String telefono;

    // Correo electrónico del paciente.
    @NotNull
	@NotBlank
	@Email
    private String correoElectronico;
    
    //Una lista de citas asociadas a paciente.
    @OneToMany (mappedBy = "paciente")
    private List<Cita> citas = new ArrayList<>();

   //Una lista de médicos asociados a este paciente.
   @ManyToMany
   @JoinTable(
		  name="Consulta",
		  joinColumns = @JoinColumn(name = "medico_id"),
		  inverseJoinColumns = @JoinColumn(name = "paciente_id"))
   private List<Medico> medicos = new ArrayList<>();        
    
    //Historial médico asociado a paciente. 
    @OneToOne(mappedBy="paciente")    
    private HistorialMedico historialMedico;  

}
