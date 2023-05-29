package pl.aml.poc.servicea.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aml.poc.servicea.service.CallBService;

@RestController
@RequestMapping("/test")
public class TestController {

    private final CallBService callBService;

    @Autowired
    public TestController(CallBService callBService) {
        this.callBService = callBService;
    }

    @GetMapping("/call")
    public ResponseEntity<String> callOtherService() {
        String response = callBService.callServiceB().getBody();
        return ResponseEntity.ok(response);
    }

}
