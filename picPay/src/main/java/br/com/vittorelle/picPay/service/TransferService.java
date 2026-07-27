package br.com.vittorelle.picPay.service;

import br.com.vittorelle.picPay.dto.TransferDto;
import br.com.vittorelle.picPay.entity.Transfer;
import br.com.vittorelle.picPay.entity.Wallet;
import br.com.vittorelle.picPay.exception.InsufficientBalanceException;
import br.com.vittorelle.picPay.exception.TransferNotAllowedForWalletType;
import br.com.vittorelle.picPay.exception.TransferNotAuthorizedException;
import br.com.vittorelle.picPay.exception.WalletNotFoundException;
import br.com.vittorelle.picPay.repository.TransferRepository;
import br.com.vittorelle.picPay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult =  transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletType();
        }

        if (!sender.isBalanceEqualOrGreaterThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
