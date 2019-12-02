package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private String param;
    public WelcomeController(@Value("${welcome.message}") String param)
    {
        this.param = param;
    }

    @GetMapping("/")
    public String sayHello() {
        return param;
    }
}