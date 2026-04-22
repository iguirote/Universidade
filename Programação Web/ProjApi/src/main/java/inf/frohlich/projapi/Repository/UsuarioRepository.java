package inf.frohlich.projapi.Repository;

import inf.frohlich.projapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
