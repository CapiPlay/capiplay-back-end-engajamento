package br.senai.sc.engajamento.inscricao.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BuscarUmInscricaoCommand {
    @NotNull
    private UUID idUsuario;
    @NotNull
    private UUID idCanal;
}
