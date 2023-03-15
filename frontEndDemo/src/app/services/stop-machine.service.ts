import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StopMachineService {

  private readonly stopMachineApi = environment.stopMachineApi;

  constructor(private httpClient: HttpClient) { }

  stopMachine(machine: any): Observable<any> {
    return this.httpClient.post<any>(this.stopMachineApi,
    {
        name: machine.name,
        status: machine.status,
        createdBy: machine.createdBy,
        active: machine.active,
        angularDate: localStorage.getItem("jwt")
    });
  }
}
