package br.com.vittorelle_tec.transacao_api.business.services;

import br.com.vittorelle_tec.transacao_api.controller.dtos.StatisticsResponseDTO;
import br.com.vittorelle_tec.transacao_api.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    public final TransactionService transactionService;

    public StatisticsResponseDTO calculateStatisticsTransactions(Integer searchRange) {
        log.info("Iniciada busca de estatísticas de transações pelo período de tempo {}", searchRange);

        List<TransactionRequestDTO> transactions = transactionService.listTransactions(searchRange);

        if(transactions.isEmpty()) {
            return new StatisticsResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics statistics = transactions.stream()
                .mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");

        return new StatisticsResponseDTO(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax()
        );
    }

}
