package ru.rgrabelnikov.ordersapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(final Class<?> entityClass, final UUID id) {
        super("%s %s not found".formatted(entityClass.getSimpleName(), id));
    }
}
