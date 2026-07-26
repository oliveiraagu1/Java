package br.com.vittorelle.picPay.repository;

import br.com.vittorelle.picPay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletTypeRepository extends JpaRepository<WalletType, UUID> {
    Optional<WalletType> findByDescription(String description);
}
