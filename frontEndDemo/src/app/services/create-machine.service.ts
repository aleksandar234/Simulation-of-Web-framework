import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateMachineService {

   private readonly createMachineApi = environment.createMachineApi;
   private name: string;
   private status: string;
   private createdBy: string;
   private active: boolean;

  constructor(private httpClient: HttpClient) {
     this.name = '';
     this.status = 'STOPPED';
     this.createdBy = '';
     this.active = true;
  }

  createMachine(machine: Machine): Observable<any> {
     return this.httpClient.post<any>(this.createMachineApi,
     {
       name: machine.name,
       status: this.status,
       createdBy: localStorage.getItem('loggedUser'),
       active: this.active
     });
  }

  getActive(): boolean {
    return this.active;
  }

  setActive(active: boolean): void {
    this.active = active;
  }

  setUsername(createdBy: string): void {
     this.createdBy = createdBy;
  }

  getUsername(): string {
    return this.createdBy;
  }

  setStatus(status: string): void {
    this.status = status;
  }

  getStatus(): string {
    return this.status;
  }

  setName(name: string): void {
      this.name = name;
   }

   getName(): string {
      return this.name;
   }


}
