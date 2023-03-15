package rs.raf.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.User;

import java.util.List;

// Ovaj NoRepositoryBean stavljamo zato sto nam ova metoda koju mi pisemo nema je u CrudRepositorijumu
@NoRepositoryBean
public interface SSRRepository extends CrudRepository<Machine, Long> {

    void startMachineAsync(Long id, User user, String jwt);
    void stopMachineAsync(Long id, User user, String jwt);
    void restartMachineAsync(Long id, User user, String jwt);

}
