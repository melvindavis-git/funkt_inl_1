import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Functions {

    public long countMovies(List<Movie> ml) {
        return ml.stream().count();
    }

    public int longestMovie(List<Movie> ml) {
        return ml.stream().mapToInt(m -> m.getRuntime()).max().orElse(0);
    }

    public long countUniqueGenres(List<Movie> ml) {
        return ml.stream().flatMap(m -> m.getGenres().stream()).distinct().count();
    }

    public List<String> actorsInHighestRatedMovie(List<Movie> ml) {
        return ml.stream().max(Comparator.comparingDouble(m -> m.getImdbRating()))
                .map(m -> m.getCast()).orElse(Collections.emptyList());
    }

}