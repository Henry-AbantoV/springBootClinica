package edu.unc.clinica.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Este servicio proporciona funcionalidades para la generación, validación y manipulación de tokens JWT.
 */
@Service
public class JwtService {

	/**
     * La clave secreta utilizada para firmar y verificar los tokens JWT.
     */
	 private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

	    /**
	     * Genera un token JWT para el usuario dado.
	     * @param user Los detalles del usuario para los cuales se generará el token.
	     * @return El token JWT generado.
	     */
	    public String getToken(UserDetails user) {
	        return getToken(new HashMap<>(), user);
	    }

	    
	    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
	        return Jwts
	            .builder()
	            .setClaims(extraClaims)
	            .setSubject(user.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
	            .signWith(getKey(), SignatureAlgorithm.HS256)
	            .compact();
	    }

	    private Key getKey() {
	       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
	       return Keys.hmacShaKeyFor(keyBytes);
	    }
	    
	    /**
	     * Obtiene el nombre de usuario del token JWT.
	     * @param token El token JWT.
	     * @return El nombre de usuario extraído del token.
	     */

	    public String getUsernameFromToken(String token) {
	        return getClaim(token, Claims::getSubject);
	    }

	    /**
	     * Verifica si un token JWT es válido para un usuario dado.
	     * @param token El token JWT.
	     * @param userDetails Los detalles del usuario para comparar con el token.
	     * @return true si el token es válido para el usuario dado, false de lo contrario.
	     */
	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username=getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	    }

	    private Claims getAllClaims(String token)
	    {
	        return Jwts
	            .parserBuilder()
	            .setSigningKey(getKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	    }

	    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
	    {
	        final Claims claims=getAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Date getExpiration(String token)
	    {
	        return getClaim(token, Claims::getExpiration);
	    }

	    private boolean isTokenExpired(String token)
	    {
	        return getExpiration(token).before(new Date());
	    }	    
}
