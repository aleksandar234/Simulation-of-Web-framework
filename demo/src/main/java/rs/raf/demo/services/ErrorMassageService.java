package rs.raf.demo.services;

import org.springframework.stereotype.Service;
import rs.raf.demo.model.ErrorMassage;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.ErrorMassageRepository;

import java.util.List;

@Service
public class ErrorMassageService {

    private final ErrorMassageRepository errorMassageRepository;

    public ErrorMassageService(ErrorMassageRepository errorMassageRepository) {
        this.errorMassageRepository = errorMassageRepository;
    }

    public ErrorMassage save(ErrorMassage errorMassage) {
        return errorMassageRepository.save(errorMassage);
    }

    public List<ErrorMassage> findByUser(User user) {
        return  errorMassageRepository.findByUser(user);
    }

}
