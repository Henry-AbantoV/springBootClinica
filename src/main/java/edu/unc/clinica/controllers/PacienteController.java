/*
 * @file PacienteController.java;
 * @Autor Daniela Torres (c)2024
 * @Created 5 mar 2024,3:30:11
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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
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
import org.springframework.web.servlet.ModelAndView;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.dto.CitaDTO;
import edu.unc.clinica.dto.FacturaDTO;
import edu.unc.clinica.dto.PacienteDTO;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.CitaRepository;
import edu.unc.clinica.repositories.PacienteRepository;
import edu.unc.clinica.services.CitaService;
import edu.unc.clinica.services.PacienteService;
import edu.unc.clinica.util.ApiResponse;

// TODO: Auto-generated Javadoc

@RestController
@RequestMapping(value = "api/pacientes", headers = "Api-Version=1")
public class PacienteController {

	/** The paciente S. */
	@Autowired
	private PacienteService pacienteS;

	@Autowired
	private PacienteRepository pacienteR;

	@Autowired
	private CitaService citaS;
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Endpoint para obtener todos los pacientes registrados.
	 * 
	 * @return ResponseEntity con la lista de pacientes o un estado de no contenido
	 *         si no hay pacientes.
	 */
	@GetMapping
	public ResponseEntity<?> obtenerTodosPacientes() {

		List<Paciente> pacientes = pacienteS.listarPacientes();
		if (pacientes == null || pacientes.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		/*
		 * else { List<PacienteDTO> pacienteDto = pacientes.stream() .map(paciente ->
		 * modelMapper.map(paciente, PacienteDTO.class)).collect(Collectors.toList());
		 * ApiResponse<List<PacienteDTO>> response = new ApiResponse<>(true,
		 * "Lista de pacientes", pacienteDto); return ResponseEntity.ok(response); }
		 */

		for (Paciente paciente : pacientes) {
			paciente.add(linkTo(methodOn(PacienteController.class).obtenerPacientesPorId(paciente.getIdPaciente()))
					.withSelfRel());
			paciente.add(linkTo(methodOn(PacienteController.class).obtenerTodosPacientes())
					.withRel(IanaLinkRelations.COLLECTION));
		}
		CollectionModel<Paciente> modelo = CollectionModel.of(pacientes);
		modelo.add(linkTo(methodOn(PacienteController.class).obtenerTodosPacientes()).withSelfRel());
		return new ResponseEntity<>(pacientes, HttpStatus.OK);

	}

	/**
	 * Endpoint para obtener un paciente específico por su ID.
	 * 
	 * @param id Identificador del paciente.
	 * @return ResponseEntity con la información del paciente solicitado.
	 * @throws EntityNotFoundException Si el paciente no se encuentra.
	 */
	@GetMapping("/{idPaciente}")
	public ResponseEntity<?> obtenerPacientesPorId(@PathVariable Long idPaciente) throws EntityNotFoundException {
		Paciente pacientes = pacienteS.buscarPacienteById(idPaciente);
		if (pacientes == null) {

			throw new EntityNotFoundException("Paciente no encontrado con el ID: " + idPaciente);
		}

		PacienteDTO pacienteDto = modelMapper.map(pacientes, PacienteDTO.class);

		// Crear enlace HATEOAS para el recurso
		pacientes.add(linkTo(methodOn(PacienteController.class).obtenerPacientesPorId(pacientes.getIdPaciente()))
				.withSelfRel());

		return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}

	/**
	 * Endpoint para guardar un nuevo paciente.
	 * 
	 * @param pacienteDto DTO del paciente a guardar.
	 * @param result      BindingResult para manejar errores de validación.
	 * @return ResponseEntity con la información del paciente guardado.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */

	@PostMapping
	public ResponseEntity<?> guardarPaciente(@Valid @RequestBody PacienteDTO pacienteDto, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			// return validar(result);
			Paciente nuevoPaciente = modelMapper.map(pacienteDto, Paciente.class);
			PacienteDTO savePacienteDto = modelMapper.map(nuevoPaciente, PacienteDTO.class);
			ApiResponse<PacienteDTO> rpta = new ApiResponse<>(true, "Revise los campos", savePacienteDto);

			// return ResponseEntity.badRequest().body(rpta);

			// ApiResponse<Object> message = new ApiResponse<>(false,ex.getMessage(), null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rpta);

		}
		Paciente nuevoPaciente = modelMapper.map(pacienteDto, Paciente.class);
		pacienteS.grabarPaciente(nuevoPaciente);
		PacienteDTO savePacienteDto = modelMapper.map(nuevoPaciente, PacienteDTO.class);
		ApiResponse<PacienteDTO> response = new ApiResponse<>(true, "Paciente guardado en la BD", savePacienteDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * Endpoint para actualizar la información de un paciente existente.
	 * 
	 * @param id          Identificador del paciente a actualizar.
	 * @param pacienteDto DTO con la información actualizada del paciente.
	 * @return ResponseEntity con la información del paciente actualizado.
	 * @throws EntityNotFoundException   Si el paciente no se encuentra.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<PacienteDTO>> actualizarPaciente(@PathVariable Long id,
			@RequestBody PacienteDTO pacienteDto) throws EntityNotFoundException, IllegalOperationException {

		Paciente pacienteActualizado = modelMapper.map(pacienteDto, Paciente.class);
		pacienteS.actualizarPaciente(id, pacienteActualizado);
		PacienteDTO updateFactura = modelMapper.map(pacienteActualizado, PacienteDTO.class);
		ApiResponse<PacienteDTO> response = new ApiResponse<>(true, "Datos actualizados del paciente", updateFactura);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	/**
	 * Endpoint para eliminar un paciente por su ID.
	 * 
	 * @param id Identificador del paciente a eliminar.
	 * @return ResponseEntity confirmando la eliminación del paciente.
	 * @throws EntityNotFoundException   Si el paciente no se encuentra.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPaciente(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		pacienteS.eliminarPaciente(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Paciente eliminado con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Endpoint para asignar una cita a un paciente.
	 * 
	 * @param IdPaciente Identificador del paciente.
	 * @param IdCita     Identificador de la cita a asignar.
	 * @return ResponseEntity con el paciente al que se le asignó la cita.
	 * @throws EntityNotFoundException   Si el paciente o la cita no se encuentran.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@PatchMapping("/{IdPaciente}/asignarCita/{IdCita}")
	public ResponseEntity<?> asignarCita(@PathVariable Long IdPaciente, @PathVariable Long IdCita)
			throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacienteS.asignarCita(IdPaciente, IdCita);
		return ResponseEntity.ok(paciente);

	}

	/**
	 * Método privado para manejar errores de validación en la entrada de datos de
	 * los endpoints.
	 * 
	 * @param result BindingResult con los errores de validación.
	 * @return ResponseEntity con los errores formateados.
	 * @throws IllegalOperationException
	 */

	@GetMapping(value = "/{idPaciente}/ObtenerCitas/{idCita}")
	public ResponseEntity<?> obtenerCitaDeLista(@PathVariable Long idPaciente, @PathVariable Long idCita)
			throws EntityNotFoundException, IllegalOperationException {

		Cita cita = pacienteS.obtenerCitaPorId(idPaciente, idCita);

		CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class);
		ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita obtenida con éxito", citaDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene todas las citas asociadas a un paciente.
	 *
	 * @param id Identificador del paciente.
	 * @return ResponseEntity con la lista de citas del paciente solicitado.
	 * @throws EntityNotFoundException Si el paciente no se encuentra.
	 */
	private ResponseEntity<Map<String, String>> validar(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

	/**
	 * Maneja las solicitudes GET para obtener todas las citas de un paciente por su
	 * ID.
	 *
	 * @param idPaciente El ID del paciente para el cual se obtienen las citas.
	 * @return ResponseEntity con la lista de CitaDTO correspondiente al paciente o
	 *         un mensaje de error si no se encuentran citas.
	 * @throws EntityNotFoundException Si el paciente no se encuentra en la base de
	 *                                 datos.
	 */
	@GetMapping("/{idPaciente}/citas")
	public ResponseEntity<?> obtenerCitas(@PathVariable Long idPaciente) throws EntityNotFoundException {
		List<Cita> citas = pacienteS.obtenerCitas(idPaciente);
		List<CitaDTO> citasDTO = citas.stream().map(cita -> modelMapper.map(cita, CitaDTO.class))
				.collect(Collectors.toList());
		ApiResponse<List<CitaDTO>> response = new ApiResponse<>(true, "Citas obtenidas con éxito", citasDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene una cita específica de la lista de citas de un paciente.
	 *
	 * @param idPaciente El ID del paciente del que se desea obtener la cita.
	 * @param idCita     El ID de la cita que se desea obtener.
	 * @return ResponseEntity con la cita obtenida y un mensaje de éxito.
	 * @throws EntityNotFoundException   Si no se encuentra el paciente o la cita
	 *                                   con los IDs proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de
	 *                                   obtención de la cita.
	 */
	@GetMapping("/{idPaciente}/citas/{idCita}")
	public ResponseEntity<?> obtenerCitaDeLaLista(@PathVariable Long idPaciente, @PathVariable Long idCita)
			throws EntityNotFoundException, IllegalOperationException {
		Cita cita = pacienteS.obtenerCitaPorId(idPaciente, idCita);
		CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class);
		ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita obtenida con éxito", citaDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene las facturas asociadas a una cita específica de un paciente.
	 *
	 * @param idPaciente El ID del paciente.
	 * @param idCita     El ID de la cita.
	 * @return ResponseEntity con una lista de FacturaDTO en caso de éxito o un
	 *         mensaje de error.
	 * @throws EntityNotFoundException   Si no se encuentra el paciente o la cita.
	 * @throws IllegalOperationException Si ocurre una operación ilegal.
	 */
	@GetMapping("/{idPaciente}/citas/{idCita}/facturas")
	public ResponseEntity<?> obtenerFacturas(@PathVariable Long idPaciente, @PathVariable Long idCita)
			throws EntityNotFoundException, IllegalOperationException {
		List<Factura> facturas = pacienteS.obtenerFacturas(idPaciente, idCita);
		List<FacturaDTO> facturasDTO = facturas.stream().map(factura -> modelMapper.map(factura, FacturaDTO.class))
				.collect(Collectors.toList());
		ApiResponse<List<FacturaDTO>> response = new ApiResponse<>(true, "Facturas obtenidas con éxito", facturasDTO);
		return ResponseEntity.ok(response);
	}
}
