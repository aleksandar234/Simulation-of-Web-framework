import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ShowUsersComponent } from './show-users/show-users.component';
import { InsertUserComponent } from './insert-user/insert-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { CreateMachineComponent } from './machines/create-machine/create-machine.component';
import { SearchMachineComponent } from './machines/search-machine/search-machine.component';
import { ErrorsComponent } from './machines/errors/errors.component';
import { ReservationMachineComponent } from './machines/reservation-machine/reservation-machine.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ShowUsersComponent,
    InsertUserComponent,
    EditUserComponent,
    CreateMachineComponent,
    SearchMachineComponent,
    ErrorsComponent,
    ReservationMachineComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass:TokenInterceptorService, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
