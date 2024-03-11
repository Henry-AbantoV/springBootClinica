package edu.unc.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unc.clinica.domain.HistorialMedico;
import edu.unc.clinica.domain.Paciente;

public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
}
