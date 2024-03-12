package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import edu.unc.clinica.domain.Medico;
import edu.unc.clinica.domainModels.Departamento;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

/**
 * Esta interfaz define los métodos que debe implementar un servicio para
 * gestionar la entidad Medico.
 */
public interface MedicoService {

	/**
	 * Obtiene una lista de todos los médicos.
	 * 
	 * @return Una lista de objetos Medico.
	 */
	List<Medico> listarMedicos();

	/**
	 * Busca un médico por su ID.
	 * 
	 * @param idMedico El ID del médico a buscar.
	 * @return El objeto Medico correspondiente al ID especificado.
	 * @throws EntityNotFoundException Si no se encuentra ningún médico con el ID
	 *                                 especificado.
	 */
	Medico buscarMedicoById(Long idMedico) throws EntityNotFoundException;

	/**
	 * Guarda un nuevo médico en la base de datos.
	 * 
	 * @param medico El objeto Medico a guardar.
	 * @return El médico guardado.
	 * @throws IllegalOperationException Si ocurre una operación ilegal durante la
	 *                                   operación.
	 */
	Medico grabarMedico(Medico medico) throws IllegalOperationException;

	/**
	 * Actualiza un médico existente en la base de datos.
	 * 
	 * @param id     El ID del médico a actualizar.
	 * @param medico El objeto Medico con los datos actualizados.
	 * @return El médico actualizado.
	 * @throws EntityNotFoundException   Si no se encuentra ningún médico con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si ocurre una operación ilegal durante la
	 *                                   operación.
	 */
	Medico actualizarMedico(Long id, Medico medico) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Elimina un médico de la base de datos.
	 * 
	 * @param idMedico El ID del médico a eliminar.
	 * @throws EntityNotFoundException   Si no se encuentra ningún médico con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si ocurre una operación ilegal durante la
	 *                                   operación.
	 */
	void eliminarMedico(Long IdMedico) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Asigna un paciente a un médico.
	 * 
	 * @param idMedico   El ID del médico al que se asignará el paciente.
	 * @param idPaciente El ID del paciente que se asignará al médico.
	 * @return El médico con el paciente asignado.
	 * @throws EntityNotFoundException   Si no se encuentra ningún médico o paciente
	 *                                   con los IDs especificados.
	 * @throws IllegalOperationException Si ocurre una operación ilegal durante la
	 *                                   operación.
	 */
	Medico asignarPaciente(Long idMedico, Long idPaciente) throws EntityNotFoundException, IllegalOperationException;

	/**
	 * Asigna un jefe a un médico.
	 * 
	 * @param idMedico  El ID del médico al que se asignará el jefe.
	 * @param idMedJefe El ID del médico que se asignará como jefe.
	 * @return El médico con el jefe asignado.
	 * @throws EntityNotFoundException   Si no se encuentra ningún médico o jefe con
	 *                                   los IDs especificados.
	 * @throws IllegalOperationException Si ocurre una operación ilegal durante la
	 *                                   operación.
	 */

	Medico asignarJefe(Long idMedico, Long IdMedJefe) throws EntityNotFoundException, IllegalOperationException;

	// Comunicacion entre microservicios
	void eliminarMedicoDepa(Long id);

	Optional<Departamento> asignarDepartamento(Departamento depa, Long idDepa);

	Optional<Departamento> crearDepartamento(Departamento depa, Long idDepa);

	Optional<Departamento> eliminarDepartamento(Departamento depa, Long iddepa);

}
