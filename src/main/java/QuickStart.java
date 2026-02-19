

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuickStart {
    public static void main(String[] args) throws IOException {

        Functions f = new Functions();
        Properties p = new Properties();
        p.load(new FileInputStream("src/main/java/settings.properties"));

        try (MongoClient mongoClient = MongoClients.create(p.getProperty("connStr"))) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");


            List<Movie> movieList = new ArrayList<>();
            for (Document doc : collection.find(new Document("year", 1975))) {
                movieList.add(Movie.fromDocument(doc));
            }

            //f.printAllMovies(movieList);

            System.out.println("Amount of movies made in 1975: " + f.countMovies(movieList));
            System.out.println("Length of the longest movie: " + f.longestMovie(movieList));
            System.out.println("Amount of unique genres: " + f.countUniqueGenres(movieList));
            System.out.println("Actors in the highest imdb-rated movie: " + f.actorsInHighestRatedMovie(movieList));
            System.out.println("Title of the movie with the least actors: " + f.leastActors(movieList));
            System.out.println("Amount of actors that participated in more than one movie: " +
                    f.actorsInMultipleMovies(movieList));
            System.out.println("The name of the actor that participated in the most movies: " +
                    f.actorInMostMovies(movieList));
            System.out.println("Amount of unique languages: " + f.countUniqueLang(movieList));
            System.out.println("There is at least one movie title that appears in more than one movie: " +
                    f.duplicateTitles(movieList));

        }
    }
}