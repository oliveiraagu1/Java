package br.com.vittorelle.picPay.controller;

import br.com.vittorelle.picPay.dto.TransferDto;
import br.com.vittorelle.picPay.entity.Transfer;
import br.com.vittorelle.picPay.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {
       var response = transferService.transfer(dto);
       return ResponseEntity.ok(response);
    }
}
