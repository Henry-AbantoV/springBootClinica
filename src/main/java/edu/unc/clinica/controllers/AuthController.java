package edu.unc.clinica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.clinica.Auth.AuthResponse;
import edu.unc.clinica.Auth.AuthService;
import edu.unc.clinica.Auth.LoginRequest;
import edu.unc.clinica.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;


/**
 * Este controlador maneja las solicitudes relacionadas con la autenticación de usuarios en el sistema.
 * Proporciona endpoints para iniciar sesión y registrar nuevos usuarios.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	/**
     * Servicio de autenticación utilizado para procesar las solicitudes de inicio de sesión y registro.
     */
	private final AuthService authService;
	
	 /**
     * Endpoint para iniciar sesión de usuario.
     * @param request La solicitud de inicio de sesión que contiene las credenciales del usuario.
     * @return ResponseEntity con la respuesta de la autenticación, incluido el token de autenticación.
     */
	@PostMapping(value="Login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	 /**
     * Endpoint para registrar un nuevo usuario.
     * @param request La solicitud de registro que contiene los detalles del nuevo usuario.
     * @return ResponseEntity con la respuesta de la autenticación, incluido el token de autenticación.
     */
	@PostMapping(value="register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
}
