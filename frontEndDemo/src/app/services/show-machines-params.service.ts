import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Machine} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowMachinesParamsService {

  private readonly searchParametersApi = environment.searchParametersApi;

  constructor(private httpClient: HttpClient) {
  }

  searchMachinesWithParameters(searchParameters: string[]): Observable<Machine> {
    console.log(this.searchParametersApi +`/name=${searchParameters[0]}/status=${searchParameters[1]}/start=${searchParameters[2]}/end=${searchParameters[3]}`);
    return this.httpClient.get<Machine>(this.searchParametersApi +`/name=${searchParameters[0]}/status=${searchParameters[1]}/start=${searchParameters[2]}/end=${searchParameters[3]}`);
  }


}
