package br.com.vittorelle.picPay.service;

import br.com.vittorelle.picPay.client.AuthorizationClient;
import br.com.vittorelle.picPay.entity.Transfer;
import br.com.vittorelle.picPay.exception.PicPayException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public Boolean isAuthorized(Transfer transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
