package pl.dziedziul.videorentalstore.test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestClock extends Clock {
    private static Logger logger = LoggerFactory.getLogger(TestClock.class);
    public static Instant DEFAULT_TEST_INSTANT = Instant.parse("2018-01-01T00:00:00.123456Z");
    private final ZoneId zone;
    private final AtomicReference<Instant> instant = new AtomicReference<>();

    private TestClock(Instant instant, ZoneId zone) {
        this.instant.set(instant);
        this.zone = zone;
    }

    public static TestClock defaultWarsaw() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("Europe/Warsaw"));
    }

    public static TestClock defaultUtc() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("UTC"));
    }

    public static TestClock defaultMalta() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("Europe/Malta"));
    }

    public void reset() {
        changeInstant(DEFAULT_TEST_INSTANT);
    }

    public void changeInstant(Instant instant) {
        this.instant.set(instant);
        logger.info("Setting test time to {}", instant);
    }

    public void windForward(TemporalAmount temporalAmount) {
        Instant newInstant = this.instant.updateAndGet(instant -> instant.plus(temporalAmount));
        logger.info("Winding test time forward to {}", newInstant);
    }

    public void windForwardSeconds(int seconds) {
        windForward(Duration.ofSeconds(seconds));
    }

    public void windForwardDays(int days) {
        windForward(Duration.ofDays(days));

    }

    public void windBack(TemporalAmount temporalAmount) {
        Instant newInstant = this.instant.updateAndGet(instant -> instant.minus(temporalAmount));
        logger.info("Winding test time back to {}", newInstant);
    }

    public void windBackSeconds(int seconds) {
        windBack(Duration.ofSeconds(seconds));
    }


    public void windBackDays(int days) {
        windBack(Duration.ofDays(days));
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        if (zone == this.zone) {
            return this;
        } else {
            return new TestClock(instant.get(), this.zone);
        }
    }

    @Override
    public Instant instant() {
        return instant.get();
    }
}
