import { Component, OnInit } from '@angular/core';
import { ShowMachineService } from '../../services/show-machine.service';
import {StartMachineService} from '../../services/start-machine.service';
import {StopMachineService} from '../../services/stop-machine.service';
import {RestartMachineService} from '../../services/restart-machine.service';
import {ReservationMachineService} from '../../services/reservation-machine.service';
import {Machine} from '../../models';

@Component({
  selector: 'app-reservation-machine',
  templateUrl: './reservation-machine.component.html',
  styleUrls: ['./reservation-machine.component.css']
})
export class ReservationMachineComponent implements OnInit {

  machineList: any;
  machineSearchList: any;
  date: any;

  constructor(private reservationMachineService: ReservationMachineService,private stopMachineService: StopMachineService, private restartMachineService: RestartMachineService, private showMachineService: ShowMachineService) {

  }

  ngOnInit(): void {
    this.showMachineService.showMachines().subscribe((machines: any) => {
       console.log(machines);
       this.machineList = machines;
    })
  }

  reserveStartMachine(machine: any) {
    console.log(this.date);
    this.reservationMachineService.reservationStartMachine(machine, this.date).subscribe(machine=>{
      console.log("Reservation made for Starting machine...");
    })
  }

  reserveStopMachine(machine: any) {
    this.reservationMachineService.reservationStopMachine(machine, this.date).subscribe(machine=>{
      console.log("Reservation made for Stopping machine...");
    })
  }

  reserveRestartMachine(machine: any) {
      this.reservationMachineService.reservationRestartMachine(machine, this.date).subscribe(machine=>{
        console.log("Reservation made for Restarting machine...");
      })
    }

}
