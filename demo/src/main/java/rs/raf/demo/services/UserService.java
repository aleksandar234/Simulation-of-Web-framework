package rs.raf.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = this.userRepository.findByUsername(username);
        if(myUser == null) {
            throw new UsernameNotFoundException("User name "+username+" not found");
        }
        // Treci argument je lista rola koju mozemo da prosledimo
        // Posto trenutno nisam prosledjivao role
        // nego sam radio sam authentifikaciju prosledjujem prazan niz
        // Ovde mu prosledjujem rolu i onda u jwtFilteru

//        System.out.println(myUser.getPermissions());

//        List<SimpleGrantedAuthority> auth = new ArrayList<>();
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("GET");
//        auth.add(authority);
        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), new ArrayList<>());
    }

    public User findByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
