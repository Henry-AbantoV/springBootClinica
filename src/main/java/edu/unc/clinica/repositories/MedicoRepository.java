package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.unc.clinica.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	@Modifying
	@Query("delete from Medico md where md.idMedico=?1")
	void eliminarMedicoDepaPorId(Long id);
}
