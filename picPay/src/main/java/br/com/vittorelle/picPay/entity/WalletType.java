package br.com.vittorelle.picPay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "wallets_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    public enum Enum {

        USER("user"),
        MERCHANT("merchant");

        Enum(String description) {
            this.description = description;
        }

        private String description;

        public String getDescription() {
            return description;
        }

        public WalletType get(){
            return new WalletType(null, description);
        }

    }

}
