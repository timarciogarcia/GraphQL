package com.microinfor.graphQL.repository;

import com.microinfor.graphQL.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}