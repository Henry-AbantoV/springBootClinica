package edu.unc.clinica.services;

import java.util.List;

import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
/**
 * La interfaz FacturaService proporciona métodos para gestionar las operaciones relacionadas con las facturas en el sistema.
 * Define operaciones como listar facturas, buscar factura por ID, grabar factura, actualizar factura y eliminar factura.
 */
public interface FacturaService {
	/**
     * Recupera una lista de todas las facturas almacenadas en el sistema.
     *
     * @return Lista de facturas.
     */
	List<Factura> listarFacturas();
	/**
     * Busca una factura por su identificador único.
     *
     * @param idFactura Identificador único de la factura.
     * @return Factura correspondiente al ID proporcionado.
     * @throws EntityNotFoundException Si no se encuentra la factura con el ID especificado.
     */
	Factura buscarFacturabyId(Long IdFactura) throws EntityNotFoundException;
	
	/**
     * Guarda una nueva factura en el sistema.
     *
     * @param factura La factura a ser guardada.
     * @return Factura guardada.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	Factura grabarFactura (Factura factura) throws IllegalOperationException ;
	
	/**
     * Actualiza la información de una factura existente en el sistema.
     *
     * @param id      Identificador único de la factura a ser actualizada.
     * @param factura Nueva información de la factura.
     * @return Factura actualizada.
     * @throws EntityNotFoundException  Si no se encuentra la factura con el ID especificado.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	Factura actualizarFactura(Long id, Factura factura) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Elimina una factura del sistema por su identificador único.
     *
     * @param idFactura Identificador único de la factura a ser eliminada.
     * @throws EntityNotFoundException  Si no se encuentra la factura con el ID especificado.
     * @throws IllegalOperationException Si la operación no es válida.
     */
	void eliminarFactura(Long IdFactura) throws EntityNotFoundException, IllegalOperationException;
}
