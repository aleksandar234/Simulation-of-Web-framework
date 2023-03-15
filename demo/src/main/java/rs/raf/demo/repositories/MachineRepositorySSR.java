package rs.raf.demo.repositories;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import rs.raf.demo.model.ErrorMassage;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Permiss;
import rs.raf.demo.permiss.Status;
import rs.raf.demo.services.ErrorMassageService;
import rs.raf.demo.services.MachineService;
import rs.raf.demo.services.PermissionService;
import rs.raf.demo.services.UserService;

import java.util.*;

@Repository
public class MachineRepositorySSR implements SSRRepository{

    private final WebClient webClient;
    private final MachineService machineService;
    private final UserService userService;
    private final ErrorMassageService errorMassageService;
    private static List<Machine> listOfCalls = new ArrayList();
    private final PermissionService permissionService;

    public MachineRepositorySSR(WebClient.Builder webClientBuilder, MachineService machineService, UserService userService, ErrorMassageService errorMassageService, PermissionService permissionService) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/machines").build();
        this.machineService = machineService;
        this.userService = userService;
        this.errorMassageService = errorMassageService;
        this.permissionService = permissionService;
    }


    @Override
    public void startMachineAsync(Long id, User user, String jwt) {
//        System.out.println("IDDDDDDDDDDDDD:"  + id);
        this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getMachine/{id}")
                        .build(id))
                .header("Authorization", "Bearer " + jwt)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Machine>(){})
                .subscribe(machine1 -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Zavrsio sam:" + machine1.getBody().getName());
                    if(machine1.getBody().getStatus() == Status.STOPPED) {
                        Objects.requireNonNull(machine1.getBody()).setStatus(Status.RUNNING);
                        machine1.getBody().setUser(user);
                        machineService.save(machine1.getBody());
                        System.out.println("FINISHED!!");
                    } else {
                        System.out.println("Masina nije bila u STOPPED stanju");
                        errorMassage(machine1, "Operation START", user, "Pokusaj pokretanja masine koja je vec bila u stanju RUNNING");
                    }
                });
        System.out.println(ResponseEntity.ok(200));
        System.out.println("Finished starting async machine");
    }

    private void errorMassage(ResponseEntity<Machine> machine1, String operation, User user, String errorM) {
        ErrorMassage errorMassage = new ErrorMassage();
        listOfCalls.add(machine1.getBody());
        Date date = new Date();
        errorMassage.setDate(date);
        errorMassage.setMachineId(machine1.getBody().getId());
        errorMassage.setReservedOperation(operation);
        errorMassage.setUser(user);
        errorMassage.setErrorMassage(errorM);
        errorMassageService.save(errorMassage);
        System.out.println("Velicina neke liste: " + listOfCalls.size());
    }

    public static List getListOfCalls() {
        return listOfCalls;
    }

    public static void setListOfCalls(List listOfCalls) {
        MachineRepositorySSR.listOfCalls = listOfCalls;
    }

    @Override
    public void stopMachineAsync(Long id, User user, String jwt) {
        int flag = 0;
        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
        for(Permisije p: permisije) {
            if(p.getPermission().equals(Permiss.CAN_RESTART_MACHINES)) {
                flag = 1;
                break;
            }
        }
        if(flag == 0) {
            System.out.println("No permission for this action");
            return;
        }
        System.out.println("IDDDDDDDDDDDDD:"  + id);
        this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getMachine/{id}")
                        .build(id))
                .header("Authorization", "Bearer " + jwt)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Machine>(){})
                .subscribe(machine1 -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Zavrsio sam:" + machine1.getBody().getName());
                    if(machine1.getBody().getStatus() == Status.RUNNING) {
                        Objects.requireNonNull(machine1.getBody()).setStatus(Status.STOPPED);
                        machine1.getBody().setUser(user);
                        machineService.save(machine1.getBody());
                        System.out.println("FINISHED!!");
                    } else {
                        System.out.println("Masina nije bila u RUNNING stanju");
                        errorMassage(machine1, "Operation STOP", user, "Pokusaj zaustavljanja masine koja je vec bila u stanju STOPPED");
                    }
                });
        System.out.println(ResponseEntity.ok(200));
        System.out.println("Finished stopping async machine");
    }

    @Override
    public void restartMachineAsync(Long id, User user, String jwt) {
        int flag = 0;
        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
        for(Permisije p: permisije) {
            if(p.getPermission().equals(Permiss.CAN_START_MACHINES)) {
                flag = 1;
                break;
            }
        }
        if(flag == 0) {
            System.out.println("No permission for this action");
            return;
        }
        System.out.println("IDDDDDDDDDDDDD:"  + id);
        this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getMachine/{id}")
                        .build(id))
                .header("Authorization", "Bearer " + jwt)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Machine>(){})
                .subscribe(machine1 -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(machine1.getBody().getStatus() == Status.RUNNING) {
                        System.out.println("Prvi deo prebacujem iz :" + machine1.getBody().getStatus().toString());
                        Objects.requireNonNull(machine1.getBody()).setStatus(Status.STOPPED);
                        machine1.getBody().setUser(user);
                        machineService.save(machine1.getBody());
                        System.out.println("Sada je stanje masine: " + machine1.getBody().getName() + " sa statusom " + machine1.getBody().getStatus().toString());
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Prebacujem opet u stanje RUNNING");
                        Objects.requireNonNull(machine1.getBody()).setStatus(Status.RUNNING);
                        machine1.getBody().setUser(user);
                        machineService.save(machine1.getBody());
                        System.out.println("FINISHED!!");
                    } else {
                        System.out.println("Masina nije bila u RUNNING stanju");
                        errorMassage(machine1, "Operation RESTART", user, "Pokusaj restartovanja masine koja nije bila u stanju START");
                    }
                });
        System.out.println(ResponseEntity.ok(200));
        System.out.println("Finished restarting async machine");
    }

    @Override
    public <S extends Machine> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Machine> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Machine> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Machine> findAll() {
        return null;
    }

    @Override
    public Iterable<Machine> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Machine entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Machine> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
