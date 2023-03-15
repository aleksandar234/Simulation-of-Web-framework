package rs.raf.demo.services;

import org.springframework.stereotype.Service;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.repositories.PermissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permisije save(Permisije permisije) {
        return permissionRepository.save(permisije);
    }

    public Optional<Permisije> findById(Long id) {
        return permissionRepository.findById(id);
    }

    public List<Permisije> findAll() {
        return (List<Permisije>) permissionRepository.findAll();
    }

    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    public List<Permisije> findAllByUserPermissionEquals(Long userPermission) {
        return permissionRepository.findAllByUserPermissionEquals(userPermission);
    }


}
