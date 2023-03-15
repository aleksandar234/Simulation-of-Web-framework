import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationMachineService {

  private readonly reservationStartMachineApi = environment.reservationStartMachineApi;
  private readonly reservationStopMachineApi = environment.reservationStopMachineApi;
  private readonly reservationRestartMachineApi = environment.reservationRestartMachineApi;
  private readonly errorMassageApi = environment.errorMassageApi;

  constructor(private httpClient: HttpClient) { }

  reservationStartMachine(machine: any, date: any): Observable<any> {
    return this.httpClient.post<any>(this.reservationStartMachineApi,
    {
        name: machine.name,
        status: machine.status,
        createdBy: machine.createdBy,
        active: machine.active,
        angularDate: localStorage.getItem("jwt") + " " + date
    });
  }


  reservationStopMachine(machine: any, date: any): Observable<any> {
    return this.httpClient.post<any>(this.reservationStopMachineApi,
    {
        name: machine.name,
        status: machine.status,
        createdBy: machine.createdBy,
        active: machine.active,
        angularDate: localStorage.getItem("jwt") + " " + date
    });
  }

  reservationRestartMachine(machine: any, date: any): Observable<any> {
    return this.httpClient.post<any>(this.reservationRestartMachineApi,
    {
        name: machine.name,
        status: machine.status,
        createdBy: machine.createdBy,
        active: machine.active,
        angularDate: localStorage.getItem("jwt") + " " + date
    });
  }

  errorMassage(machine: any[]): Observable<any> {
    return this.httpClient.get<any>(`${this.errorMassageApi}`);
  }


}
