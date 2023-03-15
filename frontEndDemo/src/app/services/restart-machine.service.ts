import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestartMachineService {

  private readonly restartMachineApi = environment.restartMachineApi;

  constructor(private httpClient: HttpClient) { }

  restartMachine(machine: any): Observable<any> {
    return this.httpClient.post<any>(this.restartMachineApi,
    {
        name: machine.name,
        status: machine.status,
        createdBy: machine.createdBy,
        active: machine.active,
        angularDate: localStorage.getItem("jwt")
    });
  }


}
