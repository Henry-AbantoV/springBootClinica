package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.ErrorMessage;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.FacturaRepository;

/**
 * La clase FacturaServiceImp implementa la interfaz FacturaService y
 * proporciona la lógica de negocio para operaciones relacionadas con las
 * facturas en el sistema. Está marcada con la anotación @Service para indicar
 * que es un componente de servicio gestionado por Spring.
 */
@Service
public class FacturaServiceImp implements FacturaService {

	@Autowired
	private FacturaRepository facturaR;

	/**
	 * Recupera una lista de todas las facturas almacenadas en el sistema.
	 *
	 * @return Lista de facturas.
	 */
	@Override
	public List<Factura> listarFacturas() {
		return (List<Factura>) facturaR.findAll();
	}

	/**
	 * Busca una factura por su identificador único.
	 *
	 * @param idFactura Identificador único de la factura.
	 * @return Factura correspondiente al ID proporcionado.
	 * @throws EntityNotFoundException Si no se encuentra la factura con el ID
	 *                                 especificado.
	 */
	@Override
	@Transactional(readOnly = true)
	public Factura buscarFacturabyId(Long IdFactura) throws EntityNotFoundException {
		Optional<Factura> factura = facturaR.findById(IdFactura);
		if (factura.isEmpty()) {
			throw new EntityNotFoundException("La factura con el ID proporcinado no se encontró");
		}
		return factura.get();

	}

	/**
	 * Guarda una nueva factura en el sistema.
	 *
	 * @param factura La factura a ser guardada.
	 * @return Factura guardada.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	@Override
	public Factura grabarFactura(Factura factura) throws IllegalOperationException {
		/*
		 * if(facturaR.findById(factura.getIdFactura())!=null) { throw new
		 * IllegalOperationException("La factura con el ID requerido ya existe"); }
		 */
		return facturaR.save(factura);
	}

	/**
	 * Elimina una factura del sistema por su identificador único.
	 *
	 * @param idFactura Identificador único de la factura a ser eliminada.
	 * @throws EntityNotFoundException   Si no se encuentra la factura con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	@Override
	public void eliminarFactura(Long IdFactura) throws EntityNotFoundException, IllegalOperationException {
		Factura factura = facturaR.findById(IdFactura)
				.orElseThrow(() -> new EntityNotFoundException("La factura con el ID proporcionado no se encontró "));
		facturaR.deleteById(IdFactura);
	}

	/**
	 * Actualiza la información de una factura existente en el sistema.
	 *
	 * @param id      Identificador único de la factura a ser actualizada.
	 * @param factura Nueva información de la factura.
	 * @return Factura actualizada.
	 * @throws EntityNotFoundException   Si no se encuentra la factura con el ID
	 *                                   especificado.
	 * @throws IllegalOperationException Si la operación no es válida.
	 */
	@Override
	@Transactional
	public Factura actualizarFactura(Long id, Factura factura)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Factura> facEntity = facturaR.findById(id);
		if (facEntity.isEmpty())
			throw new EntityNotFoundException("La factura con el ID proporcionado no fue encontrado");
		/*
		 * if(!facturaR.findByCosto(factura.getCosto()).isEmpty()) { throw new
		 * IllegalOperationException("El costo total de la factura ya existe"); }
		 */
		factura.setIdFactura(id);
		;
		return facturaR.save(factura);
	}

}
