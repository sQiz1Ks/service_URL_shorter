import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Auth {
    private final HashMap users = new HashMap<String, String>();
    private final Scanner scanner = new Scanner(System.in);
    public boolean login(){
        System.out.println("User authorization");
        while (true){
            System.out.println("Your UUID");
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
    public boolean register(String uuid, String password){
        while (users.containsKey(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        users.put(uuid, password);
        return true;
    }
}

