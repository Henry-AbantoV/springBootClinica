package edu.unc.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.unc.clinica.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {	
	List<Paciente> findByNombres(String nombres);

}
