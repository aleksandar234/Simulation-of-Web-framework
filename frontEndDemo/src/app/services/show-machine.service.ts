import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowMachineService {

  private readonly showMachinesApi = environment.showMachinesApi;

  constructor(private httpClient: HttpClient) {
  }

  showMachines(): Observable<Machine> {
    return this.httpClient.get<Machine>(`${this.showMachinesApi}`);
  }


}
