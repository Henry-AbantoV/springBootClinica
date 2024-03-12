package edu.unc.clinica.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unc.clinica.domainModels.Departamento;

@FeignClient(name="SpringDepartClinica", url="http://localhost:8020/api/departamentos")
public interface DepaClientRest {

	@GetMapping("/{id}")
	Departamento detalleDepaMedicos(@PathVariable Long id);
	
	@PostMapping()
	Departamento crearDepartamento(@RequestBody Departamento departamento);
	
	@GetMapping("/medicos-por-departamento")
	List<Departamento> obtenerMedicosPorDepartamento(@RequestParam Iterable<Long> ids);
}
