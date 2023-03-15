package rs.raf.demo.controllers;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.ErrorMassage;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Permiss;
import rs.raf.demo.services.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/ssr")
public class MachineControllerSSR {

    private final MachineServiceSSR machineServiceSSR;
    private final UserService userService;
    private final MachineService machineService;
    private final MyCronService myCronService;
    private final TaskScheduler taskScheduler;
    private final ErrorMassageService errorMassageService;

    public MachineControllerSSR(MachineServiceSSR machineServiceSSR, UserService userService, MachineService machineService, MyCronService myCronService, TaskScheduler taskScheduler, ErrorMassageService errorMassageService) {
        this.machineServiceSSR = machineServiceSSR;
        this.userService = userService;
        this.machineService = machineService;
        this.myCronService = myCronService;
        this.taskScheduler = taskScheduler;
        this.errorMassageService = errorMassageService;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping(value = "/scheduleStart", produces = MediaType.APPLICATION_JSON_VALUE)
//    @Scheduled(cron = "0 * * * * *", zone = "Europe/Belgrade")
//    @Scheduled(cron = "#{@this.generateScheduleStart()}", zone = "Europe/Belgrade")
//    public void runScheduleStart() {
//        System.out.println("Nesto sam ispisao");
//    }
//
//    @Scheduled(cron = "0 * * * * *", zone = "Europe/Belgrade")
//    public void runScheduleStop() {
//        System.out.println("Nesto sam stop22222");
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/scheduleStart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateScheduleStart(@RequestBody Machine machine) {
        String jwt = machine.getAngularDate().split(" ")[0];
        System.out.println(jwt);
        String time = machine.getAngularDate().split(" ")[1];
        System.out.println(time);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        CronTrigger cronTrigger = myCronService.getCronExpression(time);
        this.taskScheduler.schedule(() -> {
            User user = userService.findByUserName(userName);
            List<Machine> machines = machineService.findByName(machine.getName());
            Machine machine1 = new Machine();
            for(Machine m: machines) {
                if(m.getUser().getUserId().equals(user.getUserId())) {
                    machine1 = m;
                    break;
                }
            }
            this.machineServiceSSR.startAsyncMachine(machine1.getId(), user, jwt);
        }, cronTrigger);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/scheduleStop", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateScheduleStop(@RequestBody Machine machine) {
        String jwt = machine.getAngularDate().split(" ")[0];
        System.out.println(jwt);
        String time = machine.getAngularDate().split(" ")[1];
        System.out.println(time);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        CronTrigger cronTrigger = myCronService.getCronExpression(time);
        this.taskScheduler.schedule(() -> {
            User user = userService.findByUserName(userName);
            List<Machine> machines = machineService.findByName(machine.getName());
            Machine machine1 = new Machine();
            for(Machine m: machines) {
                if(m.getUser().getUserId().equals(user.getUserId())) {
                    machine1 = m;
                    break;
                }
            }
            this.machineServiceSSR.stopAsyncMachine(machine1.getId(), user, jwt);
        }, cronTrigger);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/scheduleRestart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateScheduleRestart(@RequestBody Machine machine) {
        String jwt = machine.getAngularDate().split(" ")[0];
        System.out.println(jwt);
        String time = machine.getAngularDate().split(" ")[1];
        System.out.println(time);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        CronTrigger cronTrigger = myCronService.getCronExpression(time);
        this.taskScheduler.schedule(() -> {
            User user = userService.findByUserName(userName);
            List<Machine> machines = machineService.findByName(machine.getName());
            Machine machine1 = new Machine();
            for(Machine m: machines) {
                if(m.getUser().getUserId().equals(user.getUserId())) {
                    machine1 = m;
                    break;
                }
            }
            this.machineServiceSSR.restartAsyncMachine(machine1.getId(), user, jwt);
        }, cronTrigger);
    }

    // Ovde u zagradama kad zovem sa fronta treba da se uzme Long id i tjt
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/startMachine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void startMachineAsync(@RequestBody Machine machine) {
        System.out.println("Ovo je prosledjeni jwt sa fronta: " + machine.getAngularDate());
        String jwt = machine.getAngularDate();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        List<Machine> machines = machineService.findByName(machine.getName());
        Machine machine1 = new Machine();
        for(Machine m: machines) {
            if(m.getUser().getUserId().equals(user.getUserId())) {
                machine1 = m;
                break;
            }
        }
        this.machineServiceSSR.startAsyncMachine(machine1.getId(), user, jwt);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/stopMachine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void stopMachineAsync(@RequestBody Machine machine) {
        System.out.println("Ovo je prosledjeni jwt sa fronta: " + machine.getAngularDate());
        String jwt = machine.getAngularDate();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        List<Machine> machines = machineService.findByName(machine.getName());
        Machine machine1 = new Machine();
        for(Machine m: machines) {
            if(m.getUser().getUserId().equals(user.getUserId())) {
                machine1 = m;
                break;
            }
        }
        this.machineServiceSSR.stopAsyncMachine(machine1.getId(), user, jwt);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/restartMachine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void restartMachineAsync(@RequestBody Machine machine) {
        System.out.println("Ovo je prosledjeni jwt sa fronta: " + machine.getAngularDate());
        String jwt = machine.getAngularDate();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        List<Machine> machines = machineService.findByName(machine.getName());
        Machine machine1 = new Machine();
        for(Machine m: machines) {
            if(m.getUser().getUserId().equals(user.getUserId())) {
                machine1 = m;
                break;
            }
        }
        this.machineServiceSSR.restartAsyncMachine(machine1.getId(), user, jwt);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/errorMassage")
    public ResponseEntity<?> errorMassage() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        List<ErrorMassage> em = errorMassageService.findByUser(user);
        for(ErrorMassage errorMassage: em) {
            System.out.println(errorMassage);
        }
        return ResponseEntity.ok(em);
    }






//        System.out.println(MachineRepositorySSR.getListOfCalls());
//        List<Machine> machines = new ArrayList<>();
//        for(int i = 0; i < MachineRepositorySSR.getListOfCalls().size(); i++) {
//            Machine m = (Machine) MachineRepositorySSR.getListOfCalls().get(i);
//            machines.add(m);
//        }
}
