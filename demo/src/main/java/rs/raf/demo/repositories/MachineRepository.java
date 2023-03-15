package rs.raf.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Status;

import java.util.List;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {

    List<Machine> findByNameContaining(String name);
    List<Machine> findByName(String name);
    List<Machine> findByStatus(Status status);
    Machine findByStatus(Boolean status);
    List<Machine> findAllByUserId(Long userId);
}
