import controller.BookController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookController controller = new BookController();
        System.out.print("Digite o nome do livro: ");
        String query = sc.nextLine();
        controller.search(query);
    }
}