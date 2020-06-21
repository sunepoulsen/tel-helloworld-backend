package dk.sunepoulsen.tech.enterprise.labs.helloworld.service.domain;

import dk.sunepoulsen.tech.enterprise.labs.core.service.domain.requests.ApiBadRequestException;
import dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.model.Greetings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/greetings")
    public Greetings getGreetings() {
        Greetings greetings = new Greetings();
        greetings.setMessage("Hello World");

        return greetings;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/bad-request")
    public Greetings getBadRequest() {
        throw new ApiBadRequestException("bad-request", "id", "Invalid id");
    }
}
