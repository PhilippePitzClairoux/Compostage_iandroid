package com.compostage.Exceptions;

import java.io.IOException;

public class InvalidServerQuery extends Exception {

    public InvalidServerQuery() {
        super();
    }

    public InvalidServerQuery(String message) {
        super(message);
    }
}
