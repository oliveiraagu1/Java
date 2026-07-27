package br.com.vittorelle.picPay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "wallets_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @Getter
    @AllArgsConstructor
    public enum Enum {

        USER("user"),
        MERCHANT("merchant");

        private String description;

        public WalletType get(){
            return new WalletType(null, description);
        }

    }

}
