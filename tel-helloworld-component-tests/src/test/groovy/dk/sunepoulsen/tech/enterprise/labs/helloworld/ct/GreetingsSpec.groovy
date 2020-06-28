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

    void "GET /bad-request returns 400"() {
        given: 'HelloWorld service is available'
            deployment.waitForAvailable(CONTAINER_NAME)

        when: 'Call GET /bad-request'
            HttpRequest httpRequest = httpHelper.newRequestBuilder(CONTAINER_NAME, '/bad-request')
                .GET()
                .build()

            HttpResponseVerificator verificator = httpHelper.sendRequest(httpRequest)

        then: 'Response Code is 400'
            verificator.responseCode(400)

        and: 'Content Type is application/json'
            verificator.contentTypeIsJson()

        and: 'Response body is json'
            verificator.bodyIsJson()

        and: 'Verify greetings body'
            verificator.bodyAsJson() == [
                'code': 'bad-request',
                'param': 'id',
                'message': 'Invalid id'
            ]
    }

    void "GET /unknown-path returns 404"() {
        given: 'HelloWorld service is available'
            deployment.waitForAvailable(CONTAINER_NAME)

        when: 'Call GET /unknown-path'
            HttpRequest httpRequest = httpHelper.newRequestBuilder(CONTAINER_NAME, '/unknown-path')
                .GET()
                .build()

            HttpResponseVerificator verificator = httpHelper.sendRequest(httpRequest)

        then: 'Response Code is 404'
            verificator.responseCode(404)

        and: 'Content Type is application/json'
            verificator.contentTypeIsJson()

        and: 'Response body is json'
            verificator.bodyIsJson()

        and: 'Verify greetings body'
            verificator.bodyAsJson() == [
                'timestamp': verificator.bodyAsJson().timestamp,
                'status': 404,
                'error': 'Not Found',
                'message': '',
                'path': '/unknown-path'
            ]
    }
}
