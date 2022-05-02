package co.edu.iudigital.app.service.iface;

import co.edu.iudigital.app.model.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
}
