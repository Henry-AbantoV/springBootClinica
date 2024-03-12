package edu.unc.clinica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Factura;
import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.CitaRepository;
import edu.unc.clinica.repositories.HistorialMedicoRepository;
import edu.unc.clinica.repositories.PacienteRepository;

@Service
public class PacienteServiceImp implements PacienteService {

	@Autowired
	private PacienteRepository pacientR; // Repositorio para la entidad Paciente

	@Autowired
	private CitaRepository citaR; // Repositorio para la entidad Cita

	@Autowired
	private HistorialMedicoRepository histoR; // Repositorio para la entidad Historial Medico (no se usa en los métodos
												// proporcionados)

	/**
	 * Obtiene una lista de todos los pacientes registrados en el repositorio.
	 * 
	 * @return Lista de objetos {@link Paciente}.
	 */
	@Override
	@Transactional
	public List<Paciente> listarPacientes() {
		return (List<Paciente>) pacientR.findAll();
	}

	/**
	 * Busca un paciente específico por su identificador utilizando el repositorio.
	 * 
	 * @param IdPaciente Identificador único del paciente a buscar.
	 * @return Objeto {@link Paciente} encontrado.
	 * @throws EntityNotFoundException Si el paciente no se encuentra.
	 */
	@Override
	@Transactional(readOnly = true)
	public Paciente buscarPacienteById(Long IdPacient) throws EntityNotFoundException {
		Optional<Paciente> paciente = pacientR.findById(IdPacient);
		if (paciente.isEmpty()) {
			throw new EntityNotFoundException("El paciente con el ID proporcionado no se encontró");
		}
		return paciente.get();
	}

	/**
	 * Registra un nuevo paciente en el sistema mediante el repositorio.
	 * 
	 * @param paciente Objeto {@link Paciente} con la información a registrar.
	 * @return Objeto {@link Paciente} registrado con su identificador asignado.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@Override
	@Transactional
	public Paciente grabarPaciente(Paciente paciente) throws IllegalOperationException {

		// AGREGADO
		if (!pacientR.findBydni(paciente.getDni()).isEmpty()) {
			throw new IllegalOperationException("El DNI del Paciente ya está registrado.");
		} else if (!pacientR.findByCorreoElectronico(paciente.getCorreoElectronico()).isEmpty()) {
			throw new IllegalOperationException("El Correo del Paciente ya está registrado.");
		} else if (!pacientR.findByDireccion(paciente.getDireccion()).isEmpty()) {
			throw new IllegalOperationException("La Direccion del Paciente ya está registrado.");
		} else if (!pacientR.findByTelefono(paciente.getTelefono()).isEmpty()) {
			throw new IllegalOperationException("El Telefono del Paciente ya está registrado.");
		}
		// AGREGADO
		return pacientR.save(paciente);
	}

	/**
	 * Actualiza la información de un paciente existente.
	 * 
	 * @param id       Identificador único del paciente a actualizar.
	 * @param paciente Objeto {@link Paciente} con la información actualizada.
	 * @return Objeto {@link Paciente} actualizado.
	 * @throws EntityNotFoundException   Si el paciente no se encuentra.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@Override
	@Transactional
	public Paciente actualizarPaciente(Long id, Paciente paciente)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Paciente> pacEntity = pacientR.findById(id);
		if (pacEntity.isEmpty())
			throw new EntityNotFoundException("El paciente con el ID proporcionado no fue encontrado");

		if (!pacientR.findBydni(paciente.getDni()).isEmpty()) {
			throw new IllegalOperationException("El DNI del Paciente ya está registrado.");
		}
		if (!pacientR.findByCorreoElectronico(paciente.getCorreoElectronico()).isEmpty()) {
			throw new IllegalOperationException("El Correo del Paciente ya está registrado.");
		}
		if (!pacientR.findByDireccion(paciente.getDireccion()).isEmpty()) {
			throw new IllegalOperationException("La Direccion del Paciente ya está registrado.");
		}
		if (!pacientR.findByTelefono(paciente.getTelefono()).isEmpty()) {
			throw new IllegalOperationException("El Telefono del Paciente ya está registrado.");
		}
		paciente.setIdPaciente(id);
		return pacientR.save(paciente);
	}

	/**
	 * Elimina un paciente del sistema identificado por su ID.
	 * 
	 * @param IdPaciente Identificador único del paciente a eliminar.
	 * @throws EntityNotFoundException   Si el paciente no se encuentra.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@Override
	@Transactional
	public void eliminarPaciente(Long IdPacient) throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacientR.findById(IdPacient).orElseThrow(
				() -> new EntityNotFoundException("El paciente con el ID proporcionado no fue encontrado"));
		pacientR.deleteById(IdPacient);

	}

	/**
	 * Asigna una cita a un paciente.
	 * 
	 * @param IdPaciente Identificador único del paciente.
	 * @param IdCita     Identificador único de la cita a asignar.
	 * @return Objeto {@link Paciente} al que se le asignó la cita.
	 * @throws EntityNotFoundException   Si el paciente o la cita no se encuentran.
	 * @throws IllegalOperationException Si la operación no cumple con las reglas de
	 *                                   negocio.
	 */
	@Override
	@Transactional
	public Paciente asignarCita(Long IdPacient, Long IdCita) throws EntityNotFoundException, IllegalOperationException {
		// Intenta encontrar el paciente y la cita y luego los asocia si la cita no
		// tiene ya un paciente asignado.
		try {
			Paciente pacienteEntity = pacientR.findById(IdPacient).orElseThrow(
					() -> new EntityNotFoundException("El paciente con el ID proporcionado no fue encontrado"));
			Cita citaEntity = citaR.findById(IdCita).orElseThrow(
					() -> new EntityNotFoundException("La cita con el ID proporcionado no fue encontrado"));

			if (citaEntity.getPaciente() == null) {
				citaEntity.setPaciente(pacienteEntity);
				return pacientR.save(pacienteEntity);
			} else {
				throw new IllegalOperationException("El paciente ya tiene asignada una cita");
			}
		} catch (Exception e) {
			throw new IllegalOperationException("Error durante la asignación de cita");
		}
	}

	@Override
	public List<Cita> obtenerCitasPaciente(Long idPaciente) throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		Paciente paciente = pacientR.findById(idPaciente).orElseThrow(
				() -> new EntityNotFoundException("El paciente con el ID proporcionado no fue encontrado"));
		return paciente.getCitas();
	}

	/**
	 * Obtiene una reserva especifica asociada a un cliente.
	 *
	 * @param idPaciente El ID del paciente al que esta asociada la cita.
	 * @param idCita     El ID de la cita que se desea obtener.
	 * @return La cita especifica asociada al paciente.
	 * @throws EntityNotFoundException   Si no se encuentra el paciente o la cita
	 *                                   con los IDs proporcionados.
	 * @throws IllegalOperationException Si la cita no fue realizada por el
	 *                                   paciente.
	 */
	@Override
	public Cita obtenerCitaPorId(Long idPaciente, Long idCita)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		Paciente pacienteEntity = pacientR.findById(idPaciente).orElseThrow(
				() -> new EntityNotFoundException("El paciente con el ID proporcionado no fue encontrado"));
		Cita citaEntity = citaR.findById(idCita)
				.orElseThrow(() -> new EntityNotFoundException("La cita con el ID proporcionado no fue encontrado"));

		List<Cita> citaPaciente = pacienteEntity.getCitas();

		if (citaPaciente.contains(citaEntity)) {
			return citaEntity;

		} else {
			throw new IllegalOperationException("La cita no fue realizada por el paciente");
		}
	}

	/**
	 * Obtiene todas las citas de un paciente por su ID.
	 *
	 * @param idPaciente El ID del paciente para el cual se obtienen las citas.
	 * @return Lista de objetos Cita correspondiente al paciente.
	 * @throws EntityNotFoundException Si el paciente no se encuentra en la base de
	 *                                 datos.
	 */
	@Override
	public List<Cita> obtenerCitas(Long idPaciente) throws EntityNotFoundException {
		Paciente paciente = pacientR.findById(idPaciente).orElseThrow();
		return paciente.getCitas();
	}
	
	/**
	 * Obtiene las facturas asociadas a una cita de un paciente.
	 *
	 * @param idPaciente El ID del paciente.
	 * @param idCita     El ID de la cita.
	 * @return Lista de objetos Factura asociada a la cita y paciente.
	 * @throws EntityNotFoundException     Si el paciente o la cita no se encuentran en la base de datos.
	 * @throws IllegalOperationException   Si la cita no pertenece al paciente o si la cita no tiene factura.
	 */
	@Override
	public List<Factura> obtenerFacturas(Long idPaciente, Long idCita)
			throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacientR.findById(idPaciente).orElseThrow();
		Cita cita = citaR.findById(idCita).orElseThrow();
		List<Cita> citasPaciente = paciente.getCitas();
		List<Factura> facturas = new ArrayList<>();

		if (citasPaciente.contains(cita)) {
			if (facturas.isEmpty()) {
				throw new IllegalOperationException("La cita no tiene factura");
			} else {
				return facturas;
			}
		} else {
			throw new IllegalOperationException("La cita no pertenece al paciente");
		}
	}
}
