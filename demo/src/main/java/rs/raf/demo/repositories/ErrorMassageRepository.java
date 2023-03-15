package rs.raf.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.ErrorMassage;
import rs.raf.demo.model.User;

import java.util.List;

@Repository
public interface ErrorMassageRepository extends CrudRepository<ErrorMassage, Long> {

    List<ErrorMassage> findByUser(User user);

}
