package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarTodosPorVideoReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRepository;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {
    private final ReacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    public void criar(@Valid CriarReacaoCommand cmd) {

        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));
        Video video = videoRepository.findById(cmd.getIdVideo())
                .orElseThrow(()-> new NaoEncontradoException("Resposta não encontrado!"));

        Reacao reacaoExistente = repository.findByIdUsuarioAndIdVideo(usuario, video);

        if (reacaoExistente == null) {
            Reacao reacao = new Reacao();
            reacao.setIdUsuario(usuario);
            reacao.setIdVideo(video);
            reacao.setCurtida(cmd.getCurtida());

            if(cmd.getCurtida()){
                video.setQtdCurtidas(video.getQtdCurtidas() + 1);
            } else {
                video.setQtdDescurtidas(video.getQtdDescurtidas() + 1);
            }
            videoService.editarPontuacao(video);

            repository.save(reacao);
        } else if (reacaoExistente.isCurtida() == cmd.getCurtida()) {

            if(cmd.getCurtida()){
                video.setQtdCurtidas(video.getQtdCurtidas() - 1);
            } else {
                video.setQtdDescurtidas(video.getQtdDescurtidas() - 1);
            }
            videoService.editarPontuacao(video);

            repository.deleteByIdUsuarioAndIdVideo(usuario, video);
        } else {
            reacaoExistente.setCurtida(!reacaoExistente.isCurtida());
            repository.save(reacaoExistente);
        }
    }

    public Reacao buscarUm(@Valid BuscarUmReacaoCommand cmd) {
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));
        Video video = videoRepository.findById(cmd.getIdVideo())
                .orElseThrow(()-> new NaoEncontradoException("Resposta não encontrado!"));

        Reacao reacao = repository.findByIdUsuarioAndIdVideo(usuario, video);
        if (reacao == null) {
            throw new NaoEncontradoException("Reação não encontrada!");
        }

        return reacao;
    }

    public List<Reacao> buscarTodosPorVideo(@Valid BuscarTodosPorVideoReacaoCommand cmd) {
        Video video = videoRepository.findById(cmd.getIdVideo())
                .orElseThrow(()-> new NaoEncontradoException("Resposta não encontrado!"));

        return repository.findAllByIdVideo(video);
    }
}
