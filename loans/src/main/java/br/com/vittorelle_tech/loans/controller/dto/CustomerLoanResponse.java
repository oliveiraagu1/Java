package br.com.vittorelle_tech.loans.controller.dto;

import java.util.List;

public record CustomerLoanResponse(String customer,
                                   List<LoanResponse> loans
) {}
