import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DBase {
    private final HashMap<String, List<Object>> Dbase = new HashMap<>();

    public boolean addObject(String username, Object obj) {
        if (username == null || obj == null) {
            return false;
        }
        Dbase.computeIfAbsent(username, k -> new ArrayList<>()).add(obj);
        return true;
    }

    public List<Object> getObjects(String username) {
        if (username == null) {
            return Collections.emptyList();
        }
        return Dbase.getOrDefault(username, Collections.emptyList());
    }

    public boolean removeObject(String username, int index) {
        List<Object> objects = Dbase.get(username);
        if (objects == null || index < 0 || index >= objects.size()) {
            return false;
        }
        objects.remove(index);
        return true;
    }

    public boolean clearUserObjects(String username) {
        if (username == null) {
            return false;
        }
        return Dbase.remove(username) != null;
    }

}
