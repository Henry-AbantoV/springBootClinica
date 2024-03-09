package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
