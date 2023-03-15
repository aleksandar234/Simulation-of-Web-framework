package rs.raf.demo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.raf.demo.model.*;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.permiss.Permiss;
import rs.raf.demo.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData{

//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public BootstrapData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("Loading Data...");
//
//
//
//
//        User user1 = new User();
//        user1.setUsername("user1");
//        user1.setFirstName("Aleksandar");
//        user1.setLastName("Stojanovic");
//        user1.setPassword(this.passwordEncoder.encode("user1"));
//        Permisije p1 = new Permisije();
//        p1.setPermission(Permiss.CAN_CREATE_USER);
//        List<Permisije> permissions = new ArrayList<>();
//        permissions.add(p1);
//        user1.setPermissions(permissions);
//        this.userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setUsername("user2");
//        user2.setFirstName("Nikola");
//        user2.setLastName("Nikolic");
//        user2.setPassword(this.passwordEncoder.encode("user2"));
//        Permisije p3 = new Permisije();
//        p3.setPermission(Permiss.CAN_READ_USER);
//        Permisije p4 = new Permisije();
//        p4.setPermission(Permiss.CAN_DELETE_USER);
//        List<Permisije> permissions2 = new ArrayList<>();
//        permissions2.add(p3);
//        permissions2.add(p4);
//        user2.setPermissions(permissions2);
//        this.userRepository.save(user2);
//
//        System.out.println("Data loaded!");
//    }
}
