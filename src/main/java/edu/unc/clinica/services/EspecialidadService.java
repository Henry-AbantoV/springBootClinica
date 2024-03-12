/*
 * @file EspecialidadService.java;
 * @Autor Fernando C.J. (c)2024
 * @Created 12 mar. 2024,02:02:05
 */

package edu.unc.clinica.services;

import java.util.List;
import edu.unc.clinica.domain.Especialidad;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

/**
 * Interfaz que define los servicios relacionados con las especialidades
 * médicas.
 */
public interface EspecialidadService {

	/**
	 * Método para listar todas las especialidades médicas.
	 *
	 * @return Lista de objetos de tipo Especialidad.
	 */
	List<Especialidad> listarEspecialidades();

	/**
	 * Método para buscar una especialidad médica por su ID.
	 *
	 * @param IdEsp ID de la especialidad médica a buscar.
	 * @return Objeto Especialidad correspondiente al ID especificado.
	 * @throws EntityNotFoundException Si no se encuentra la especialidad médica con
	 *                                 el ID especificado.
	 */
	Especialidad buscarEspecialidadbyId(Long IdEsp) throws EntityNotFoundException;

	/**
	 * Método para grabar una nueva especialidad médica.
	 *
	 * @param espec Objeto de tipo Especialidad a ser grabado.
	 * @return La especialidad médica grabada.
	 * @throws IllegalOperationException Si se produce una operación ilegal al
	 *                                   intentar grabar la especialidad médica.
	 */
	Especialidad grabarEspecilidad(Especialidad espec) throws IllegalOperationException;

	/**
	 * Método para actualizar una especialidad médica existente.
	 *
	 * @param id    ID de la especialidad médica a actualizar.
	 * @param espec Objeto de tipo Especialidad con los datos actualizados.
	 * @return La especialidad médica actualizada.
	 * @throws EntityNotFoundException   Si no se encuentra la especialidad médica
	 *                                   con el ID especificado.
	 * @throws IllegalOperationException Si se produce una operación ilegal al
	 *                                   intentar actualizar la especialidad médica.
	 */
	Especialidad actualizarEspecilidad(Long id, Especialidad espec)
			throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Método para eliminar una especialidad médica por su ID.
	 *
	 * @param IdEsp ID de la especialidad médica a eliminar.
	 * @throws EntityNotFoundException   Si no se encuentra la especialidad médica
	 *                                   con el ID especificado.
	 * @throws IllegalOperationException Si se produce una operación ilegal al
	 *                                   intentar eliminar la especialidad médica.
	 */
	void eliminarEspecialidad(Long IdEsp) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Método para asignar médicos a una especialidad médica.
	 *
	 * @param IdEsp    ID de la especialidad médica a la que se asignarán los
	 *                 médicos.
	 * @param IdMedico ID del médico a asignar a la especialidad médica.
	 * @return La especialidad médica actualizada con el médico asignado.
	 * @throws EntityNotFoundException   Si no se encuentra la especialidad médica o
	 *                                   el médico con los IDs especificados.
	 * @throws IllegalOperationException Si se produce una operación ilegal al
	 *                                   intentar asignar el médico a la
	 *                                   especialidad médica.
	 */
	Especialidad asignarMedicos(Long IdEsp, Long IdMedico) throws EntityNotFoundException, IllegalOperationException;

}
