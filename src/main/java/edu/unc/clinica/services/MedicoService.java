package edu.unc.clinica.services;

import java.util.List;
import java.util.Optional;

import edu.unc.clinica.domain.Medico;
import edu.unc.clinica.domainModels.Departamento;
import edu.unc.clinica.exceptions.EntityNotFoundException;
import edu.unc.clinica.exceptions.IllegalOperationException;

public interface MedicoService {

	List<Medico> listarMedicos();
	Medico buscarMedicoById(Long idMedico) throws EntityNotFoundException;
	Medico grabarMedico(Medico medico)throws IllegalOperationException;
	Medico actualizarMedico(Long id, Medico medico) throws EntityNotFoundException, IllegalOperationException;
	void eliminarMedico(Long IdMedico) throws EntityNotFoundException, IllegalOperationException;
	Medico asignarPaciente(Long idMedico, Long idPaciente) throws EntityNotFoundException, IllegalOperationException;	
	Medico asignarJefe(Long idMedico, Long IdMedJefe) throws EntityNotFoundException, IllegalOperationException;
	
	//Comunicacion entre microservicios
	void eliminarMedicoDepa(Long id);
	Optional<Departamento>asignarDepartamento(Departamento depa, Long idMedico);
	Optional<Departamento> crearDepartamento(Departamento depa, Long idMedico);
	Optional<Departamento> eliminarDepartamento(Departamento depa, Long idMedico);

}
