package tn.jibli;

import org.springframework.stereotype.Component;

// Spring creates one instance of this class at startup, we never write "new WelcomeMessage()"
@Component
public class WelcomeMessage {

    public String text() {
        return "Welcome to Jibli";
    }
}
