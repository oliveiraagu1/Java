package br.com.vittorelle.picPay.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@AllArgsConstructor
public class WalletDataAlreadyExistsException extends PicPayException {

    private String detail;

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);

        pb.setTitle("Wallet already exists");
        pb.setDetail(detail);

        return pb;
    }
}
