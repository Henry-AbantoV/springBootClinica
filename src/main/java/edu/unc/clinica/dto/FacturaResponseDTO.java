package edu.unc.clinica.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class FacturaResponseDTO {
	  private Long idFactura;
	    private LocalDateTime fechaEmision;
	    private String descripServicios;
	    private String pagoRealizados;
	    private Double saldoPendiente;
	    private Double costo;
	    private List<PacienteResponseDTO> citas;

	    public FacturaResponseDTO(Long idFactura, LocalDateTime fechaEmision, String descripServicios, String pagoRealizados, Double saldoPendiente, Double costo, List<PacienteResponseDTO> citas) {
	        this.idFactura = idFactura;
	        this.fechaEmision = fechaEmision;
	        this.descripServicios = descripServicios;
	        this.pagoRealizados = pagoRealizados;
	        this.saldoPendiente = saldoPendiente;
	        this.costo = costo;
	        this.citas = citas;
	    }
}
