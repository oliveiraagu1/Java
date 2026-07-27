package br.com.vittorelle.picPay.repository;

import br.com.vittorelle.picPay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository  extends JpaRepository<Transfer, UUID> {
}
