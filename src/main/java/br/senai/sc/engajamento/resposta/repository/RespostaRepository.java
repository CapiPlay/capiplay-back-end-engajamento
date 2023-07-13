package br.senai.sc.engajamento.resposta.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, UUID> {

    List<Resposta> findAllByComentario(Comentario comentario);
}