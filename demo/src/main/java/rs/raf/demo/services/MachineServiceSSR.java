package rs.raf.demo.services;

import org.springframework.stereotype.Service;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.User;
import rs.raf.demo.repositories.SSRRepository;

@Service
public class MachineServiceSSR {

    private SSRRepository ssrRepository;

    public MachineServiceSSR(SSRRepository ssrRepository) {
        this.ssrRepository = ssrRepository;
    }

    public void startAsyncMachine(Long id, User user, String jwt) {
        ssrRepository.startMachineAsync(id, user, jwt);
    }

    public void stopAsyncMachine(Long id, User user, String jwt) {
        ssrRepository.stopMachineAsync(id, user, jwt);
    }

    public void restartAsyncMachine(Long id, User user, String jwt) {
        ssrRepository.restartMachineAsync(id, user, jwt);
    }
}
