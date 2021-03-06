package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import java.util.Set;

public class UsuarioRepository {

    Set<DadosUsuario> usuarios;

    public UsuarioRepository(Set<DadosUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public DadosUsuario get(int idUsuario) {
        return usuarios.stream().filter(it -> it.idUsuario == idUsuario).findFirst().orElse(null);
    }


}
