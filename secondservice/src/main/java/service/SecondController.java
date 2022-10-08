package service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondController {

    @GetMapping("/home")
    public String home() {
        return "This is Second Service Home";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info("Header = {}", header);
        return "Message in Second Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, this is second service";
    }
}
