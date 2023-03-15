import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Users, UserLogin} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteService {

  private readonly deleteApi = environment.deleteApi;

  constructor(private httpClient: HttpClient) { }

  deleteUser(user: any): Observable<any> {
      console.log(user.id)
      console.log(user.username);
      return this.httpClient.delete<number>(this.deleteApi+"/"+user.id);
   }

}
