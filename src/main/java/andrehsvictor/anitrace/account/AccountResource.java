package andrehsvictor.anitrace.account;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.anitrace.account.dto.AccountDto;
import andrehsvictor.anitrace.account.dto.CreateAccountDto;
import andrehsvictor.anitrace.account.dto.EditAccountDto;
import andrehsvictor.anitrace.account.dto.SendActionEmailDto;
import andrehsvictor.anitrace.token.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountResource {

    private final AccountService accountService;

    @PostMapping("/api/v1/account")
    public ResponseEntity<AccountDto> create(@Valid @RequestBody CreateAccountDto createAccountDto) {
        AccountDto accountDto = accountService.create(createAccountDto);
        URI location = URI.create("/api/v1/users/" + accountDto.getId());
        return ResponseEntity.created(location).body(accountDto);
    }

    @GetMapping("/api/v1/account")
    public AccountDto get() {
        return accountService.get();
    }

    @PutMapping("/api/v1/account")
    public AccountDto edit(@Valid @RequestBody EditAccountDto editAccountDto) {
        return accountService.edit(editAccountDto);
    }

    @DeleteMapping("/api/v1/account")
    public ResponseEntity<Void> delete() {
        accountService.delete();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/v1/account/send-action-email")
    public ResponseEntity<Void> sendActionEmail(@Valid @RequestBody SendActionEmailDto sendActionEmailDto) {
        accountService.sendActionEmail(sendActionEmailDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/v1/account/verify-email")
    public ResponseEntity<Void> verifyEmail(@Valid @RequestBody TokenDto tokenDto) {
        accountService.verifyEmail(tokenDto);
        return ResponseEntity.noContent().build();
    }
}
