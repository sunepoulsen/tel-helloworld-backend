package dk.sunepoulsen.tech.enterprise.labs.helloworld.service.domain

import dk.sunepoulsen.tech.enterprise.labs.core.rs.client.model.ServiceError
import dk.sunepoulsen.tech.enterprise.labs.core.service.domain.requests.ApiBadRequestException
import dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.model.Greetings
import org.junit.Test
import spock.lang.Specification

class GreetingsControllerSpec extends Specification {
    @Test
    void "GET /greetings returns 200"() {
        given: 'Greetings controller is ready'
            GreetingsController sut = new GreetingsController()

        when: 'Call GET /greetings'
            Greetings result = sut.greetings

        then: 'Body contains a Hello World greetings'
            result == new Greetings(message: 'Hello World')
    }

    @Test
    void "GET /bad-request returns 400"() {
        given: 'Greetings controller is ready'
            GreetingsController sut = new GreetingsController()

        when: 'Call GET /bad-request'
            sut.badRequest

        then: 'Throws ApiBadRequestException with error body'
            ApiBadRequestException ex = thrown(ApiBadRequestException)
            ex.serviceError == new ServiceError(
                code: 'bad-request',
                param: 'id',
                message: 'Invalid id'
            )
    }
}
