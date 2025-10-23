package br.com.bookfinder.service;
import br.com.bookfinder.model.Book;
import com.google.gson.*;
import java.net.http.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openlibrary.org/search.json?q=" + query.replace(" ", "+") + "&limit=5"))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!json.has("docs")) return books;

            json.getAsJsonArray("docs").forEach(item -> {
                JsonObject info = item.getAsJsonObject();

                String title = info.has("title") ? info.get("title").getAsString() : "Sem título";

                String authors = "Desconhecido";
                if (info.has("author_name")) {
                    JsonArray authorArray = info.getAsJsonArray("author_name");
                    authors = authorArray.toString(); // ou você pode juntar em string legível
                }

                String date = info.has("first_publish_year") ? info.get("first_publish_year").getAsString() : "N/A";

                books.add(new Book(title, authors, date));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
