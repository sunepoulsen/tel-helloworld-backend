package dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.model;

import dk.sunepoulsen.tech.enterprise.labs.core.rs.client.model.BaseModel;
import lombok.Data;

import java.util.Objects;

@Data
public class Greetings implements BaseModel {
    private String message;
}
