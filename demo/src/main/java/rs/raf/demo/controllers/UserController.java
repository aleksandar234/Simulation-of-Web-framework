package rs.raf.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.PermissionRepository;
import rs.raf.demo.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @PostMapping("/placePermission")
    public User userPermission(@RequestBody User user) {
        return userRepository.save(user);
    }


}
