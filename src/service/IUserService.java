package service;

import dto.UserRequest;
import model.Location;
import model.User;

public interface IUserService {
    boolean addUser(UserRequest userRequest);
    boolean updateUserLocation(String id, Integer xCoordinate, Integer yCoordinate);
    User getUserById(String id);
}
