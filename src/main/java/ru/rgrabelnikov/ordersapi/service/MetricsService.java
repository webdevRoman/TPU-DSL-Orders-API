package ru.rgrabelnikov.ordersapi.service;

import org.springframework.http.HttpStatusCode;

import java.util.concurrent.Callable;

public interface MetricsService {

    void incrementRequestsTotalCounter(HttpStatusCode status);

    void incrementOrdersCreatedCounter();

    <T> T recordRequestTimer(Callable<T> action) throws Exception;
}
