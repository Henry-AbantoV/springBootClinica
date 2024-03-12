/*
 * @file FacturaController.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,0:04:36
 */
package edu.unc.clinica.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.unc.clinica.dto.PacienteResponseDTO;
import edu.unc.clinica.dto.FacturaResponseDTO;
import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.dto.FacturaDTO;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.FacturaRepository;
import edu.unc.clinica.services.FacturaService;
import edu.unc.clinica.util.ApiResponse;

// TODO: Auto-generated Javadoc
/**
 * Controlador REST que maneja las operaciones relacionadas con las facturas.
 */
@RestController
@RequestMapping(value = "api/facturas", headers = "Api-Version=1")
public class FacturaController {

	/** The factura S. */
	@Autowired
	private FacturaService facturaS;

		/**
	     * Maneja las solicitudes GET para obtener todas las facturas.
	     * @return ResponseEntity con una lista de FacturaDTO en caso de éxito o un mensaje de error si no hay facturas.
	     */
	    @GetMapping
	    public ResponseEntity<?> obtenerTodasFacturas() {
	     
	            List<Factura> facturas = facturaS.listarFacturas();
	            if(facturas==null || facturas.isEmpty()) {
	            	return ResponseEntity.noContent().build();
	            }
	            for(Factura factura:facturas) {
	            	factura.add(linkTo(methodOn(FacturaController.class).obtenerFacturasPorId(factura.getIdFactura())).withSelfRel());
	                factura.add(linkTo(methodOn(FacturaController.class).obtenerTodasFacturas()).withRel(IanaLinkRelations.COLLECTION));
	            }
	            CollectionModel<Factura> modelo = CollectionModel.of(facturas);
	            modelo.add(linkTo(methodOn(EspecialidadController.class).obtenerTodasEspecialidades()).withSelfRel());
	            return new ResponseEntity<>(facturas, HttpStatus.OK);
	    }

	/** El modelMapper. */
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FacturaRepository facturaRepository;

	@GetMapping
	public ResponseEntity<List<FacturaResponseDTO>> getFactura() {
	    List<Factura> facturas = facturaRepository.findAll();

	    List<FacturaResponseDTO> facturasResponse = facturas.stream()
	        .map(factura -> {
	            List<PacienteResponseDTO> citasResponse = factura.getCitas().stream()
	                .map(cita -> new PacienteResponseDTO(
	                    cita.getPaciente().getNombres(),
	                    cita.getPaciente().getApellidos()
	                ))
	                .collect(Collectors.toList());

	            return new FacturaResponseDTO(
	                    factura.getIdFactura(),
	                    LocalDateTime.ofInstant(factura.getFechaEmision().toInstant(), ZoneId.systemDefault()),
	                    factura.getDescripServicios(),
	                    factura.getPagoRealizados(),
	                    factura.getSaldoPendiente(),
	                    factura.getCosto(),
	                    citasResponse
	                );
	            })
	            .collect(Collectors.toList());

	        return new ResponseEntity<>(facturasResponse, HttpStatus.OK);
	    }
	
	/**
	 * Maneja las solicitudes GET para obtener todas las facturas.
	 * 
	 * @return ResponseEntity con una lista de FacturaDTO en caso de éxito o un
	 *         mensaje de error si no hay facturas.
	 */
	@GetMapping("/api/facturas/all")
	public ResponseEntity<List<FacturaResponseDTO>> getFacturas() {
	    List<Factura> facturas = facturaS.listarFacturas();
	    if (facturas == null || facturas.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        List<FacturaResponseDTO> facturaDto = facturas.stream().map(factura -> modelMapper.map(factura, FacturaResponseDTO.class))
	                .collect(Collectors.toList());
	        ApiResponse<List<FacturaResponseDTO>> response = new ApiResponse<>(true, "Lista de facturas", facturaDto);
	        return ResponseEntity.ok(response.getData());
	    }
	}

	/**
	 * Maneja las solicitudes GET para obtener una factura por su ID.
	 *
	 * @param id El ID de la factura.
	 * @return ResponseEntity con la FacturaDTO correspondiente al ID o un mensaje
	 *         de error si no se encuentra la factura.
	 * @throws EntityNotFoundException the entity not found exception
	 */

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerFacturasPorId(@PathVariable Long id) throws EntityNotFoundException {

		Factura facturas = facturaS.buscarFacturabyId(id);

		FacturaDTO facturaDto = modelMapper.map(facturas, FacturaDTO.class);
		ApiResponse<FacturaDTO> response = new ApiResponse<>(true, "Lista de facturas", facturaDto);
		return ResponseEntity.ok(response);

	}

	/**
=======
>>>>>>> 2a01e206b875989fb282018fea26be5e4cc2bcfe
	 * Maneja las solicitudes POST para guardar una nueva factura.
	 *
	 * @param facturaDto La factura a guardar.
	 * @param result     the result
	 * @return ResponseEntity con la FacturaDTO guardada en caso de éxito o un
	 *         mensaje de error si falla la operación.
	 * @throws IllegalOperationException Si ocurre una operación ilegal.
	 */

	@PostMapping
	public ResponseEntity<?> guardarFactura(@Valid @RequestBody FacturaDTO facturaDto, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		Factura nuevaFactura = modelMapper.map(facturaDto, Factura.class);
		facturaS.grabarFactura(nuevaFactura);
		FacturaDTO saveFacturaDto = modelMapper.map(nuevaFactura, FacturaDTO.class);
		ApiResponse<FacturaDTO> response = new ApiResponse<>(true, "Factura guardada", saveFacturaDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	/**
	 * Maneja las solicitudes PUT para actualizar una factura existente.
	 * 
	 * @param id         El ID de la factura a actualizar.
	 * @param facturaDto La factura actualizada.
	 * @return ResponseEntity con la FacturaDTO actualizada en caso de éxito o un
	 *         mensaje de error si falla la operación.
	 * @throws EntityNotFoundException   Si no se encuentra la factura.
	 * @throws IllegalOperationException Si ocurre una operación ilegal.
	 */

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<FacturaDTO>> actualizarFactura(@PathVariable Long id,
			@RequestBody FacturaDTO facturaDto) throws EntityNotFoundException, IllegalOperationException {

		Factura facturaActualizada = modelMapper.map(facturaDto, Factura.class);
		facturaS.actualizarFactura(id, facturaActualizada);
		FacturaDTO updateFactura = modelMapper.map(facturaActualizada, FacturaDTO.class);
		ApiResponse<FacturaDTO> response = new ApiResponse<>(true, "Factura actualizada", updateFactura);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	/**
	 * Maneja las solicitudes DELETE para eliminar una factura por su ID.
	 * 
	 * @param id El ID de la factura a eliminar.
	 * @return ResponseEntity con un mensaje de éxito si la factura se elimina
	 *         correctamente o un mensaje de error si falla la operación.
	 * @throws EntityNotFoundException   Si no se encuentra la factura.
	 * @throws IllegalOperationException Si ocurre una operación ilegal.
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarFactura(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		facturaS.eliminarFactura(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Factura eliminada con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Valida los resultados de la validación y devuelve un ResponseEntity con los
	 * errores en caso de que haya alguno.
	 *
	 * @param result El resultado de la validación.
	 * @return ResponseEntity que contiene un mapa de errores.
	 */
	private ResponseEntity<Map<String, String>> validar(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}


}
