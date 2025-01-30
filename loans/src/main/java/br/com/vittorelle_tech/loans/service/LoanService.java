package br.com.vittorelle_tech.loans.service;

import br.com.vittorelle_tech.loans.controller.dto.CustomerLoanRequest;
import br.com.vittorelle_tech.loans.controller.dto.CustomerLoanResponse;
import br.com.vittorelle_tech.loans.controller.dto.LoanResponse;
import br.com.vittorelle_tech.loans.domain.Loan;
import br.com.vittorelle_tech.loans.domain.LoanType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    public CustomerLoanResponse checkLoanAvailability(CustomerLoanRequest loanRequest) {

        var customer = loanRequest.toCustomer();
        var loan = new Loan(customer);

        List<LoanResponse> loans = new ArrayList<>();

        if (loan.isPersonalLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.PERSONAL, loan.getPersonalLoanInterestRate()));
        }

        if (loan.isConsigmentLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.CONSIGNMENT, loan.getConsigmentLoanInterestRate()));
        }

        if (loan.isGuaranteedLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.GUARANTEED, loan.getGuaranteedLoanInterestRate()));
        }

        return new CustomerLoanResponse(loanRequest.name(), loans);
    }
}