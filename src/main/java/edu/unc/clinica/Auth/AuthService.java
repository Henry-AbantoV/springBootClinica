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

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;

	
	  public AuthResponse login(LoginRequest request) {
	  authManager.authenticate(new
	  UsernamePasswordAuthenticationToken(request.getUsername(),
	  request.getPassword())); 
	  UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(); String
	  token=jwtService.getToken(user); return AuthResponse.builder() .token(token)
	  .build();
	  
	  }
	
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
