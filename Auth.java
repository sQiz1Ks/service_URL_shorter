import java.util.HashMap;
import java.util.Scanner;

public class Auth {
    private final HashMap users = new HashMap<String, String>();
    private final Scanner scanner;

    public Auth(){
        scanner = new Scanner(System.in);

        users.put("admin", "password123");
        users.put("user", "qwerty");
    }
    public boolean login(){
        System.out.println("User authorization");
        while (true){
            System.out.println("Your login");
            String username = scanner.nextLine().trim();

            System.out.println("Your password");
            String password = scanner.nextLine().trim();

            if (authCheck(username, password)){
                System.out.println("Authorization complete");
                return true;
            }else {
                System.out.println("Authorization failed");
                return false;
            }
        }
    }
    private boolean authCheck(String user, String pass){
        if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
            return false;
        }
        return users.containsKey(user) && users.get(user).equals(pass);
    }
}

