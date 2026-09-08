package com.oodles.drdemoclaimsprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private static final Path EXPORT_ROOT = Paths.get("/srv/exports");

    private final AccountRepository repository;
    private final SessionCodec codec;

    public AccountController(AccountRepository repository, SessionCodec codec) {
        this.repository = repository;
        this.codec = codec;
    }

    @GetMapping("/accounts")
    public List<String> byRegion(@RequestParam String region) throws Exception {
        return repository.findByRegion(region);
    }

    @GetMapping(value = "/accounts/summary", produces = MediaType.TEXT_HTML_VALUE)
    public String summary(@RequestParam String label) {
        return "<html><body><h1>" + label + "</h1></body></html>";
    }

    @GetMapping("/exports")
    public byte[] export(@RequestParam String name) throws Exception {
        return Files.readAllBytes(EXPORT_ROOT.resolve(name));
    }

    @GetMapping("/session/restore")
    public String restore(@RequestParam String snapshot) throws Exception {
        return String.valueOf(codec.decode(snapshot));
    }
}
