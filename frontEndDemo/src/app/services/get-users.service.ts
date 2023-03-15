import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Users} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetUsersService {

  private readonly getApi = environment.getApi;

  constructor(private httpClient: HttpClient) {
  }

   getUsers(): Observable<Users> {
     return this.httpClient.get<Users>(`${this.getApi}`);
   }
}
