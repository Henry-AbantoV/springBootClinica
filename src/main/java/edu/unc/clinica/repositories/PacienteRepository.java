/*
 * @file PacienteRepository.java;
 * @Autor Daniela Torres (c)2024
 * @Created 12 mar 2024,2:15:20
 */
package edu.unc.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Paciente;

// TODO: Auto-generated Javadoc
/**
 * The Interface PacienteRepository.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    /**
     * Busca pacientes por nombres.
     *
     * @param nombres Nombres del paciente a buscar.
     * @return Lista de pacientes que coinciden con los nombres proporcionados.
     */
	List<Paciente> findByNombres(String nombres);

    /**
     * Busca pacientes por DNI.
     *
     * @param dnipaciente DNI del paciente a buscar.
     * @return Lista de pacientes que coinciden con el DNI proporcionado.
     */
	List<Paciente> findBydni(String dnipaciente);

    /**
     * Busca pacientes por correo electrónico.
     *
     * @param correopaciente Correo electrónico del paciente a buscar.
     * @return Lista de pacientes que coinciden con el correo electrónico proporcionado.
     */
	List<Paciente> findByCorreoElectronico(String correopaciente);

    /**
     * Busca pacientes por dirección.
     *
     * @param direccionpaciente Dirección del paciente a buscar.
     * @return Lista de pacientes que coinciden con la dirección proporcionada.
     */
	List<Paciente> findByDireccion(String direccionpaciente);

    /**
     * Busca pacientes por número de teléfono.
     *
     * @param telefonopaciente Número de teléfono del paciente a buscar.
     * @return Lista de pacientes que coinciden con el número de teléfono proporcionado.
     */
	List<Paciente> findByTelefono(String telefonopaciente);
}
