package com.microinfor.graphQL.service;

import com.microinfor.graphQL.exception.UsuarioNotFoundException;
import com.microinfor.graphQL.model.Usuario;
import com.microinfor.graphQL.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario getUsuario(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Usuario> getTodosUsuarios() {
        return repo.findAll();
    }

    public Usuario criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        return repo.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, String nome, String email) {
        Usuario usuario = repo.findById(id).orElse(null);
        if (usuario != null) {
            if (nome != null) usuario.setNome(nome);
            if (email != null) usuario.setEmail(email);
            return repo.save(usuario);
        }
        throw new UsuarioNotFoundException("Usuário não encontrado com ID: " + id);
    }

    public boolean deletarUsuario(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        throw new UsuarioNotFoundException("Usuário não encontrado com ID: " + id);
    }
}