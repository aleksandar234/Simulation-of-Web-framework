package rs.raf.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Permiss;
import rs.raf.demo.services.PermissionService;
import rs.raf.demo.services.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/useri")
public class PermissionController {

    private final PermissionService permissionService;
    private final UserService userService;

    public PermissionController(PermissionService permissionService, UserService userService) {
        this.permissionService = permissionService;
        this.userService = userService;
    }

    /**
     * READ
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        System.out.println(userName);
        for(Permisije p: user.getPermissions()) {
            System.out.println(p.toString());
        }
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_READ_USER)) {
                return ResponseEntity.ok(userService.findAll());
//            }
//        }
//        System.out.println();
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    /**
     * CREATE
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user){
        System.out.println();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User u = userService.findByUserName(userName);
        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(u.getUserId());
//        for(Permisije p: permisije) {
//            System.out.println(p);
//            if(p.getPermission().equals(Permiss.CAN_CREATE_USER)) {
                return ResponseEntity.ok(userService.save(user));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    /**
     * UPDATE
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User u = userService.findByUserName(userName);
        System.out.println(u.getPermissions());
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(u.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_UPDATE_USER)) {
                return ResponseEntity.ok(userService.save(user));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    /**
     * DELETE
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User u = userService.findByUserName(userName);
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(u.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_DELETE_USER)) {
                userService.deleteUser(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

}
