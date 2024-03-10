package edu.unc.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.unc.clinica.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {	
	List<Paciente> findBydni(String dnipaciente);
	List<Paciente> findByCorreoElectronico(String correopaciente);
	List<Paciente> findByDireccion(String direccionpaciente);
	List<Paciente> findByTelefono(String telefonopaciente);
	
	
}
