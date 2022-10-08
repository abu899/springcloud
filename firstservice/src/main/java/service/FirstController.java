package service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstController {

    @GetMapping("/home")
    public String home() {
        return "This is First Service Home";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("Header = {}", header);
        return "Message in First Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, this is first service";
    }
}
