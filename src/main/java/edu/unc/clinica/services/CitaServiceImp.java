package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.ErrorMessage;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.CitaRepository;
import edu.unc.clinica.repositories.FacturaRepository;
/**
 * La clase CitaServiceImp implementa la interfaz CitaService y proporciona la lógica de negocio para operaciones relacionadas con citas y facturas en el sistema.
 * Está marcada con la anotación @Service para indicar que es un componente de servicio gestionado por Spring.
 */
@Service
public class CitaServiceImp implements CitaService {

	@Autowired
	private CitaRepository citaR;
	
	@Autowired
	private FacturaRepository facturaR;
	
	/**
     * Recupera una lista de todas las citas almacenadas en el sistema.
     *
     * @return Lista de citas.
     */
	@Override
	@Transactional
	public List<Cita> listarCitas() {
		return (List<Cita>)citaR.findAll();
	}

	/**
     * Busca una cita por su identificador único.
     *
     * @param idCita Identificador único de la cita.
     * @return Cita correspondiente al ID proporcionado.
     * @throws EntityNotFoundException Si no se encuentra la cita con el ID especificado.
     */
	@Override
	@Transactional(readOnly=true)
	public Cita buscarCitabyId(Long IdCita) throws EntityNotFoundException  {
		Optional<Cita> cita=citaR.findById(IdCita);
		if(cita.isEmpty()) {
			throw new EntityNotFoundException("La cita con el ID proporcionado no se encontró.");
		}
		return cita.get();
		}

	/**
     * Guarda una nueva cita en el sistema.
     *
     * @param cita La cita a ser guardada.
     * @return Cita guardada.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	@Override
	@Transactional
	public Cita grabarCita(Cita cita) throws IllegalOperationException  {
		return citaR.save(cita);
	}

	/**
     * Elimina una cita del sistema por su identificador único.
     *
     * @param idCita Identificador único de la cita a ser eliminada.
     * @throws EntityNotFoundException  Si no se encuentra la cita con el ID especificado.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	@Override
	@Transactional
	public void eliminarCita(Long IdCita) throws EntityNotFoundException, IllegalOperationException {
		Cita cita=citaR.findById(IdCita).orElseThrow(
				()->new EntityNotFoundException("La cita con id proporcionado no se encontro"));
				
		citaR.deleteById(IdCita);
		
	}
	
	/**
     * Actualiza la información de una cita existente en el sistema.
     *
     * @param id   Identificador único de la cita a ser actualizada.
     * @param cita Nueva información de la cita.
     * @return Cita actualizada.
     * @throws EntityNotFoundException  Si no se encuentra la cita con el ID especificado.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	@Override
	@Transactional
	public Cita actualizarCita(Long id, Cita cita) throws EntityNotFoundException, IllegalOperationException {
		Optional<Cita> citaEntity = citaR.findById(id);
		if(citaEntity.isEmpty())
			throw new EntityNotFoundException("La cita con id proporcionado no fue encontrado");
			
		cita.setIdCita(id);		
		return citaR.save(cita);
	}

	/**
     * Asigna una factura a una cita existente en el sistema.
     *
     * @param idCita    Identificador único de la cita.
     * @param idFactura Identificador único de la factura a ser asignada.
     * @return Cita actualizada con la asignación de factura.
     * @throws EntityNotFoundException  Si no se encuentra la cita o la factura con los ID especificados.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	@Override
	@Transactional
	public Cita asignarFactura(Long IdCita, Long IdFactura) throws EntityNotFoundException, IllegalOperationException {
		try {
		Factura FacturaEntity =  facturaR.findById(IdFactura).orElseThrow(
				()->new EntityNotFoundException("La factura con este id proporcionado no existe en la BD")
				);
		Cita citaEntity = citaR.findById(IdCita).orElseThrow(
				()->new EntityNotFoundException("La cita con este id proporcionado no existe en la BD")
				);
		if (citaEntity.getFactura()== null) {
			citaEntity.setFactura(FacturaEntity);
            return citaR.save(citaEntity);
        } else {
            throw new IllegalOperationException("La cita ya tiene asignada una factura");
        }
		}catch (Exception e) {
	        throw new IllegalOperationException("Error durante la asignación de factura");
	    }
		
	}
	
}
