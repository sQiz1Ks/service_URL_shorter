import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class URL_short {
    public static final HashMap<String, String> UrlMap = new HashMap<>();
    private final Random random = new Random();
    private static final String chrset = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int codelth = 6;

    public String generateCode() {
        StringBuilder sb = new StringBuilder(codelth);
        for (int i = 0; i < codelth; i++) {
            sb.append(chrset.charAt(random.nextInt(chrset.length())));
        }
        return sb.toString();
    }

    public String expand(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        return UrlMap.get(code);
    }

    public boolean delete(String code) {
        if (UrlMap.containsKey(code)){
            UrlMap.remove(code);
            return true;
        } else {
            System.out.println("No URL for this code find");
            return false;
        }


    }

    public String shorten(String longUrl) {
        String code = generateCode();
        while (UrlMap.containsKey(code)){
            code = generateCode();
        }
        UrlMap.put(code, longUrl);
        return code;
    }
}
