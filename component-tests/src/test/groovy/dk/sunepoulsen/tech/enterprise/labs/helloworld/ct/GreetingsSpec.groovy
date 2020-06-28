package dk.sunepoulsen.tech.enterprise.labs.helloworld.ct

import dk.sunepoulsen.tech.enterprise.labs.core.component.tests.specification.DeploymentSpecification
import dk.sunepoulsen.tech.enterprise.labs.core.component.tests.verification.HttpResponseVerificator

import java.net.http.HttpRequest

class GreetingsSpec extends DeploymentSpecification {

    private static String CONTAINER_NAME = 'tel-helloworld-service'

    void setupSpec() {
        initDeployment('ct', [CONTAINER_NAME])
    }

    void "GET /greetings returns OK"() {
        given: 'HelloWorld service is available'
            deployment.waitForAvailable(CONTAINER_NAME)

        when: 'Call GET /greetings'
            HttpRequest httpRequest = httpHelper.newRequestBuilder(CONTAINER_NAME, '/greetings')
                .GET()
                .build()

            HttpResponseVerificator verificator = httpHelper.sendRequest(httpRequest)

        then: 'Response Code is 200'
            verificator.responseCode(200)

        and: 'Content Type is application/json'
            verificator.contentTypeIsJson()

        and: 'Response body is json'
            verificator.bodyIsJson()

        and: 'Verify greetings body'
            verificator.bodyAsJson() == [
                'message': 'Hello World'
            ]
    }
}
