package service;

import dto.UserRequest;
import model.Contact;
import model.Location;
import model.User;
import repository.UserRepository;

public class UserService implements IUserService {

    UserRepository userRepository;

    public UserService() {
        userRepository = UserRepository.getUserRepository();
    }

    @Override
    public boolean addUser(UserRequest userRequest) {
        User user = new User();
        Location location = new Location(userRequest.getxLocation(), userRequest.getyLocation());
        user.setId(userRequest.getId());
        user.setLocation(location);
        user.setName(userRequest.getName());

        Contact contact = new Contact();
        contact.setEmail(userRequest.getEmail());
        contact.setPhoneNumber(userRequest.getPhoneNumber());
        user.setContact(contact);

        userRepository.addNewUser(user);
        return true;
    }

    @Override
    public boolean updateUserLocation(String id, Integer xCoordinate, Integer yCoordinate) {
        Location location = new Location(xCoordinate, yCoordinate);
        userRepository.updateLocationOfUser(id, location);
        return true;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }


}
