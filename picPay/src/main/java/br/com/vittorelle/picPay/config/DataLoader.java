package br.com.vittorelle.picPay.config;

import br.com.vittorelle.picPay.entity.WalletType;
import br.com.vittorelle.picPay.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (walletTypeRepository.count() == 0) {
            Arrays.stream(WalletType.Enum.values())
                    .forEach(walletType -> walletTypeRepository.save(walletType.get()));
        }
    }
}
