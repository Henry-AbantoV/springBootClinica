package edu.unc.clinica.JWT;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Este filtro de autenticación JWT verifica la validez del token JWT en las solicitudes entrantes.
 * Si el token es válido, establece la autenticación del usuario en el contexto de seguridad.
 */
@Component
@RequiredArgsConstructor
public class JwtAutenticationFilter extends OncePerRequestFilter {

	/**
     * Servicio JWT utilizado para la manipulación de tokens JWT.
     */
	private final JwtService jwtService;
	
	 /**
     * Servicio de detalles del usuario utilizado para cargar los detalles del usuario a partir del nombre de usuario.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Realiza la lógica de filtrado para verificar la validez del token JWT en las solicitudes entrantes.
     * Si el token es válido, establece la autenticación del usuario en el contexto de seguridad.
     * @param request La solicitud HTTP entrante.
     * @param response La respuesta HTTP asociada.
     * @param filterChain La cadena de filtros restantes en el filtro.
     * @throws ServletException Si ocurre un error durante el procesamiento de la solicitud.
     * @throws IOException Si ocurre un error de E/S durante el procesamiento de la solicitud.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
    	// Extrae el token JWT de la solicitud
        final String token = getTokenFromRequest(request);
        final String username;

       // Verifica si el token es nulo
        if (token==null)
        {
            filterChain.doFilter(request, response);
            return;
        }
        
     // Obtiene el nombre de usuario del token JWT
        username=jwtService.getUsernameFromToken(token);

     // Verifica si el nombre de usuario no es nulo y no hay autenticación en el contexto de seguridad actual
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
        	// Carga los detalles del usuario utilizando el servicio de detalles del usuario
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);

            // Verifica si el token JWT es válido para el usuario
            if (jwtService.isTokenValid(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }   
        // Pasa la solicitud al siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT de la solicitud HTTP.
     * @param request La solicitud HTTP.
     * @return El token JWT si está presente en la solicitud, o null si no lo está.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }
        return null;
    }

}
