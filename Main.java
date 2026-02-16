import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Main {
    public static int mainmenu(){
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
    public static int usermenu(){
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
    public static int adminmenu(){
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
    public static int urlmenu(){
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
    public static void main(String[] args) {
        String pswrd;
        String url;
        String uuid = "";
        String shurl;
        String lftime;
        String code;
        int nusage;
        int mmenu;
        int urmenu;
        int umenu;
        List<Object> curent;
        Auth auth = new Auth();
        URL_short urlShort = new URL_short();
        DBase dBase = new DBase();
        List<Object> urlf = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        mmenu = mainmenu();
        while (mmenu != 3){
            switch (mmenu){
                case (1):
                    System.out.println("Please enter your URL");
                    url = scanner.nextLine().trim();
                    System.out.println("You are new user, please enter your password");
                    pswrd = scanner.nextLine().trim();
                    uuid = UUID.randomUUID().toString();
                    auth.register(uuid, pswrd);
                    System.out.println("Your registration complete, here your UUID: " + uuid);
                    System.out.println("And your password: " + pswrd);
                    shurl = urlShort.shorten(url);
                    System.out.println("Your URL is has been shorted: http://clck.ru/" + shurl);
                    System.out.println("Please enter lifespan of your short URL in format of MM:DD:YYYY");
                    lftime = scanner.nextLine().trim();
                    System.out.println("Please enter number of uses of your short URL");
                    nusage = scanner.nextInt();
                    urlf.add(shurl);
                    urlf.add(lftime);
                    urlf.add(nusage);
                    dBase.addObject(uuid, urlf);
                    urmenu = usermenu();
                    while (urmenu != 3){
                    switch (urmenu) {
                        case (1):
                            System.out.println("Please enter your URL");
                            url = scanner.nextLine().trim();
                            shurl = urlShort.shorten(url);
                            System.out.println("Your URL is has been shorted: http://clck.ru/" + shurl);
                            System.out.println("Please enter lifespan of your short URL in format of MM:DD:YYYY");
                            lftime = scanner.nextLine().trim();
                            System.out.println("Please enter number of uses of your short URL");
                            nusage = scanner.nextInt();
                            urlf.add(shurl);
                            urlf.add(lftime);
                            urlf.add(nusage);
                            dBase.addObject(uuid, urlf);
                            break;
                        case (2):
                            System.out.println("Here all your's URL's [[code, date, usege]]");
                            System.out.println(dBase.getObjects(uuid));
                            umenu = urlmenu();
                            while (umenu != 5) {
                                switch (umenu) {
                                    case (1):
                                        System.out.println("Please enter your URL");
                                        url = scanner.nextLine().trim();
                                        shurl = urlShort.shorten(url);
                                        System.out.println("Your URL is has been shorted: http://clck.ru/" + shurl);
                                        System.out.println("Please enter lifespan of your short URL in format of MM:DD:YYYY");
                                        lftime = scanner.nextLine().trim();
                                        System.out.println("Please enter number of uses of your short URL");
                                        nusage = scanner.nextInt();
                                        urlf.add(shurl);
                                        urlf.add(lftime);
                                        urlf.add(nusage);
                                        dBase.addObject(uuid, urlf);
                                        break;
                                    case (2):
                                        System.out.println("Which URL you want to delete, please enter required index");
                                        int index = scanner.nextInt();
                                        curent = Arrays.asList(dBase.getObjects(uuid)).get(index);
                                        urlShort.delete(curent.get(0).toString());
                                        dBase.removeObject(uuid, index);
                                        System.out.println("URL was delete");
                                        dBase.getObjects(uuid);
                                        break;
                                    case (3):
                                        for (int i = 0; i < Arrays.asList(dBase.getObjects(uuid)).size(); i++) {
                                            curent = Arrays.asList(dBase.getObjects(uuid)).get(i);
                                            urlShort.delete(curent.get(0).toString());
                                        }
                                        dBase.clearUserObjects(uuid);
                                        System.out.println("Your URL base was cleared");
                                        dBase.getObjects(uuid);
                                        break;
                                    case (4):

                                        System.out.println("Please enter short URL code");
                                        code = scanner.nextLine().trim();
                                        URI uri = null;
                                        try {
                                            uri = new URI(urlShort.expand(code));
                                        } catch (URISyntaxException e) {
                                            throw new RuntimeException(e);
                                        }
                                        System.out.println(urlShort.expand(code));

                                        try {
                                            Desktop.getDesktop().browse(uri);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                }
                                umenu = urlmenu();
                            }
                            break;
                    }
                    urmenu = usermenu();
                    }
                case(2):
                    if (auth.login()){
                        urmenu = usermenu();
                        while (urmenu != 3){
                            switch (urmenu) {
                                case (1):
                                    System.out.println("Please enter your URL");
                                    url = scanner.nextLine().trim();
                                    shurl = urlShort.shorten(url);
                                    System.out.println("Your URL is has been shorted: http://clck.ru/" + shurl);
                                    System.out.println("Please enter lifespan of your short URL in format of MM:DD:YYYY");
                                    lftime = scanner.nextLine().trim();
                                    System.out.println("Please enter number of uses of your short URL");
                                    nusage = scanner.nextInt();
                                    urlf.add(shurl);
                                    urlf.add(lftime);
                                    urlf.add(nusage);
                                    dBase.addObject(uuid, urlf);
                                    break;
                                case (2):
                                    System.out.println("Here all your's URL's [[code, date, usege]]");
                                    System.out.println(dBase.getObjects(uuid));
                                    while (urlmenu() != 5) {
                                        switch (urlmenu()) {
                                            case (1):
                                                System.out.println("Please enter your URL");
                                                url = scanner.nextLine().trim();
                                                shurl = urlShort.shorten(url);
                                                System.out.println("Your URL is has been shorted: http://clck.ru/" + shurl);
                                                System.out.println("Please enter lifespan of your short URL in format of MM:DD:YYYY");
                                                lftime = scanner.nextLine().trim();
                                                System.out.println("Please enter number of uses of your short URL");
                                                nusage = scanner.nextInt();
                                                urlf.add(shurl);
                                                urlf.add(lftime);
                                                urlf.add(nusage);
                                                dBase.addObject(uuid, urlf);
                                                break;
                                            case (2):
                                                System.out.println("Which URL you want to delete, please enter required index");
                                                int index = scanner.nextInt();
                                                curent = Arrays.asList(dBase.getObjects(uuid)).get(index);
                                                urlShort.delete(curent.get(0).toString());
                                                dBase.removeObject(uuid, index);
                                                System.out.println("URL was delete");
                                                dBase.getObjects(uuid);
                                                break;
                                            case (3):
                                                for (int i = 0; i < dBase.getObjects(uuid).size(); i++) {
                                                    curent = Arrays.asList(dBase.getObjects(uuid)).get(i);
                                                    urlShort.delete(curent.get(0).toString());
                                                }
                                                dBase.clearUserObjects(uuid);
                                                System.out.println("Your URL base was cleared");
                                                dBase.getObjects(uuid);
                                                break;
                                            case (4):
                                                System.out.println("Please enter short URL code");
                                                code = scanner.nextLine().trim();
                                                URI uri = null;
                                                try {
                                                    uri = new URI(urlShort.expand(code));
                                                } catch (URISyntaxException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                System.out.println(urlShort.expand(code));

                                                try {
                                                    Desktop.getDesktop().browse(uri);
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                break;
                                        }

                                    }
                                    break;
                            }
                            urmenu = usermenu();
                        }
                    } else {
                        System.out.println("Something gone wrong please try again");
                    }

                    break;
            }
            mmenu = mainmenu();
        }
    }
}
