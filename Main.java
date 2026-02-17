import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Auth auth = new Auth();
    DBase dBase = new DBase();
    URL_short urlShort = new URL_short();
    public static int mainmenu() {
        int option;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create short URL");
        System.out.println("2 - Login");
        System.out.println("3 - Quit");

        option = input.nextInt();
        return option;
    }

    public static int usermenu() {
        int option;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create new short URL");
        System.out.println("2 - Show my URL's");
        System.out.println("3 - Main menu");

        option = input.nextInt();
        return option;
    }

    public static int adminmenu() {
        int option;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create new short URL");
        System.out.println("2 - Show my URL's");
        System.out.println("3 - Show all URL's");
        System.out.println("4 - Manage URL's");
        System.out.println("5 - Main menu");

        option = input.nextInt();
        return option;
    }

    public static int urlmenu() {
        int option;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create new short URL");
        System.out.println("2 - Delete URL");
        System.out.println("3 - Clear URL base");
        System.out.println("4 - Enter short URL");
        System.out.println("5 - Main menu");

        option = input.nextInt();
        return option;
    }
    public void mmenu(){
        switch (mainmenu()){
            case(1):
                String uuid = UUID.randomUUID().toString();
                System.out.println("Please enter your URL");
                String longURL = scanner.nextLine().trim();
                System.out.println("You're a new user, please enter password");
                String pass = scanner.nextLine().trim();
                while(!auth.register(uuid, pass)){
                    System.out.println("Something gone wrong, try again");
                    pass = scanner.nextLine().trim();
                }
                System.out.println("Your registration is complete");
                System.out.println("This is your UUID: " + uuid);
                System.out.println("This is your shorted URL:" + urlShort.shorten(longURL));
                umenu();
                break;
            case(2):
                System.out.println("Please ");




        }
    }

    public static void main(String[] args) {
    }
}
