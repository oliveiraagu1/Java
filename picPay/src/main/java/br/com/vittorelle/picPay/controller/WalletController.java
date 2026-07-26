package br.com.vittorelle.picPay.controller;

import br.com.vittorelle.picPay.dto.CreateWalletDto;
import br.com.vittorelle.picPay.entity.Wallet;
import br.com.vittorelle.picPay.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WalletController {
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody CreateWalletDto dto){
        var wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }

}
