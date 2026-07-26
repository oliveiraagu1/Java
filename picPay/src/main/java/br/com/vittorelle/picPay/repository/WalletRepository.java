package br.com.vittorelle.picPay.repository;

import br.com.vittorelle.picPay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
