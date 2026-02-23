import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionsTest {

    List<String> genres1 = List.of("genre1", "genre2", "genre3");
    List<String> genres2 = List.of("genre4", "genre5", "genre6");
    List<String> cast1 = List.of("actor1", "actor2", "actor3");
    List<String> cast2 = List.of("actor1", "actor5");
    List<String> lang1 = List.of("lang1", "lang2", "lang3");
    List<String> lang2 = List.of("lang4", "lang5", "lang6");
    Movie m1 = new Movie
            ("1", "Title1", 2026, genres1, "Director1", cast1, 1.0, lang1, 100);
    Movie m2 = new Movie
            ("2", "Title2", 2026, genres2, "Director2", cast2, 2.0, lang2, 200);
    Movie m3 = new Movie
            ("3", "Title3", 2026, List.of("genreX", "genreY"), "Director3", cast1,
                    3.0, lang2, 300);

    List<Movie> movieList = List.of(m1, m2, m3);
    Functions functions = new Functions();
    MovieSearchInterface genres = m -> m.getGenres();
    MovieSearchInterface languages = m -> m.getLanguages();

    @Test
    void uniqueGenresTest() {
        long result = functions.countUnique(movieList, genres);
        assertEquals(8, result);
    }

    @Test
    void uniqueLanguagesTest() {
        long result = functions.countUnique(movieList, languages);
        assertEquals(6, result);
    }

    @Test
    void countMoviesTest() {
        long result = functions.countMovies(movieList);
        assertEquals(3, result);
    }

    @Test
    void longestMovieTest() {
        long result = functions.longestMovie(movieList);
        assertEquals(300, result);
    }

    @Test
    void actorsInHighestRatedMovieTest() {
        List<String> result = functions.actorsInHighestRatedMovie(movieList);
        assertEquals(cast1, result);
    }

    @Test
    void leastActorsTest() {
        String result = functions.leastActors(movieList);
        assertEquals("Title2", result);
    }

    @Test
    void actorsInMultipleMoviesTest() {
        long result = functions.actorsInMultipleMovies(movieList);
        assertEquals(3, result);
    }

    @Test
    void actorInMostMoviesTest() {
        String result = functions.actorInMostMovies(movieList);
        assertEquals("actor1", result);
    }

    @Test
    void duplicateTitlesTest() {
        boolean result = functions.duplicateTitles(movieList);
        assertEquals(false, result);
    }


}