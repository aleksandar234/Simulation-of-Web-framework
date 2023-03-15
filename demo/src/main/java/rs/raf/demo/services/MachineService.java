package rs.raf.demo.services;

import org.springframework.stereotype.Service;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Status;
import rs.raf.demo.repositories.MachineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    public Optional<Machine> findById(Long id) {
        return machineRepository.findById(id);
    }

    public List<Machine> findAll() {
        return (List<Machine>) machineRepository.findAll();
    }

    public void delete(Long id) {
        machineRepository.deleteById(id);
    }

    public List<Machine> findAllByUserId(Long userId) {
        return machineRepository.findAllByUserId(userId);
    }

    public List<Machine> findByName(String name) {
        return machineRepository.findByName(name);
    }

    public List<Machine> findByNameContaining(String name) {
        return machineRepository.findByNameContaining(name);
    }

    public List<Machine> findByStatus(Status status) {
        return machineRepository.findByStatus(status);
    }





}
