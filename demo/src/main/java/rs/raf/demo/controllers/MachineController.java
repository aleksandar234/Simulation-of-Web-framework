package rs.raf.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Period;
import rs.raf.demo.model.Permisije;
import rs.raf.demo.model.User;
import rs.raf.demo.permiss.Permiss;
import rs.raf.demo.permiss.Status;
import rs.raf.demo.repositories.PeriodRepository;
import rs.raf.demo.services.MachineService;
import rs.raf.demo.services.PermissionService;
import rs.raf.demo.services.UserService;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/machines")
@RestController
public class MachineController {

    private final MachineService machineService;
    private final UserService userService;
    private final PermissionService permissionService;
    private final PeriodRepository periodRepository;

    public MachineController(MachineService machineService, UserService userService, PermissionService permissionService, PeriodRepository periodRepository) {
        this.machineService = machineService;
        this.userService = userService;
        this.permissionService = permissionService;
        this.periodRepository = periodRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMachines(@RequestBody Machine machine){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User u = userService.findByUserName(userName);
        machine.setUser(u);
//        System.out.println(u.getPermissions());
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(u.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_SEARCH_MACHINES)) {
                u.getMachines().add(machine);
                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year  = localDate.getYear();
                int month = localDate.getMonthValue();
                int day   = localDate.getDayOfMonth();
                String y = String.valueOf(year);
                String m = String.valueOf(month);
                String d = String.valueOf(day);
                String finalDate = y + "-" + m + "-" + d;
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(finalDate);
                    Period period = new Period();
                    period.setStartDate(date1);
                    period.setEndDate(date1);
                    period.setMachine(machine);
                    List<Period> periods = new ArrayList<>();
                    periods.add(period);
                    machine.setPeriods(periods);
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String aDate = formatter.format(date1);
                    machine.setAngularDate(aDate);
                    periodRepository.save(period);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok(machineService.save(machine));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMachines(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        System.out.println(userName);
        for(Permisije p: user.getPermissions()) {
            System.out.println(p.toString());
        }
        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
        for(Permisije p: permisije) {
            if(p.getPermission().equals(Permiss.CAN_SEARCH_MACHINES)) {
                return ResponseEntity.ok(machineService.findAll());
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/machineUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        System.out.println(userName);
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_SEARCH_MACHINES)) {
                List<Machine> allActiveMachines = new ArrayList<>();
                for(Machine m: machineService.findAllByUserId(user.getUserId())) {
                    if(m.getActive()) {
                        allActiveMachines.add(m);
                    }
                }
                return ResponseEntity.ok(allActiveMachines);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/name={name}/status={status}/start={start}/end={end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMachinesPerParams(@PathVariable String name, @PathVariable String status, @PathVariable String start, @PathVariable String end) throws ParseException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        System.out.println(userName);
        System.out.println(name);

//        int flag = 0;
//        List<Permisije> permisije = permissionService.findAllByUserPermissionEquals(user.getUserId());
//        for(Permisije p: permisije) {
//            if(p.getPermission().equals(Permiss.CAN_SEARCH_MACHINES)) {
//                flag = 1;
//                break;
//            }
//        }
//        if(flag == 0) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden activity");
//        }

        boolean nameParam = false;
        boolean statusParam = false;
        boolean dateParam = false;
        if(name.length() > 3) nameParam = true;
        if(status.length() > 3) statusParam = true;
        if(start.length() > 3 && end.length() > 3) dateParam = true;

        String newName = name.substring(3);
        String newStatus = (status.substring(3)).toLowerCase();


        String newStart = start.substring(3);
        String newEnd = end.substring(3);
        Date dateStart = null;
        Date dateEnd = null;
        if(!newStart.equals("")) {
            dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(newStart);
        }
        if(!newEnd.equals("")) {
            dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(newEnd);
        }

        Boolean[] params = new Boolean[3];
        params[0] = nameParam;
        params[1] = statusParam;
        params[2] = dateParam;

        List<Machine> machineNames;
        Set<Machine> userMachinesStatus = new HashSet<>();
        List<Machine> machineStatus;
        Set<Machine> userMachinesNames = new HashSet<>();
        List<Machine> allMachines;
        Set<Machine> userMachinesDate = new HashSet<>();
        Set<Machine> finalUserMachinesDate = new HashSet<>();
        List<Machine> allUserMachinesStatus = new ArrayList<>();
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        for(int i = 0; i < 3; i++) {
            if(params[0]) {
                machineNames = machineService.findByNameContaining(newName);
                for(Machine m: machineNames) {
                    if(m.getUser().getId().equals(user.getId())) {
                        userMachinesNames.add(m);
                    }
                }
                flag1 = 1;
            }
            if(params[1]) {
                Status s;
                if(newStatus.equals("running")) {
                    s = Status.RUNNING;
                } else if(newStatus.equals("stopped")){
                    s = Status.STOPPED;
                } else if(newStatus.contains("stopped") && newStatus.contains("running")) {
                    allUserMachinesStatus = machineService.findAllByUserId(user.getUserId());
                    s = null;
                } else {
                    s = null;
                }
                if(allUserMachinesStatus.size() > 0) {
                    for(Machine m: allUserMachinesStatus) {
                        if(m.getUser().getId().equals(user.getId())) {
                            userMachinesStatus.add(m);
                        }
                    }
                } else {
                    machineStatus = machineService.findByStatus(s);
                    for(Machine m: machineStatus) {
                        if(m.getUser().getId().equals(user.getId())) {
                            userMachinesStatus.add(m);
                        }
                    }
                }
                flag2 = 1;
            }
            if(params[2]) {
                allMachines = machineService.findAll();
                for(Machine machine: allMachines) {
                    List<Period> p1 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndMachineId(dateStart, dateStart, machine.getId());
                    List<Period> p2 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndMachineId(dateEnd, dateEnd, machine.getId());
                    List<Period> p3 = periodRepository.findPeriodByStartDateAfterAndEndDateBeforeAndMachineId(dateStart, dateEnd, machine.getId());
                    List<Period> p4 = periodRepository.findPeriodByStartDateAndEndDateAndMachineId(dateStart, dateEnd, machine.getId());
                    if(p1.isEmpty() && p2.isEmpty() && p3.isEmpty() && p4.isEmpty()) {
                        System.out.println("Machine with id:" + machine.getId() + " not found in this period");
                    } else {
                        userMachinesDate.add(machine);
                    }
                }
                // Kad sam nasao sve masine koje se nalaze izmedju ovih vremenskih perioda sad treba da izbacim one koje
                // mi ne pripadaju ulogovanom user-u
                for(Machine m: userMachinesDate) {
                    if(m.getUser().getId().equals(user.getId())) {
                        finalUserMachinesDate.add(m);
                    }
                }
                flag3 = 1;
            }
        }


        Set<Machine> emptyList = new HashSet<>();
        if(userMachinesNames.size() == 0 || userMachinesStatus.size() == 0 || finalUserMachinesDate.size() == 0) {
            // U slucaju da sam ukucao ime i bilo koje drugo polje da sam uneo. Nista mi se ne poklapa sa imenom znaci
            // da ne treba da mi vrati ni jednu masinu.
            // Analogno je za sve ostale masine
            if((userMachinesNames.size() == 0 && flag1 == 1))
                return ResponseEntity.ok(emptyList);
            if(userMachinesStatus.size() == 0 && flag2 == 1)
                return ResponseEntity.ok(emptyList);
            if(finalUserMachinesDate.size() == 0 && flag3 == 1)
                return ResponseEntity.ok(emptyList);
            if(userMachinesNames.size() == 0 && finalUserMachinesDate.size() == 0)
                return ResponseEntity.ok(userMachinesStatus);
            if(userMachinesNames.size() == 0 && userMachinesStatus.size() == 0)
                return ResponseEntity.ok(finalUserMachinesDate);
            if(userMachinesStatus.size() == 0 && finalUserMachinesDate.size() == 0)
                return ResponseEntity.ok(userMachinesNames);
            if(userMachinesNames.size() == 0) {
                System.out.println("Uso u userMachineNames");
                return getMachinesWithoutName(userMachinesStatus, finalUserMachinesDate);
            }
            if(userMachinesStatus.size() == 0) {
                System.out.println("Uso u userMachineStatus");
                return getMachinesWithoutStatus(userMachinesNames, finalUserMachinesDate);
            }
            if(finalUserMachinesDate.size() == 0) {
                System.out.println("Uso u finalMachine");
                return getMachinesWithoutDate(userMachinesNames, userMachinesStatus);
            }
            System.out.println("Proso sam sve");
            List<Machine> allActiveMachines = new ArrayList<>();
            for(Machine m: machineService.findAllByUserId(user.getUserId())) {
                if(m.getActive()) {
                    allActiveMachines.add(m);
                }
            }
            return ResponseEntity.ok(allActiveMachines);
        }

        Set<Machine> machines = new HashSet<>();
        for(Machine m1: userMachinesNames) {
            for(Machine m2: userMachinesStatus) {
                for(Machine m3: finalUserMachinesDate) {
                    if(m1.getId().equals(m2.getId()) && m1.getId().equals(m3.getId())) {
                        machines.add(m1);
                    }
                }
            }
        }
        return ResponseEntity.ok(machines);
    }

    private ResponseEntity<?> getMachinesWithoutDate(Set<Machine> userMachinesNames, Set<Machine> userMachinesStatus) {
        Set<Machine> machines = new HashSet<>();
        for(Machine m2: userMachinesNames) {
            for(Machine m3: userMachinesStatus) {
                if(m2.getId().equals(m3.getId())) {
                    machines.add(m2);
                }
            }
        }
        return ResponseEntity.ok(machines);
    }

    private ResponseEntity<?> getMachinesWithoutStatus(Set<Machine> userMachinesNames, Set<Machine> finalUserMachinesDate) {
        Set<Machine> machines = new HashSet<>();
        for(Machine m2: userMachinesNames) {
            for(Machine m3: finalUserMachinesDate) {
                if(m2.getId().equals(m3.getId())) {
                    machines.add(m2);
                }
            }
        }
        return ResponseEntity.ok(machines);
    }

    private ResponseEntity<?> getMachinesWithoutName(Set<Machine> userMachinesStatus, Set<Machine> finalUserMachinesDate) {
        Set<Machine> machines = new HashSet<>();
        for(Machine m2: userMachinesStatus) {
            for(Machine m3: finalUserMachinesDate) {
                if(m2.getId().equals(m3.getId())) {
                    machines.add(m2);
                }
            }
        }
        return ResponseEntity.ok(machines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/getMachine/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Machine startMachineAsync(@PathVariable Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        List<Machine> usersMachines;
        usersMachines = machineService.findAllByUserId(user.getUserId());
        System.out.println("Velicina liste:" + usersMachines.size());
        Machine machine = new Machine();
        for(Machine m: usersMachines) {
            if(m.getId().equals(id)) {
                machine = m;
                break;
            }
        }
        machine.setUser(user);
        System.out.println("Ime masineee:" + machine.getName() + " ID Usera je:" + machine.getUser().getUserId());
        return machine;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> destroyMachine(@RequestBody Machine machine) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User u = userService.findByUserName(userName);

        Machine m1 = machineService.findByName(machine.getName()).get(0);

        Machine machineUpdate = new Machine();
        machineUpdate.setId(m1.getId());
        machineUpdate.setUser(m1.getUser());
        machineUpdate.setActive(false);
        machineUpdate.setStatus(m1.getStatus());
        machineUpdate.setName(m1.getName());

        machineService.delete(m1.getId());
        return ResponseEntity.ok(machineService.save(machineUpdate));

    }




}
