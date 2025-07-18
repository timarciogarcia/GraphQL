package com.microinfor.graphQL.controller;

import com.microinfor.graphQL.exception.UsuarioNotFoundException;
import com.microinfor.graphQL.model.Usuario;
import com.microinfor.graphQL.service.UsuarioService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @QueryMapping
    public Usuario usuario(@Argument Long id) {
        return usuarioService.getUsuario(id);
    }

    @QueryMapping
    public List<Usuario> todosUsuarios() {
        return usuarioService.getTodosUsuarios();
    }

    @MutationMapping
    public Usuario criarUsuario(@Argument String nome, @Argument String email) {
        return usuarioService.criarUsuario(nome, email);
    }

    @MutationMapping
    public Usuario atualizarUsuario(@Argument Long id, @Argument String nome, @Argument String email) {
        return usuarioService.atualizarUsuario(id, nome, email);
    }

    @MutationMapping
    public Boolean deletarUsuario(@Argument Long id) throws UsuarioNotFoundException {
        return usuarioService.deletarUsuario(id);
    }
}