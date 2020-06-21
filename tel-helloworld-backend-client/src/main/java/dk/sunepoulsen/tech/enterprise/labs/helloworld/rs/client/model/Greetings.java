package dk.sunepoulsen.tech.enterprise.labs.helloworld.rs.client.model;

import dk.sunepoulsen.tech.enterprise.labs.core.rs.client.model.BaseModel;

import java.util.Objects;

public class Greetings implements BaseModel {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greetings greetings = (Greetings) o;
        return Objects.equals(message, greetings.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Greetings{" +
                "message='" + message + '\'' +
                '}';
    }
}
