package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unc.clinica.domain.HistorialMedico;

public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
	
}
