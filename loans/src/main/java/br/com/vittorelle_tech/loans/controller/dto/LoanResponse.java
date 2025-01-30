package br.com.vittorelle_tech.loans.controller.dto;

import br.com.vittorelle_tech.loans.domain.LoanType;

public record LoanResponse(LoanType type, Double interestRate) {}
