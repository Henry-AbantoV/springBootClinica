package edu.unc.clinica.services;

import java.util.List;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Factura;
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
	
	/**
	 * Obtiene la lista de citas asociadas a un paciente
	 *
	 * @param idPaciente El ID del paciente del cual se desean obtener las citas.
	 * @return Una lista de citas asociadas al paciente.
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
    List<Cita> obtenerCitas(Long idPaciente) throws EntityNotFoundException;
    
    /**
	 * Obtiene una reserva especifica asociada a un cliente.
	 *
	 * @param idCliente El ID del cliente al que esta asociada la reserva.
	 * @param idReserva El ID de la reserva que se desea obtener.
	 * @return La reserva especifica asociada al cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la reserva con los IDs proporcionados.
	 * @throws IllegalOperationException Si la reserva no fue realizada por el cliente.
	 */
    Cita obtenerCitaPorId(Long idPaciente,Long idCita) throws EntityNotFoundException,IllegalOperationException ;
	
    /**
   	 * Obtiene la lista de facturas asociadas a una cita de un paciente.
   	 *
   	 * @param idPaciente El ID del paciente al que esta asociada la cita.
   	 * @param idCita El ID de la cita de la cual se desean obtener las facturas.
   	 * @return Una lista de facturas asociadas a la cita del paciente.
   	 * @throws EntityNotFoundException    Si no se encuentra el cliente, la reserva o alguna habitacion asociada.
   	 * @throws IllegalOperationException Si la reserva no pertenece al cliente o no tiene habitaciones.
   	 */
    List<Factura> obtenerFacturas(Long idPaciente, Long idCita) throws EntityNotFoundException,IllegalOperationException;;
    
}
