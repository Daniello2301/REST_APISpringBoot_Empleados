package co.edu.iudigital.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.iudigital.app.model.Role;
import co.edu.iudigital.app.model.Usuario;
import co.edu.iudigital.app.repository.IUsuarioRepository;
import co.edu.iudigital.app.service.iface.IUsuarioService;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("Iniciando loaduserByUsername");
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			LOG.error("No se puede loguear el usuario "+ username);
			throw new UsernameNotFoundException("No se puede loguear el usuario "+ username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles = usuario.getRoles();
		for(Role role: roles) {
			GrantedAuthority gA = new SimpleGrantedAuthority(role.getNombre());
			authorities.add(gA);
		}
		return new User(
					usuario.getUsername(), 
					usuario.getPassword(), 
					usuario.getEnabled(),
					true,
					true,
					true,
					authorities
				);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
