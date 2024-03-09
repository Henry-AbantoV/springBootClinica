package edu.unc.clinica.services;

import java.util.List;


import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

/**
 * Interfaz que define los servicios relacionados con la entidad Paciente.
 */
public interface PacienteService {

	 /**
     * Obtiene una lista de todos los pacientes registrados.
     * 
     * @return Lista de objetos {@link Paciente}.
     */
	List<Paciente> listarPacientes();
	 /**
     * Busca un paciente específico por su identificador.
     * 
     * @param IdPaciente Identificador único del paciente a buscar.
     * @return Objeto {@link Paciente} encontrado.
     * @throws EntityNotFoundException Si el paciente no se encuentra.
     */
	Paciente buscarPacienteById(Long IdPacient) throws EntityNotFoundException;
	 /**
     * Registra un nuevo paciente en el sistema.
     * 
     * @param paciente Objeto {@link Paciente} con la información a registrar.
     * @return Objeto {@link Paciente} registrado con su identificador asignado.
     * @throws IllegalOperationException Si la operación no cumple con las reglas de negocio.
     */
	Paciente grabarPaciente (Paciente paciente) throws IllegalOperationException;
	 /**
     * Actualiza la información de un paciente existente.
     * 
     * @param id Identificador único del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la información actualizada.
     * @return Objeto {@link Paciente} actualizado.
     * @throws EntityNotFoundException Si el paciente no se encuentra.
     * @throws IllegalOperationException Si la operación no cumple con las reglas de negocio.
     */
	Paciente actualizarPaciente(Long id, Paciente paciente) throws EntityNotFoundException, IllegalOperationException;
	   /**
     * Elimina un paciente del sistema.
     * 
     * @param idPaciente Identificador único del paciente a eliminar.
     * @throws EntityNotFoundException Si el paciente no se encuentra.
     * @throws IllegalOperationException Si la operación no cumple con las reglas de negocio.
     */
	void eliminarPaciente(Long idPaciente) throws EntityNotFoundException, IllegalOperationException;
	 /**
     * Asigna una cita a un paciente.
     * 
     * @param idPaciente Identificador único del paciente.
     * @param IdCita Identificador único de la cita a asignar.
     * @return Objeto {@link Paciente} al que se le asignó la cita.
     * @throws EntityNotFoundException Si el paciente o la cita no se encuentran.
     * @throws IllegalOperationException Si la operación no cumple con las reglas de negocio.
     */
	Paciente asignarCita(Long idPaciente, Long IdCita) throws EntityNotFoundException, IllegalOperationException;
	
}
