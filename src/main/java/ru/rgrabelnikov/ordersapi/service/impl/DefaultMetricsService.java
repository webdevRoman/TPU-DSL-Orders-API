package ru.rgrabelnikov.ordersapi.service.impl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.rgrabelnikov.ordersapi.service.MetricsService;

import java.util.concurrent.Callable;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class DefaultMetricsService implements MetricsService {

    private final MeterRegistry meterRegistry;

    @Override
    public void incrementRequestsTotalCounter(final HttpStatusCode status) {
        final HttpServletRequest rq = requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Counter.builder("http.requests")
                .description("Сколько запросов обработало API")
                .tags("method", rq.getMethod())
                .tags("route,", rq.getRequestURI())
                .tags("status", String.valueOf(status.value()))
                .register(meterRegistry)
                .increment();
    }

    @Override
    public void incrementOrdersCreatedCounter() {
        Counter.builder("shop.orders.placed")
                .description("Сколько заказов создано")
                .register(meterRegistry)
                .increment();
    }

    @Override
    public <T> T recordRequestTimer(final Callable<T> action) throws Exception {
        final HttpServletRequest rq = requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return Timer.builder("http.request.duration.seconds")
                .description("Распределение времени ответа")
                .publishPercentileHistogram()
                .tags("method", rq.getMethod())
                .tags("route", rq.getRequestURI())
                .register(meterRegistry)
                .recordCallable(action);
    }
}
