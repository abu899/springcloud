package service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
@RequiredArgsConstructor
public class FirstController {

    private final Environment env;

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
    public String check(HttpServletRequest request) {
        String serverPort = env.getProperty("local.server.port");
        log.info("env local server port = {} ", serverPort);
        return "Hi, this is first service, port is " + request.getServerPort();
    }
}
