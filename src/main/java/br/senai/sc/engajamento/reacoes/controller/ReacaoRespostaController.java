package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarTodosPorComentarioReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoResposta;
import br.senai.sc.engajamento.reacoes.service.ReacaoRespostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoResposta")
public class ReacaoRespostaController {
    private ReacaoRespostaService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestBody CriarReacaoRespostaCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        service.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReacaoResposta> buscarUm(
            @RequestBody BuscarUmReacaoRespostaCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        return ResponseEntity.ok(service.buscarUm(cmd.from(idUsuario)));
    }

    @GetMapping("/buscar-todos-por-comentario")
    public ResponseEntity<List<ReacaoResposta>> buscarTodos(
            @RequestBody BuscarTodosPorComentarioReacaoRespostaCommand cmd) {
        return ResponseEntity.ok(service.buscarTodos(cmd));
    }
}
