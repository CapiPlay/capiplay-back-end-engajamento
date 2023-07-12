package br.senai.sc.engajamento.video.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @Column(nullable = false)
    private Long visualizacao;
}