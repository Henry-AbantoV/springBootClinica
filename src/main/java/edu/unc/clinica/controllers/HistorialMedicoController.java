/*
 * @file HistorialMedicoController.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,0:04:26
 */
package edu.unc.clinica.controllers;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.domain.HistorialMedico;
import edu.unc.clinica.dto.HistorialDTO;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.services.HistorialService;
import edu.unc.clinica.util.ApiResponse;

// TODO: Auto-generated Javadoc
/**
 * Controlador REST que maneja las operaciones relacionadas con los historiales médicos.
 */
@RestController
@RequestMapping(value = "api/historial", headers = "Api-Version=1")
public class HistorialMedicoController {
	
    /** The historial S. */
    // Inyección de dependencias de los servicios necesarios
	@Autowired
	private HistorialService historialS;
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	  /**
     * Método para obtener todos los historiales médicos.
     *
     * @return ResponseEntity con la lista de historiales médicos o sin contenido si no hay historiales.
     */
	@GetMapping
	public ResponseEntity<?> obtenerTodosHistoriales() {

		List<HistorialMedico> historial = historialS.listarHistorial();
		if (historial == null || historial.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		for(HistorialMedico historiales:historial) {
        	historiales.add(linkTo(methodOn(HistorialMedicoController.class).obtenerHistorialesPorId(historiales.getIdHistorialMedico())).withSelfRel());
            historiales.add(linkTo(methodOn(HistorialMedicoController.class).obtenerTodosHistoriales()).withRel(IanaLinkRelations.COLLECTION));
        }
        CollectionModel<HistorialMedico> modelo = CollectionModel.of(historial);
        modelo.add(linkTo(methodOn(EspecialidadController.class).obtenerTodasEspecialidades()).withSelfRel());
        return new ResponseEntity<>(historial, HttpStatus.OK);
	}

	/**
     * Método para obtener un historial médico por su ID.
     *
     * @param id ID del historial médico.
     * @return ResponseEntity con el historial médico correspondiente al ID proporcionado.
     * @throws EntityNotFoundException Si no se encuentra el historial médico con el ID especificado.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerHistorialesPorId(@PathVariable Long id) throws EntityNotFoundException {

		HistorialMedico historial = historialS.buscarPorIdHistorial(id);
		HistorialDTO historialDto = modelMapper.map(historial, HistorialDTO.class);
		ApiResponse<HistorialDTO> response = new ApiResponse<>(true, "Lista de Historiales", historialDto);
		historial.add(linkTo(methodOn(HistorialMedicoController.class).obtenerHistorialesPorId(historial.getIdHistorialMedico())).withSelfRel());
		return ResponseEntity.ok(response);
	}

	 /**
     * Método para guardar un nuevo historial médico.
     *
     * @param historialDto Objeto DTO del historial médico a guardar.
     * @param result       Resultado de la validación de datos.
     * @return ResponseEntity con el historial médico guardado.
     * @throws IllegalOperationException Si se produce una operación ilegal al intentar guardar el historial médico.
     */
	@PostMapping
	public ResponseEntity<?> guardarHistorial(@Valid @RequestBody HistorialDTO historialDto, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		HistorialMedico nuevoHistorial = modelMapper.map(historialDto, HistorialMedico.class);
		historialS.grabarHistorial(nuevoHistorial);
		HistorialDTO saveHistorialDto = modelMapper.map(nuevoHistorial, HistorialDTO.class);

		ApiResponse<HistorialDTO> response = new ApiResponse<>(true, "Historial médico guardado", saveHistorialDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}


	 /**
     * Método para actualizar un historial médico existente.
     *
     * @param id           ID del historial médico a actualizar.
     * @param historialDto Objeto DTO con los datos actualizados del historial médico.
     * @return ResponseEntity con el historial médico actualizado.
     * @throws EntityNotFoundException    Si no se encuentra el historial médico con el ID especificado.
     * @throws IllegalOperationException Si se produce una operación ilegal al intentar actualizar el historial médico.
     */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<HistorialDTO>> actualizarHistorial(@PathVariable Long id,
			@RequestBody HistorialDTO historialDto) throws EntityNotFoundException, IllegalOperationException {

		HistorialMedico historialActualizado = modelMapper.map(historialDto, HistorialMedico.class);
		historialS.actualizarHistorial(id, historialActualizado);
		HistorialDTO updateHistorial = modelMapper.map(historialActualizado, HistorialDTO.class);
		ApiResponse<HistorialDTO> response = new ApiResponse<>(true, "Historial actualizada", updateHistorial);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
     * Método para eliminar un historial médico por su ID.
     *
     * @param id ID del historial médico a eliminar.
     * @return ResponseEntity con un mensaje indicando el éxito de la operación de eliminación.
     * @throws EntityNotFoundException    Si no se encuentra el historial médico con el ID especificado.
     * @throws IllegalOperationException Si se produce una operación ilegal al intentar eliminar el historial médico.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarHistorial(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		historialS.eliminarHistorial(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Historial médico eliminado con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
     * Método para asignar un historial médico a un paciente.
     *
     * @param idHistorial ID del historial médico a asignar.
     * @param idPaciente  ID del paciente al que se asignará el historial médico.
     * @return ResponseEntity con el historial médico asignado al paciente.
     * @throws EntityNotFoundException    Si no se encuentra el historial médico o el paciente con los IDs especificados.
     * @throws IllegalOperationException Si se produce una operación ilegal al intentar asignar el historial médico al paciente.
     */
	
	@PatchMapping("/{idHistorial}/asignarPaciente/{idPaciente}")
	public ResponseEntity<?> asignarPaciente(@PathVariable Long idHistorial, @PathVariable Long idPaciente)
			throws EntityNotFoundException, IllegalOperationException {
		HistorialMedico historial = historialS.asignarHistorial(idHistorial, idPaciente);
		return ResponseEntity.ok(historial);

	}
	
	/**
     * Método para manejar errores de validación.
     *
     * @param result Resultado de la validación de datos.
     * @return ResponseEntity con los errores de validación.
     */
	private ResponseEntity<Map<String, String>> validar(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
}
