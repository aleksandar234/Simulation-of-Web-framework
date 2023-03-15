import { Component, OnInit } from '@angular/core';
import { ShowMachineService } from '../../services/show-machine.service';
import { ShowMachinesParamsService } from '../../services/show-machines-params.service';
import {DestroyMachineService} from '../../services/destroy-machine.service';
import {StartMachineService} from '../../services/start-machine.service';
import {StopMachineService} from '../../services/stop-machine.service';
import {RestartMachineService} from '../../services/restart-machine.service';
import {Machine} from '../../models';
@Component({
  selector: 'app-search-machine',
  templateUrl: './search-machine.component.html',
  styleUrls: ['./search-machine.component.css']
})
export class SearchMachineComponent implements OnInit {

  machineList: any;
  searchPerName: any;
  searchPerStatus: any;
  searchPerStartDate: any;
  searchPerEndDate: any;
  searchParameters: string[];
  machineSearchList: any;

  constructor(private startMachineService: StartMachineService,private stopMachineService: StopMachineService, private restartMachineService: RestartMachineService, private showMachineService: ShowMachineService, private destroyMachineService: DestroyMachineService, private showMachinesParamsService: ShowMachinesParamsService) {
    this.searchParameters = ['Na:','St:','Sd:','Ed:'];
  }

  ngOnInit(): void {
    this.showMachineService.showMachines().subscribe((machines: any) => {
       console.log(machines);
       this.machineList = machines;
    })
  }

  destroyMachine(machine: any) {
    this.destroyMachineService.destroyMachine(machine).subscribe(machine=>{
      console.log("Destroyed");
      this.ngOnInit()
    })
  }

  startMachine(machine: any) {
    this.startMachineService.startMachine(machine).subscribe(machine=>{
      console.log("Starting...");
    })
  }

  stopMachine(machine: any) {
    this.stopMachineService.stopMachine(machine).subscribe(machine=>{
      console.log("Stopping...");
    })
  }

  restartMachine(machine: any) {
      this.restartMachineService.restartMachine(machine).subscribe(machine=>{
        console.log("Restarting...");
      })
    }


  searchMachines(searchPerName: any, searchPerStatus: any , searchPerStartDate: any, searchPerEndDate: any) {
    this.searchParameters = ['Na:','St:','Sd:','Ed:'];
    if(searchPerName !== undefined && searchPerName !== null){
      this.searchParameters[0] = ('Na:' + searchPerName);
    }
    if(searchPerStatus !== undefined && searchPerStatus !== null){
      this.searchParameters[1] = ('St:' + searchPerStatus);
    }
    if((searchPerStartDate !== undefined && searchPerStartDate !== null) && (searchPerEndDate !== undefined && searchPerEndDate !== null)){
      this.searchParameters[2] = ('Sd:' + searchPerStartDate);
      this.searchParameters[3] = ('Ed:' + searchPerEndDate);
    }
    console.log(this.searchParameters);
    this.showMachinesParamsService.searchMachinesWithParameters(this.searchParameters).subscribe(machine=>{
      console.log(machine);
      this.machineSearchList = machine;
    });
  }

}
