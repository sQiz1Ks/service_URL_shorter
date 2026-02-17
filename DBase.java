import java.util.*;

public class DBase {
    HashMap<String, List<Object>> dbase = new HashMap<>();
    int t;
    public void addObject(String username, List<Object> obj) {
        System.out.println(dbase.containsKey(username));
            if (dbase.containsKey(username)) {
                List<Object> userdata = dbase.get(username);
                System.out.println(userdata);
                if (!userdata.isEmpty()) {
                    if (t == 1){
                        List<Object> val = new ArrayList<>();
                        val.add(userdata);
                        val.add(obj);
                        dbase.put(username, val);
                        t = 0;
                    } else {
                        userdata.add(obj);
                        dbase.put(username, userdata);
                    }
                } else {
                    dbase.put(username, obj);
                    t = 1;
                }
            } else {
                dbase.put(username, obj);
                t = 1;
            }
    }

    public List<Object> getObjects(String username) {
        if (username == null) {
            return Collections.emptyList();
        }
        return dbase.getOrDefault(username, Collections.emptyList());
    }

    public boolean removeObject(String username, int index) {
        List<Object> objects = dbase.get(username);
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
        return dbase.remove(username) != null;
    }
    public String plnDbase(){
        String result = dbase.values()
                .stream()
                .map(Object::toString)
                .reduce("", String::concat);
        return result;
    }
}
