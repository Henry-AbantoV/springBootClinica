/*
 * @file MedicoServiceImp.java;
 * @Autor YerssonC.D (c)2024
 * @Created 5 mar 2024,0:32:42
 */
package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.clinica.clients.DepaClientRest;
import edu.unc.clinica.domain.Medico;
import edu.unc.clinica.domain.MedicoDepartamento;
import edu.unc.clinica.domain.Paciente;
import edu.unc.clinica.domainModels.Departamento;
import edu.unc.clinica.exceptions.EntityNotFoundException;

import edu.unc.clinica.exceptions.IllegalOperationException;
import edu.unc.clinica.repositories.MedicoRepository;
import edu.unc.clinica.repositories.PacienteRepository;
import jakarta.persistence.PersistenceException;

/**
 * Implementación de {@link MedicoService} que proporciona métodos para
 * gestionar la entidad Medico.
 */
@Service
public class MedicoServiceImp implements MedicoService {

	@Autowired
	private MedicoRepository medicoRep;

	@Autowired
	private PacienteRepository pacienteRep;

	@Autowired
	private DepaClientRest depaClient;

	/**
	 * Obtiene una lista de todos los médicos.
	 *
	 * @return Lista de médicos.
	 */

	@Override
	@Transactional
	public List<Medico> listarMedicos() {
		return medicoRep.findAll();
	}

	/**
	 * Busca un médico por su ID.
	 *
	 * @param idMedico El ID del médico a buscar.
	 * @return El médico encontrado.
	 * @throws EntityNotFoundException Si no se encuentra el médico con el ID
	 *                                 proporcionado.
	 */
	@Override
	@Transactional(readOnly = true)
	public Medico buscarMedicoById(Long idMedico) throws EntityNotFoundException {
		Optional<Medico> medico = medicoRep.findById(idMedico);

		if (medico.isEmpty())
			throw new EntityNotFoundException("El medico con el id proporcionado no existe en la BD");
		return medico.get();

	}

	/**
	 * Guarda un nuevo médico en la base de datos.
	 *
	 * @param medico El médico a guardar.
	 * @return El médico guardado.
	 * @throws IllegalOperationException Si ocurre un error durante la operación
	 *                                   ilegal.
	 */
	@Override
	@Transactional
	public Medico grabarMedico(Medico medico) throws IllegalOperationException {

		return medicoRep.save(medico);
	}

	/**
	 * Actualiza un médico existente en la base de datos.
	 *
	 * @param id     El ID del médico a actualizar.
	 * @param medico El médico actualizado.
	 * @return El médico actualizado.
	 * @throws EntityNotFoundException   Si no se encuentra el médico con el ID
	 *                                   proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operación
	 *                                   ilegal.
	 */
	@Override
	@Transactional
	public Medico actualizarMedico(Long id, Medico medico) throws EntityNotFoundException, IllegalOperationException {
		Optional<Medico> medicoEntity = medicoRep.findById(id);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("El medico con este id no fue encontrado");

		medico.setIdMedico(id);
		return medicoRep.save(medico);

	}

	/**
	 * Elimina un médico de la base de datos.
	 *
	 * @param IdMedico El ID del médico a eliminar.
	 * @throws EntityNotFoundException   Si no se encuentra el médico con el ID
	 *                                   proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operación
	 *                                   ilegal.
	 */
	@Override
	@Transactional
	public void eliminarMedico(Long IdMedico) throws EntityNotFoundException, IllegalOperationException {
		Medico especialidad = medicoRep.findById(IdMedico)
				.orElseThrow(() -> new EntityNotFoundException("El medico con id proporcionado no se elimino"));

		medicoRep.deleteById(IdMedico);
	}

	/**
	 * Asigna un paciente a un médico.
	 *
	 * @param idMedico   El ID del médico al que se asignará el paciente.
	 * @param idPaciente El ID del paciente que se asignará al médico.
	 * @return El médico actualizado con el paciente asignado.
	 * @throws EntityNotFoundException   Si no se encuentra el médico o el paciente
	 *                                   con los ID proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación
	 *                                   ilegal.
	 */
	@Override
	@Transactional
	public Medico asignarPaciente(Long idMedico, Long idPaciente)
			throws EntityNotFoundException, IllegalOperationException {
		try {
			Medico medicoEntity = medicoRep.findById(idMedico)
					.orElseThrow(() -> new EntityNotFoundException("Medico no encontrado con ID: " + idMedico));

			Paciente paciente = pacienteRep.findById(idPaciente)
					.orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + idPaciente));

			if (!medicoEntity.getPacientes().contains(paciente)) {
				medicoEntity.getPacientes().add(paciente);
				return medicoRep.save(medicoEntity);
			} else {
				throw new IllegalOperationException("Este paciente ya está asignado a este médico");
			}
		} catch (Exception e) {
			// Manejar cualquier excepción de persistencia aquí
			// Por ejemplo, puedes lanzar una excepción personalizada, hacer un registro de
			// error, etc.
			throw new PersistenceException("Error al asignar paciente al médico", e);
		}
	}

	/**
	 * Asigna un médico como jefe de otro médico.
	 *
	 * @param idMedico  El ID del médico al que se asignará un jefe.
	 * @param IdMedJefe El ID del médico que será el jefe.
	 * @return El médico actualizado con el jefe asignado.
	 * @throws EntityNotFoundException   Si no se encuentra el médico o el jefe con
	 *                                   los ID proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación
	 *                                   ilegal.
	 */
	@Override
	@Transactional
	public Medico asignarJefe(Long idMedico, Long IdMedJefe) throws EntityNotFoundException, IllegalOperationException {

		Medico medico = medicoRep.findById(idMedico)
				.orElseThrow(() -> new EntityNotFoundException("El medico con este Id no se ha encontrado"));

		Medico jefe = medicoRep.findById(IdMedJefe)
				.orElseThrow(() -> new EntityNotFoundException("El medico jefe no se ha encontrado"));

		if (medico.equals(jefe)) {
			throw new IllegalOperationException("Un medico no puede supervisarse a sí mismo");
		}

		medico.setJefe(jefe);
		return medicoRep.save(medico);
	}

	///////////////////////// implementacion de los metodos en la comunicacion de
	///////////////////////// microservicios
	@Override
	@Transactional
	public Optional<Departamento> asignarDepartamento(Departamento depa, Long idDepa) {
		Optional<Medico> o = medicoRep.findById(idDepa);
		if (o.isPresent()) {
			Departamento depaC = depaClient.detalleDepaMedicos(depa.getIdDepartamento());

			Medico medico = o.get();
			MedicoDepartamento medepa = new MedicoDepartamento();
			medepa.setDepaId(depaC.getIdDepartamento());

			medico.addmedicoDepa(medepa);
			medicoRep.save(medico);
			return Optional.of(depaC);
		}

		return Optional.empty();
	}

	@Override
	public void eliminarMedicoDepa(Long id) {
		medicoRep.eliminarMedicoDepaPorId(id);
	}

	@Override
	@Transactional
	public Optional<Departamento> crearDepartamento(Departamento depa, Long idDepa) {
		Optional<Medico> o = medicoRep.findById(idDepa);
		if (o.isPresent()) {
			Departamento depaC = depaClient.crearDepartamento(depa);

			Medico medico = o.get();
			MedicoDepartamento medepa = new MedicoDepartamento();
			medepa.setDepaId(depaC.getIdDepartamento());

			medico.addmedicoDepa(medepa);
			medicoRep.save(medico);
			return Optional.of(depaC);
		}

		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<Departamento> eliminarDepartamento(Departamento depa, Long idDepa) {
		Optional<Medico> o = medicoRep.findById(idDepa);
		if (o.isPresent()) {
			Departamento depaC = depaClient.detalleDepaMedicos(depa.getIdDepartamento());

			Medico medico = o.get();
			MedicoDepartamento medepa = new MedicoDepartamento();
			medepa.setDepaId(depaC.getIdDepartamento());

			medico.removeMedicoDepa(medepa);
			medicoRep.save(medico);
			return Optional.of(depaC);
		}

		return Optional.empty();
	}
}
