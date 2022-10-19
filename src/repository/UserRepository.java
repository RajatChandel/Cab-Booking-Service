package repository;

import model.Location;
import model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements IUserRepository {

    private static final Object lock = new Object();
    private static Map<String, User> userMap;
    private static UserRepository userRepository = null;

    private UserRepository() {
        userMap = new ConcurrentHashMap<>();
    }

    public static UserRepository getUserRepository() {
        synchronized (lock) {
            if (userRepository == null) {
                userRepository = new UserRepository();
            }
        }
        return userRepository;
    }

    @Override
    public boolean addNewUser(User user) {
        try {
            userMap.put(user.getId(), user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public User getUserById(String id) {
        return userMap.get(id);
    }

    @Override
    public boolean updateLocationOfUser(String id, Location location) {
        try {
            User user = userMap.get(id);
            user.setLocation(location);
            userMap.put(user.getId(), user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
