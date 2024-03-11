package edu.unc.clinica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name="Medico-departamento")
public class MedicoDepartamento {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @Column(name="depa_id",unique=true)
	  private Long depaId;
	  
	  @Override
	  public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		
		if(!(obj instanceof MedicoDepartamento)) {
			return false;
		}
		
		MedicoDepartamento o=(MedicoDepartamento) obj;
		  return this.depaId !=null && this.depaId.equals(o.depaId);
		  
	  }
}
