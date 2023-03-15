import { Component, OnInit } from '@angular/core';
import {ReservationMachineService} from '../../services/reservation-machine.service';
import {Machine, ErrorMassage} from '../../models';

@Component({
  selector: 'app-errors',
  templateUrl: './errors.component.html',
  styleUrls: ['./errors.component.css']
})
export class ErrorsComponent implements OnInit {

  errorList: any[];

  constructor(private reservationMachineService: ReservationMachineService) {
    this.errorList = [];
  }

  ngOnInit(): void {
    this.reservationMachineService.errorMassage(this.errorList).subscribe((machines: any) => {
       console.log(machines);
       this.errorList = machines;
    })
  }

}
