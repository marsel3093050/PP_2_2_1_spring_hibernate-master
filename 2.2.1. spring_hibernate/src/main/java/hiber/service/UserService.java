package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
//    void addOrUpdate(User user);
    List<User> listUsers();
    User getUserByCar(String model, int series);
}
