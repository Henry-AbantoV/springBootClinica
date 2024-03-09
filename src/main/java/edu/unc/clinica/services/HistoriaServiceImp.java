package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.clinica.domain.HistorialMedico;
import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.HistorialMedicoRepository;
import edu.unc.clinica.repositories.PacienteRepository;

@Service
public class HistoriaServiceImp implements HistorialService {

    /**
     * Repositorio para acceder a los historiales médicos en la base de datos.
     */
	@Autowired
	private HistorialMedicoRepository historialR;
	
	 /**
     * Repositorio para acceder a los pacientes en la base de datos.
     */
	@Autowired
	private PacienteRepository pacienteR;

	/**
    * Retorna una lista de todos los historiales médicos.
    *
    * @return Lista de historiales médicos.
    */
	@Override
	@Transactional
	public List<HistorialMedico> listarHistorial() {
		return (List<HistorialMedico>)historialR.findAll();
	}

	/**
     * Busca un historial médico por su ID.
     *
     * @param idHistorial El ID del historial médico a buscar.
     * @return El historial médico encontrado.
     * @throws EntityNotFoundException Si el historial médico no se encuentra en la base de datos.
     */
	@Override
	@Transactional(readOnly=true)
	public HistorialMedico buscarPorIdHistorial(Long idHistorial) throws EntityNotFoundException {

		Optional<HistorialMedico> historial=historialR.findById(idHistorial);
		if(historial.isEmpty()) {
			throw new EntityNotFoundException("El historial con el ID proporcionado no se encontró.");
		}
		return historial.get();
		
	}

	/*@Override
	@Transactional
	public Optional<HistorialMedico> buscarPorDniPaciente(String dniPaciente) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return historialR.findByPaciente_Dni(dniPaciente);
	}*/

	/**
     * Guarda un nuevo historial médico.
     *
     * @param historial El historial médico a guardar.
     * @return El historial médico guardado.
     * @throws IllegalOperationException Si se produce una operación ilegal al guardar el historial médico.
     */

	@Override
	@Transactional
	public HistorialMedico grabarHistorial(HistorialMedico historial) throws IllegalOperationException {
		return historialR.save(historial);
	}

	/**
     * Elimina un historial médico por su ID.
     *
     * @param idHistorial El ID del historial médico a eliminar.
     * @throws EntityNotFoundException   Si el historial médico no se encuentra en la base de datos.
     * @throws IllegalOperationException Si se produce una operación ilegal al eliminar el historial médico.
     */
	@Override
	@Transactional
	public void eliminarHistorial(Long idHistorial) throws EntityNotFoundException, IllegalOperationException {
		HistorialMedico historial=historialR.findById(idHistorial).orElseThrow(
				()->new EntityNotFoundException("El historial con id proporcionado no se encontró"));
		historialR.deleteById(idHistorial);
	}
	
	  /**
     * Actualiza un historial médico existente.
     *
     * @param idHistorial El ID del historial médico a actualizar.
     * @param historial   El historial médico actualizado.
     * @return El historial médico actualizado.
     * @throws EntityNotFoundException   Si el historial médico no se encuentra en la base de datos.
     * @throws IllegalOperationException Si se produce una operación ilegal al actualizar el historial médico.
     */
	@Override
	@Transactional
	public HistorialMedico actualizarHistorial(Long idHistorial, HistorialMedico historial) throws EntityNotFoundException, IllegalOperationException{
		Optional<HistorialMedico> historialEntity = historialR.findById(idHistorial);
		if(historialEntity.isEmpty())
			throw new EntityNotFoundException("El historial medico con id proporcionado no fue encontrado");
			
		historial.setIdHistorialMedico(idHistorial);		
		return historialR.save(historial);		
	}
	
	/**
     * Asigna un historial médico a un paciente existente.
     *
     * @param idHistorial El ID del historial médico a asignar.
     * @param idPaciente  El ID del paciente al que se va a asignar el historial médico.
     * @return El historial médico asignado.
     * @throws EntityNotFoundException   Si el historial médico o el paciente no se encuentran en la base de datos.
     * @throws IllegalOperationException Si se produce una operación ilegal durante la asignación del historial médico.
     */
	@Override
	@Transactional
	public HistorialMedico asignarHistorial(Long idHistorial,Long idPaciente) throws EntityNotFoundException, IllegalOperationException {

		try {
			Paciente pacienteEntity =  pacienteR.findById(idPaciente).orElseThrow(
					()->new EntityNotFoundException("Paciente no encontrado")
					);
			HistorialMedico histEntity = historialR.findById(idHistorial).orElseThrow(
					()->new EntityNotFoundException("Historial medico no encontrado")
					);
			if (pacienteEntity.getHistorialMedico()== null) {
				histEntity.setPaciente(pacienteEntity);
				return historialR.save(histEntity);
	        } else {
	            throw new IllegalOperationException("El paciente ya tiene asignado un historial");
	        }
			}catch (Exception e) {
		        throw new IllegalOperationException("Error durante la asignación de historial");
		    }
	
	}

}
