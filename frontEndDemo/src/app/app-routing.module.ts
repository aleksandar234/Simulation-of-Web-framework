import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginComponent} from './login/login.component';
import {InsertUserComponent} from './insert-user/insert-user.component';
import {EditUserComponent} from './edit-user/edit-user.component';
import {ShowUsersComponent} from './show-users/show-users.component';
import {CreateMachineComponent} from './machines/create-machine/create-machine.component';
import {SearchMachineComponent} from './machines/search-machine/search-machine.component';
import {ErrorsComponent} from './machines/errors/errors.component';
import {ReservationMachineComponent} from './machines/reservation-machine/reservation-machine.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "insert",
    component: InsertUserComponent
  },
  {
    path: "edit",
    component: EditUserComponent
  },
  {
    path: "allUsers",
    component: ShowUsersComponent
  },
  {
    path: "createMachine",
    component: CreateMachineComponent
  },
  {
    path: "searchMachine",
    component: SearchMachineComponent
  },
  {
    path: "reservationMachine",
    component: ReservationMachineComponent
  },
  {
    path: "errors",
    component: ErrorsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
