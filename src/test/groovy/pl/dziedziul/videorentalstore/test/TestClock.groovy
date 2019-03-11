package pl.dziedziul.videorentalstore.test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.TemporalAmount
import java.util.concurrent.atomic.AtomicReference

class TestClock extends Clock {
    private static Logger logger = LoggerFactory.getLogger(TestClock)
    public static Instant DEFAULT_TEST_INSTANT = Instant.parse("2018-01-01T00:00:00.123456Z")
    private final ZoneId zone
    private final AtomicReference<Instant> instant = new AtomicReference()

    private TestClock(Instant instant, ZoneId zone) {
        this.instant.set(instant)
        this.zone = zone
    }

    static TestClock defaultWarsaw() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("Europe/Warsaw"))
    }

    static TestClock defaultUtc() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("UTC"))
    }

    static TestClock defaultMalta() {
        return new TestClock(DEFAULT_TEST_INSTANT, ZoneId.of("Europe/Malta"))
    }

    void reset() {
        changeInstant(DEFAULT_TEST_INSTANT)
    }

    void changeInstant(Instant instant) {
        this.instant.set(instant)
        logger.info "Setting test time to $instant"
    }

    void windForward(TemporalAmount temporalAmount) {
        def newInstant = this.instant.updateAndGet { instant -> instant.plus(temporalAmount) }
        logger.info "Winding test time forward to $newInstant"
    }

    void windForwardSeconds(int seconds) {
        windForward(Duration.ofSeconds(seconds.toLong()))
    }

    void windForwardDays(int days) {
        windForward(Duration.ofDays(days.toLong()))

    }

    void windBack(TemporalAmount temporalAmount) {
        def newInstant = this.instant.updateAndGet { instant -> instant.minus(temporalAmount) }
        logger.info "Winding test time back to $newInstant"
    }

    void windBackSeconds(int seconds) {
        windBack(Duration.ofSeconds(seconds.toLong()))
    }


    void windBackDays(int days) {
        windBack(Duration.ofDays(days.toLong()))
    }

    @Override
    ZoneId getZone() {
        return zone
    }

    Clock withZone(ZoneId zone) {
        if (zone == this.zone) {
            return this
        } else {
            return new TestClock(instant.get(), this.zone)
        }
    }

    @Override
    Instant instant() {
        return instant.get()
    }
}
