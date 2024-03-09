package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
