package pl.aml.poc.serviceb.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
@PreAuthorize("hasRole('ADMIN')")
public class SecuredController {


    @GetMapping("/call")
    public ResponseEntity<String> callOtherService() {
        return ResponseEntity.ok("Hello World");
    }

}
