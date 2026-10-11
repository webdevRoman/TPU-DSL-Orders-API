package ru.rgrabelnikov.ordersapi.service;

public interface EventSenderService {

    <T> void send(final T payload);
}
