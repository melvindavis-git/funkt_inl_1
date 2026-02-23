import java.util.List;

@FunctionalInterface
public interface MovieSearchInterface {

    List<String> search(Movie m);

}