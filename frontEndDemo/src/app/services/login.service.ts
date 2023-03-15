import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Users, UserLogin} from '../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

   private readonly loginApi = environment.loginApi;
   private username: string;
   private password: string;
   private jwtToken: string;


   constructor(private httpClient: HttpClient) {
      this.username = '';
      this.password = '';
      this.jwtToken = '';
   }

   loginUser(user: UserLogin): Observable<any> {
      console.log(this.username);
      console.log(this.password);
      return this.httpClient.post<any>(this.loginApi,
      {
        username: user.username,
        password: user.password
      });
   }

   setUsername(username: string): void {
      this.username = username;
   }

   setPassword(password: string): void {
      this.password = password;
   }

   setToken(jwtToken: string): void {
      this.jwtToken = jwtToken;
   }

   getToken(): string {
      return this.jwtToken;
   }

   getUsername(): string {
      return this.username;
   }

   getPassword(): string {
      return this.password;
   }

}
