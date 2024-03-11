package edu.unc.clinica.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa la entidad de usuario en el sistema.
 * Contiene información sobre los usuarios, como su identificador, nombre de usuario, contraseña, roles, etc.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User  implements UserDetails{
	
	/**
     * El identificador único del usuario.
     */
	@Id
	@GeneratedValue
	Integer id;
	
	/**
     * El nombre de usuario del usuario.
     */
	@Basic
	@Column(nullable = false)
	String username;
	
	/**
     * El apellido del usuario.
     */
	String lastname;
	
	 /**
     * El primer nombre del usuario.
     */
	String firstname;
	
	/**
     * La contraseña del usuario.
     */
	String password;
	
	/**
     * El rol del usuario en el sistema.
     */
	@Enumerated(EnumType.STRING)
	Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Retorna los roles del usuario como GrantedAuthority
		return List.of(new SimpleGrantedAuthority((role.name())));
	}

	@Override
	public boolean isAccountNonExpired() {
		// La cuenta del usuario nunca expira
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		 // La cuenta del usuario nunca se bloquea
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Las credenciales del usuario nunca expiran
		return true;
	}

	@Override
	public boolean isEnabled() {
		 // La cuenta del usuario siempre está habilitada
		return true;
	}
	
}
