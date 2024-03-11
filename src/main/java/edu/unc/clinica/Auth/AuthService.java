package edu.unc.clinica.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.unc.clinica.JWT.JwtService;
import edu.unc.clinica.domain.Role;
import edu.unc.clinica.domain.User;
import edu.unc.clinica.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Esta clase proporciona servicios relacionados con la autenticación de usuarios en el sistema.
 * Incluye métodos para realizar el inicio de sesión y el registro de nuevos usuarios.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
	
	/**
     * Repositorio de usuarios utilizado para acceder y manipular la información de los usuarios en la base de datos.
     */
	private final UserRepository userRepository;
	
	 /**
     * Servicio JWT utilizado para generar y validar tokens de autenticación.
     */
	private final JwtService jwtService;
	
	 /**
     * Codificador de contraseñas utilizado para codificar las contraseñas de los usuarios antes de almacenarlas en la base de datos.
     */
	private final PasswordEncoder passwordEncoder;
	
	 /**
     * Administrador de autenticación utilizado para autenticar a los usuarios durante el proceso de inicio de sesión.
     */
	private final AuthenticationManager authManager;

	/**
     * Método para autenticar a un usuario y generar un token de autenticación.
     * @param request La solicitud de inicio de sesión que contiene las credenciales del usuario.
     * @return La respuesta de autenticación que contiene el token de autenticación.
     * @throws AuthenticationException Si las credenciales de inicio de sesión son inválidas.
     */
	  public AuthResponse login(LoginRequest request) {
	  authManager.authenticate(new
	  UsernamePasswordAuthenticationToken(request.getUsername(),
	  request.getPassword())); 
	  UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(); String
	  token=jwtService.getToken(user); return AuthResponse.builder() .token(token)
	  .build();
	  
	  }
	
	   /**
	     * Método para registrar un nuevo usuario en el sistema.
	     * @param request La solicitud de registro que contiene los detalles del nuevo usuario.
	     * @return La respuesta de autenticación que contiene el token de autenticación para el nuevo usuario registrado.
	     */
	public AuthResponse register(RegisterRequest request) {
		User user = User.builder().username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.role(Role.USER).build();

		userRepository.save(user);

		return AuthResponse.builder().token(jwtService.getToken(user)).build();

	}
}
