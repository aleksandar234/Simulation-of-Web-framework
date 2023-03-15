package rs.raf.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Permisije;

import java.util.List;

@Repository
public interface PermissionRepository extends CrudRepository<Permisije, Long> {
    List<Permisije> findAllByUserPermissionEquals(Long userPermission);
}
