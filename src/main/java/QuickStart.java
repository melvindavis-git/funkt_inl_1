

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

            for (Movie m : movieList) {
                System.out.println(m);
            }

            System.out.println(f.countMovies(movieList));
            System.out.println(f.longestMovie(movieList));
            System.out.println(f.countUniqueGenres(movieList));
            System.out.println(f.actorsInHighestRatedMovie(movieList));


        }
    }
}