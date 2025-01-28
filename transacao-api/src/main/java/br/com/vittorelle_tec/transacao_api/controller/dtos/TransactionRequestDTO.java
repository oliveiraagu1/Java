package br.com.vittorelle_tec.transacao_api.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {
}
