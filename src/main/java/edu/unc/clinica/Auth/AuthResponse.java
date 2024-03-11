package edu.unc.clinica.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa la respuesta de autenticación que se devuelve al cliente después de un proceso de autenticación exitoso.
 * Contiene el token de autenticación que el cliente puede usar para realizar solicitudes autenticadas.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	
	 /**
     * El token de autenticación generado para el usuario.
     */
	String token;
}
