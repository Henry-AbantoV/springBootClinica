/*
 * @file EspecialidadController.java;
 * @Autor Fernando C.J. (c)2024
 * @Created 5 mar. 2024,01:43:26
 */

package edu.unc.clinica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import edu.unc.clinica.domain.Especialidad;
import edu.unc.clinica.dto.EspecialidadDTO;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.services.EspecialidadService;
import edu.unc.clinica.util.ApiResponse;

// TODO: Auto-generated Javadoc
/**
 * Controlador REST para las operaciones relacionadas con las especialidades médicas.
 * Esta clase maneja las solicitudes HTTP para obtener, crear, actualizar y eliminar especialidades médicas.
 */
@RestController
@RequestMapping(value = "api/especialidades", headers = "Api-Version=1")
public class EspecialidadController {

	/** The espec S. */
	@Autowired
	private EspecialidadService especS;

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
     * Obtiene todas las especialidades médicas.
     * 
     * @return ResponseEntity con la lista de todas las especialidades o un estado sin contenido si no hay ninguna.
     */
	@GetMapping
	public ResponseEntity<?> obtenerTodasEspecialidades() {

		List<Especialidad> especialidad = especS.listarEspecialidades();
		if (especialidad == null || especialidad.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<EspecialidadDTO> espDto = especialidad.stream()
					.map(espec -> modelMapper.map(espec, EspecialidadDTO.class)).collect(Collectors.toList());
			ApiResponse<List<EspecialidadDTO>> response = new ApiResponse<>(true, "Lista de Especialidades", espDto);
			return ResponseEntity.ok(response);
		}
	}
	
	
	/**
     * Obtiene una especialidad médica por su ID.
     * 
     * @param id El ID de la especialidad médica a buscar.
     * @return ResponseEntity con la especialidad médica encontrada.
     * @throws EntityNotFoundException Si la especialidad médica no se encuentra en la base de datos.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) throws EntityNotFoundException {

		Especialidad especialidad = especS.buscarEspecialidadbyId(id);

		EspecialidadDTO especialidadDto = modelMapper.map(especialidad, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Lista de Especialidades", especialidadDto);
		return ResponseEntity.ok(response);
	}
	
	
	/**
     * Guarda una nueva especialidad médica.
     * 
     * @param especDto La especialidad médica a guardar.
     * @param result   El resultado de la validación de la especialidad médica.
     * @return ResponseEntity con la especialidad médica guardada.
     * @throws IllegalOperationException Si se produce un error durante la operación de guardado.
     */
	@PostMapping
	public ResponseEntity<?> guardarEspecialidad(@Valid @RequestBody EspecialidadDTO especDto, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		Especialidad nuevaEsp = modelMapper.map(especDto, Especialidad.class);
		especS.grabarEspecilidad(nuevaEsp);
		EspecialidadDTO saveEspDto = modelMapper.map(nuevaEsp, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad guardada", saveEspDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	/**
     * Actualiza una especialidad médica existente por su ID.
     * 
     * @param espDto  La especialidad médica con los datos actualizados.
     * @param result  El resultado de la validación de la especialidad médica actualizada.
     * @param id      El ID de la especialidad médica a actualizar.
     * @return ResponseEntity con la especialidad médica actualizada.
     * @throws EntityNotFoundException   Si la especialidad médica no se encuentra en la base de datos.
     * @throws IllegalOperationException Si se produce un error durante la operación de actualización.
     */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EspecialidadDTO>> actualizarEspecialidad(@Valid @RequestBody EspecialidadDTO espDto,
			BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {

		Especialidad espActualizada = modelMapper.map(espDto, Especialidad.class);
		especS.actualizarEspecilidad(id, espActualizada);
		EspecialidadDTO updateEsp = modelMapper.map(espActualizada, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad actualizada", updateEsp);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	/**
     * Elimina una especialidad médica por su ID.
     * 
     * @param id El ID de la especialidad médica a eliminar.
     * @return ResponseEntity con un mensaje de éxito.
     * @throws EntityNotFoundException   Si la especialidad médica no se encuentra en la base de datos.
     * @throws IllegalOperationException Si se produce un error durante la operación de eliminación.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		especS.eliminarEspecialidad(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Especialidad eliminada con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
     * Asigna médicos a una especialidad médica por su ID.
     * 
     * @param IdEsp   El ID de la especialidad médica.
     * @param IdMedic El ID del médico a asignar a la especialidad.
     * @return ResponseEntity con la especialidad médica actualizada.
     * @throws EntityNotFoundException   Si la especialidad médica o el médico no se encuentran en la base de datos.
     * @throws IllegalOperationException Si se produce un error durante la operación de asignación de médicos.
     */
	@PatchMapping("/{IdEsp}/asignarMedicos/{IdMedic}")
	public ResponseEntity<?> asignarMedicos( @PathVariable Long IdEsp,
			@PathVariable Long IdMedic) throws EntityNotFoundException, IllegalOperationException {
		
		Especialidad especialidad = especS.asignarMedicos(IdEsp, IdMedic);
		return ResponseEntity.ok(especialidad);
	}
	
	
	 /**
     * Valida los resultados de la validación y devuelve un ResponseEntity con los errores en caso de que haya alguno.
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
