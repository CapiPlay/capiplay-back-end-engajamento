package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoRespostaId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarUmReacaoRespostaCommand {
    private ReacaoRespostaId idReacaoResposta;
}