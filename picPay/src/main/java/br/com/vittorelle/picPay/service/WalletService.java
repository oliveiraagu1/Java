package br.com.vittorelle.picPay.service;

import br.com.vittorelle.picPay.dto.CreateWalletDto;
import br.com.vittorelle.picPay.entity.Wallet;
import br.com.vittorelle.picPay.exception.WalletDataAlreadyExistsException;
import br.com.vittorelle.picPay.repository.WalletRepository;
import br.com.vittorelle.picPay.repository.WalletTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletTypeRepository walletTypeRepository;

    public WalletService(WalletRepository walletRepository, WalletTypeRepository walletTypeRepository) {
        this.walletRepository = walletRepository;
        this.walletTypeRepository = walletTypeRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var existingWallet = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (existingWallet.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or email already exists");
        }
        var walletType = walletTypeRepository.findByDescription(dto.walletType().getDescription())
                .orElseThrow(() -> new RuntimeException("WalletType not found: " + dto.walletType()));
        return walletRepository.save(dto.toWallet(walletType));
    }
}
