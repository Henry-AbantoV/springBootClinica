package edu.unc.clinica.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa una solicitud de inicio de sesión enviada por un usuario al sistema.
 * Contiene el nombre de usuario y la contraseña proporcionados por el usuario para iniciar sesión.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	/**
     * El nombre de usuario proporcionado por el usuario para iniciar sesión.
     */
	String username;
	
	  /**
     * La contraseña proporcionada por el usuario para iniciar sesión.
     */
	String password;
}
