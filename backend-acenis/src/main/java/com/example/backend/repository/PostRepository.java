package com.example.backend.repository;

import com.example.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> { // Tipo do ID do Post é Integer

    // CORRIGIDO: Usa findByAutor_IdUser para mapear Post.autor.idUser
    // Isso diz ao Spring Data JPA para ir até o campo 'autor' na entidade Post,
    // e dentro do objeto 'autor' (que é um Usuario), procurar pela propriedade 'idUser'.
    List<Post> findByAutor_IdUser(Integer autorId);
}
