import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class URL_short {
    public final HashMap<String, String> UrlMap = new HashMap<>();
    private final Random random = new Random();
    private static final String chrset = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int codelth = 6;
    private boolean isValid(String url) {
        if (url == null || url.isEmpty()) {
            System.out.println("Empty URL");
            return false;
        }
        try {
            new URL(url);
            System.out.println("Valid URL");
            return true;
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
            return false;
        }
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(codelth);
        for (int i = 0; i < codelth; i++) {
            sb.append(chrset.charAt(random.nextInt(chrset.length())));
        }
        return sb.toString();
    }

    public boolean containsCode(String code) {
        return code != null && UrlMap.containsKey(code);
    }

    public String expand(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        return UrlMap.get(code);
    }

    public boolean delete(String code) {
        if (code == null) {
            return false;
        }
        return UrlMap.remove(code) != null;
    }

    public String shorten(String longUrl) {
        if (!isValid(longUrl)) {
            return null;
        }
        String code;
        do {
            code = generateCode();
        } while (UrlMap.containsKey(code)); // Убеждаемся, что код ещё не занят

        UrlMap.put(code, longUrl);
        return code;
    }

    public static void main(String[] args) {
        URL_short urlShort = new URL_short();
        String longUrl = "https://stackoverflow.com/questions/29312243/how-to-cast-a-hashmap-to-map";
        String code = urlShort.shorten(longUrl);
        System.out.println("http://clck.ru/"+code);
        try {
            Desktop.getDesktop().browse(new URI(urlShort.expand(code)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
