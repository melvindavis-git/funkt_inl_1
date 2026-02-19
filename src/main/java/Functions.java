import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String leastActors(List<Movie> ml) {
        return ml.stream().min(Comparator.comparingInt(m -> m.getCast().size()))
                .map(m -> m.getTitle()).orElse("");
    }

    public long actorsInMultipleMovies(List<Movie> ml) {
        Map<String, Long> aMap = ml.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        Long aCount = aMap.values().stream().filter(c -> c > 1).count();
        return aCount;
    }

    public String actorInMostMovies(List<Movie> ml) {
        Map<String, Long> aMap = ml.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        Long aCountMax = aMap.values().stream().max(Comparator.comparingLong(c -> c)).orElse(0L);
        String name = aMap.keySet().stream().filter(n -> aMap.get(n).equals(aCountMax))
                .findAny().orElse("");
        return name;
    }

    public long countUniqueLang(List<Movie> ml) {
        return ml.stream().flatMap(m -> m.getLanguages().stream()).distinct().count();
    }

    public boolean duplicateTitles(List<Movie> ml) {
        Map<String, Long> tMap = ml.stream().map(m -> m.getTitle())
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        Long tCount = tMap.values().stream().filter(t -> t > 1).count();
        if (tCount >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void printAllMovies(List<Movie> ml) {
        for (Movie m : ml) {
            System.out.println(m);
        }
    }

}