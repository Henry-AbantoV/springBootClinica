package edu.unc.clinica.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa una solicitud de registro enviada por un usuario al sistema.
 * Contiene los detalles necesarios para crear una nueva cuenta de usuario, incluyendo el nombre de usuario,
 * la contraseña, el nombre y el apellido del usuario.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	/**
     * El nombre de usuario deseado para la nueva cuenta.
     */
	String username;
	
	/**
    * La contraseña deseada para la nueva cuenta.
    */
	String password;
	
	/**
     * El primer nombre del usuario para la nueva cuenta.
     */
	String firstname;
	
	/**
     * El apellido del usuario para la nueva cuenta.
     */
	String lastname;	
}
