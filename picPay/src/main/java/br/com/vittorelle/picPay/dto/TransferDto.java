package br.com.vittorelle.picPay.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferDto(@DecimalMin("0.01") @NotNull BigDecimal value,
                          @NotNull UUID payer,
                          @NotNull UUID payee) {
}
