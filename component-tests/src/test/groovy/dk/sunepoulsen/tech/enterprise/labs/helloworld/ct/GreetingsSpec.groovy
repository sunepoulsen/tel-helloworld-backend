package dk.sunepoulsen.tech.enterprise.labs.helloworld.ct

import dk.sunepoulsen.tech.enterprise.labs.core.component.tests.docker.DockerDeployment
import dk.sunepoulsen.tech.enterprise.labs.core.rs.client.TechEnterpriseLabsClient
import dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.HelloWorldIntegrator
import dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.model.Greetings
import spock.lang.Specification

class GreetingsSpec extends Specification {

    private static String CONTAINER_NAME = 'tel-helloworld-service'
    private static DockerDeployment deployment

    void setupSpec() {
        deployment = new DockerDeployment('ct', [CONTAINER_NAME])
        deployment.deploy()
        deployment.waitForAvailable()
    }

    void cleanupSpec() {
        deployment.undeploy()
    }

    void "GET /greetings returns OK"() {
        given: 'HelloWorld service is available'
            deployment.waitForAvailable(CONTAINER_NAME)
            TechEnterpriseLabsClient client = deployment.createClient(CONTAINER_NAME)
            HelloWorldIntegrator sut = new HelloWorldIntegrator(client)

        when: 'Call GET /greetings'
            Greetings greetings = sut.greetings().blockingGet()

        then: 'Verify greetings message'
            greetings == new Greetings(message: 'Hello World')
    }
}
