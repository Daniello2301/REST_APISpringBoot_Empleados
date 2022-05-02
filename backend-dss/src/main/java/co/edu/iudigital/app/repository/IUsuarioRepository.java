package co.edu.iudigital.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.iudigital.app.model.Usuario;

@Repository // ambigüo
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	public Usuario findByUsername(String username);
}
