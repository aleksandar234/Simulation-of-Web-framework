import { Component, OnInit } from '@angular/core';
import {Machine} from '../../models';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateMachineService} from '../../services/create-machine.service';

@Component({
  selector: 'app-create-machine',
  templateUrl: './create-machine.component.html',
  styleUrls: ['./create-machine.component.css']
})
export class CreateMachineComponent implements OnInit {

  name: string;
  status: string;
  createdBy: string;
  active: boolean;
  angularDate: string;

  constructor(private createMachineService: CreateMachineService) {
     this.name = createMachineService.getName();
     this.status = createMachineService.getStatus();
     this.createdBy = createMachineService.getUsername();
     this.active = createMachineService.getActive();
     this.angularDate = '';
  }

  ngOnInit(): void {
  }

  createMachine() {
    this.createMachineService.setName(this.name);
    let machine: Machine = {
        name: this.name,
        status: this.status,
        createdBy: this.createdBy,
        active: this.active,
        angularDate: this.angularDate
    };
    this.createMachineService.createMachine(machine).subscribe(
        (res: any) => {
           console.log("All went good");
        }
    );

  }

}
