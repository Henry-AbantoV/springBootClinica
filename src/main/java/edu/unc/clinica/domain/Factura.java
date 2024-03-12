/*
 * @file Factura.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,2:36:03
 */
package edu.unc.clinica.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * La clase Factura representa una factura en el sistema. Esta clase está
 * marcada con la anotación @Entity para indicar que es una entidad de base de
 * datos. También utiliza las anotaciones Lombok @Data para generar
 * automáticamente los métodos getter, setter, toString, equals y hashCode.
 */

@Entity
@Data
public class Factura extends RepresentationModel<Factura>{

	// Identificador único de la factura.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;

   
    // Fecha de emisión de la factura.
    private Date fechaEmision;

 
    // Descripción de los servicios incluidos en la factura.
    private String descripServicios;

    // Detalles de los pagos realizados.
    private String pagoRealizados;


	// Saldo pendiente por pagar.
	private double saldoPendiente;


    // Costo total de la factura.
    private double costo;

    
    @OneToMany(mappedBy="factura", cascade = CascadeType.ALL)
	private List<Cita> citas=new ArrayList<>();

}
