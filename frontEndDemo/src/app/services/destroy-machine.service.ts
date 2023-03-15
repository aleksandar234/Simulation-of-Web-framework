import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';
import {CreateMachineService} from './create-machine.service'

@Injectable({
  providedIn: 'root'
})
export class DestroyMachineService {

  private readonly destroyMachineApi = environment.destroyMachineApi;

  constructor(private httpClient: HttpClient, private createMachineService: CreateMachineService) { }

  destroyMachine(machine: any): Observable<any> {
     return this.httpClient.put<any>(this.destroyMachineApi,
     {
         name: machine.name,
         status: machine.status,
         createdBy: machine.createdBy,
         active: false
     });
  }
}
