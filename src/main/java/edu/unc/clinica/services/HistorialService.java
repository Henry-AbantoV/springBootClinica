package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import edu.unc.clinica.domain.HistorialMedico;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

public interface HistorialService {
	/**
     * Retorna una lista de todos los historiales médicos.
     *
     * @return Lista de historiales médicos.
     */
	List<HistorialMedico> listarHistorial();

	/**
     * Busca un historial médico por su ID.
     *
     * @param idHistorial El ID del historial médico a buscar.
     * @return El historial médico encontrado.
     * @throws EntityNotFoundException Si el historial médico no se encuentra en la base de datos.
     */
	HistorialMedico buscarPorIdHistorial(Long idHistorial)throws EntityNotFoundException;
		
	 /**
     * Guarda un nuevo historial médico.
     *
     * @param historial El historial médico a guardar.
     * @return El historial médico guardado.
     * @throws IllegalOperationException Si se produce una operación ilegal al guardar el historial médico.
     */HistorialMedico grabarHistorial(HistorialMedico historial)throws IllegalOperationException;

	
     /**
      * Actualiza un historial médico existente.
      *
      * @param idHistorial El ID del historial médico a actualizar.
      * @param historial   El historial médico actualizado.
      * @return El historial médico actualizado.
      * @throws EntityNotFoundException   Si el historial médico no se encuentra en la base de datos.
      * @throws IllegalOperationException Si se produce una operación ilegal al actualizar el historial médico.
      */
     
     HistorialMedico actualizarHistorial(Long idHistorial, HistorialMedico historial) throws EntityNotFoundException, IllegalOperationException;
	
     /**
      * Elimina un historial médico por su ID.
      *
      * @param idHistorial El ID del historial médico a eliminar.
      * @throws EntityNotFoundException   Si el historial médico no se encuentra en la base de datos.
      * @throws IllegalOperationException Si se produce una operación ilegal al eliminar el historial médico.
      */
     void eliminarHistorial(Long idHistorial) throws EntityNotFoundException, IllegalOperationException;
	
     /**
      * Asigna un historial médico a un paciente existente.
      *
      * @param idHistorial El ID del historial médico a asignar.
      * @param idPaciente  El ID del paciente al que se va a asignar el historial médico.
      * @return El historial médico asignado.
      * @throws EntityNotFoundException   Si el historial médico o el paciente no se encuentran en la base de datos.
      * @throws IllegalOperationException Si se produce una operación ilegal durante la asignación del historial médico.
      */
	HistorialMedico asignarHistorial(Long idHistorial,Long idPaciente) throws EntityNotFoundException, IllegalOperationException;
	
	
}
