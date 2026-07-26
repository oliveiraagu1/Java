package br.com.vittorelle.picPay.dto;

import br.com.vittorelle.picPay.entity.Wallet;
import br.com.vittorelle.picPay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(@NotBlank String fullName,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {

    public Wallet toWallet(WalletType walletType){
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType
        );
    }
}
