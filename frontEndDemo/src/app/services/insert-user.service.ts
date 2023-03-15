import { Injectable } from '@angular/core';
import { UserInsert, Permission } from '../models';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InsertUserService {

   private readonly insertApi = environment.insertApi;


   username: string;
   password: string;
   firstName: string;
   lastName: string;
   permissions: [Permission];
   create: boolean;
   read: boolean;
   update: boolean;
   delete: boolean;
   includePermissions: string[]
   clean: boolean;

   searchMachine: boolean;
   startMachine: boolean;
   stopMachine: boolean;
   restartMachine: boolean;
   createMachine: boolean;
   destroyMachine: boolean;

   constructor(private httpClient: HttpClient) {
      this.username = '';
      this.password = '';
      this.firstName = '';
      this.lastName = '';
      this.permissions = [{
        permission: ''
      }];
      this.create = false;
      this.read = false;
      this.update = false;
      this.delete = false;
      this.includePermissions = [];
      this.clean = false;


      this.searchMachine = false;
      this.startMachine = false;
      this.stopMachine = false;
      this.restartMachine = false;
      this.createMachine = false;
      this.destroyMachine = false;
   }

   insertUser(user: UserInsert): Observable<any> {
     return this.httpClient.post<any>(this.insertApi,
     {
        firstName: user.firstName,
        lastName: user.lastName,
        username: user.username,
        password: user.password,
        permissions: user.permissions
     });
   }

   setCreate(crt: boolean): void {
      if(crt) {
        this.includePermissions.push("CAN_CREATE_USER");
      }
   }

   setRead(rd: boolean): void {
       if(rd) {
         this.includePermissions.push("CAN_READ_USER");
       }
    }

    setUpdate(upd: boolean): void {
       if(upd) {
         this.includePermissions.push("CAN_UPDATE_USER");
       }
    }

    setDelete(dlt: boolean): void {
       if(dlt) {
         this.includePermissions.push("CAN_DELETE_USER");
       }
    }

    getCreate(): boolean {
      return this.create;
    }

    getUpdate(): boolean {
      return this.update;
    }

    getRead(): boolean {
      return this.read;
    }

    getDelete(): boolean {
      return this.delete;
    }

   setUsername(username: string): void {
       this.username = username;
    }

    setPassword(password: string): void {
       this.password = password;
    }

    getUsername(): string {
       return this.username;
    }

    getPassword(): string {
       return this.password;
    }

    getFirstName(): string {
       return this.firstName;
    }

    getSearchMachine(): boolean {
      return this.searchMachine;
    }

    setSearchMachine(src: boolean): void {
       if(src) {
         this.includePermissions.push("CAN_SEARCH_MACHINES");
       }
    }

    getStartMachine(): boolean {
      return this.startMachine;
    }

    setStartMachine(str: boolean): void {
       if(str) {
         this.includePermissions.push("CAN_START_MACHINES");
       }
    }
    getStopMachine(): boolean {
      return this.stopMachine;
    }
    setStopMachine(stop: boolean): void {
       if(stop) {
         this.includePermissions.push("CAN_STOP_MACHINES");
       }
    }
    getRestartMachine(): boolean {
      return this.restartMachine;
    }
    setRestartMachine(rst: boolean): void {
       if(rst) {
         this.includePermissions.push("CAN_RESTART_MACHINES");
       }
    }

    getCreateMachine(): boolean {
      return this.createMachine;
    }
    setCreateMachine(crt: boolean): void {
       if(crt) {
         this.includePermissions.push("CAN_CREATE_MACHINES");
       }
    }

    getDestroyMachine(): boolean {
      return this.destroyMachine;
    }
    setDestroyMachine(dst: boolean): void {
       if(dst) {
         this.includePermissions.push("CAN_DESTROY_MACHINES");
       }
    }

    getLastName(): string {
       return this.lastName;
    }

    getPermissions(): [Permission] {
       return this.permissions;
    }

    setFirstName(firstName: string): void {
       this.firstName = firstName;
    }

    setLastName(lastName: string): void {
       this.lastName = lastName;
    }

    setPermissions(permissions: [Permission]): any {
       this.permissions = permissions;
    }

    getIncludedPermissions(): string[] {
      return this.includePermissions;
    }

    setIncludedPermissions(items: string[]): void {
      this.includePermissions = items;
    }

    setCleanField(cleanF: boolean): void {
      this.clean = cleanF;
    }

}
