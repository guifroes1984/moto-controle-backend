package com.guifroes1984.moto_controle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guifroes1984.moto_controle.enums.TipoCategoria;
import com.guifroes1984.moto_controle.model.Categoria;
import com.guifroes1984.moto_controle.model.Usuario;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findByTipoAndAtivaTrueOrderByOrdem(TipoCategoria tipo);

	@Query("SELECT c FROM Categoria c WHERE (c.usuario IS NULL OR c.usuario = :usuario) AND c.tipo = :tipo AND c.ativa = true ORDER BY c.ordem")
	List<Categoria> findCategoriasUsuario(@Param("usuario") Usuario usuario, @Param("tipo") TipoCategoria tipo);

}
