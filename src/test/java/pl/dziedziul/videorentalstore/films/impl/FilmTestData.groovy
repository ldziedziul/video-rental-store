package pl.dziedziul.videorentalstore.films.impl

import pl.dziedziul.videorentalstore.films.FilmType

import static pl.dziedziul.videorentalstore.test.TestData.SOME_INSTANT

class FilmTestData {
    public static FilmEntity MATRIX = new FilmEntity("Matrix 11", FilmType.NEW, SOME_INSTANT)
    public static FilmEntity SPIDERMAN = new FilmEntity("Spiderman", FilmType.REGULAR, SOME_INSTANT)
    public static FilmEntity OUT_OF_AFRICA = new FilmEntity("Out of Africa", FilmType.OLD, SOME_INSTANT)
}
