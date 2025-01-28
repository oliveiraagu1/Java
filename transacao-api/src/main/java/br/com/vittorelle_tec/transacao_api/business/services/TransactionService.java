package br.com.vittorelle_tec.transacao_api.business.services;

import br.com.vittorelle_tec.transacao_api.controller.dtos.TransactionRequestDTO;
import br.com.vittorelle_tec.transacao_api.infra.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> listTransactions = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO transaction) {
        log.info("Iniciado o processamento de gravar transações {}", transaction);

        if(transaction.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }

        if(transaction.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        listTransactions.add(transaction);
        log.info("Transacoes adicionadas com sucesso");
    }

    public void cleanTransactions() {
        log.info("Iniciado processamento para deletar transações");
        listTransactions.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransactionRequestDTO> listTransactions(Integer searchRange) {
        log.info("Inicadas buscas de transações por tempo {}", searchRange);

        OffsetDateTime dateTimeRange = OffsetDateTime.now().minusSeconds(searchRange);
        log.info("Retorno de transações com sucesso");
        return listTransactions.stream()
                .filter(transaction -> transaction.dataHora().isAfter(dateTimeRange)).toList();
    }
}
