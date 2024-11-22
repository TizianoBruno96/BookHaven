package com.example.bookHaven.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    public static ResponseEntity<?> getResponse(ResponseType response, Object... args) {
        return switch (response) {
            case BAD_REQUEST -> {
                if (args.length > 0 && args[0] instanceof String message) {
                    yield badParameters(message);
                } else {
                    yield badParametersDefault();
                }
            }
            case OK -> {
                if (args.length > 0 && args[0] instanceof String message) {
                    yield ok(message);
                } else if (args.length > 0) {
                    yield ok(args[0]);
                }
                yield defaultResponse();
            }
            case CUSTOM -> custom(args);
            case NOT_FOUND -> {
                if (args.length > 0 && args[0] instanceof String message) {
                    yield notFound(message);
                } else if (args.length > 0) {
                    yield notFound(args[0]);
                }
                yield defaultResponse();
            }
        };
    }

    private static ResponseEntity<String> badParametersDefault() {
        String message = "Inserisci i parametri corretti";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }

    private static ResponseEntity<String> badParameters(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }

    private static ResponseEntity<String> ok(String message) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }

    private static ResponseEntity<Object> ok(Object object) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(object);
    }

    private static ResponseEntity<?> custom(Object... args) {
        if (args.length == 0) {
            return defaultResponse();
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;
        MediaType type = MediaType.APPLICATION_JSON;
        Object entity = "Errore di creazione della risposta";

        for (Object arg : args) {
            if (arg instanceof HttpStatus status1) {
                status = status1;
            } else if (arg instanceof MediaType type1) {
                type = type1;
            } else {
                entity = arg;
            }
        }

        return ResponseEntity.status(status)
                .contentType(type)
                .body(entity);
    }

    private static ResponseEntity<String> defaultResponse() {
        String message = "Errore di creazione della risposta";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }

    private static ResponseEntity<String> notFound(String arg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body(arg);
    }

    private static ResponseEntity<Object> notFound(Object arg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(arg);
    }

    public enum ResponseType {BAD_REQUEST, OK, CUSTOM, NOT_FOUND}
}
