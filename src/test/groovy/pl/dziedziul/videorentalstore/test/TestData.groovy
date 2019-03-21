package pl.dziedziul.videorentalstore.test

import java.time.Instant

class TestData {
    public static final Instant SOME_INSTANT = TestClock.DEFAULT_TEST_INSTANT
    public static final String SOME_NAME = "some-name"
    public static final int SOME_PRICE = 123
    public static final int SOME_BIGGER_PRICE = 256
    public static final UUID SOME_FILM_ID = UUID.fromString("12345678-1985-0000-0000-000000000000")
    public static final UUID SOME_OTHER_FILM_ID = UUID.fromString("12345678-1986-0000-0000-000000000000")
    public static final UUID SOME_CUSTOMER_ID = UUID.fromString("12345678-1987-0000-0000-000000000000")
    public static final UUID SOME_NONEXISTING_CUSTOMER_ID = UUID.fromString("12345678-1989-0000-0000-000000000001")
    public static int SOME_NUMBER_OF_DAYS = 5
    public static UUID SOME_RENTAL_ID = UUID.fromString("12345678-1990-0000-0000-000000000000")
    public static int SOME_POINTS = 63
}
