/*
 * @file CitaService.java;
 * @Autor Daniela Torres (c)2024
 * @Created 12 mar 2024,2:16:39
 */
package edu.unc.clinica.services;

import java.util.List;
import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

// TODO: Auto-generated Javadoc
/**
 * La interfaz CitaService define operaciones para gestionar las citas en el
 * sistema. Proporciona métodos para listar citas, buscar cita por ID, grabar
 * cita, actualizar cita, eliminar cita y asignar una factura a una cita.
 */
public interface CitaService {
	/**
	 * Recupera una lista de todas las citas almacenadas en el sistema.
	 *
	 * @return Lista de citas.
	 */
	List<Cita> listarCitas();

	/**
	 * Busca una cita por su identificador único.
	 *
	 * @param IdCita the id cita
	 * @return Cita correspondiente al ID proporcionado.
	 * @throws EntityNotFoundException Si no se encuentra la cita con el ID
	 *                                 especificado.
	 */
	Cita buscarCitabyId(Long IdCita) throws EntityNotFoundException;

	/**
	 * Guarda una nueva cita en el sistema.
	 *
	 * @param cita La cita a ser guardada.
	 * @return Cita guardada.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	Cita grabarCita(Cita cita) throws IllegalOperationException;

	/**
	 * Actualiza la información de una cita existente en el sistema.
	 *
	 * @param id   Identificador único de la cita a ser actualizada.
	 * @param cita Nueva información de la cita.
	 * @return Cita actualizada.
	 * @throws EntityNotFoundException   Si no se encuentra la cita con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	Cita actualizarCita(Long id, Cita cita) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Elimina una cita del sistema por su identificador único.
	 *
	 * @param IdCita the id cita
	 * @throws EntityNotFoundException   Si no se encuentra la cita con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	void eliminarCita(Long IdCita) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Asigna una factura a una cita existente en el sistema.
	 *
	 * @param IdCita the id cita
	 * @param IdFactura the id factura
	 * @return Cita actualizada con la asignación de factura.
	 * @throws EntityNotFoundException   Si no se encuentra la cita o la factura con
	 *                                   los ID especificados.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	Cita asignarFactura(Long IdCita, Long IdFactura) throws EntityNotFoundException, IllegalOperationException;

}
