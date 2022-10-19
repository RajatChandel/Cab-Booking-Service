package repository;

import model.Location;
import model.User;

public interface IUserRepository {
    boolean addNewUser(User user);
    User getUserById(String id);
    boolean updateLocationOfUser(String id, Location location);
}
