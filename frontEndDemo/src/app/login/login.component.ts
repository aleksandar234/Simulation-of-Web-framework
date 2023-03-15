import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';
import {UserLogin} from '../models';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


   username: string;
   password: string;
   jwtToken: any;

   constructor(private loginService: LoginService) {
      this.username = loginService.getUsername();
      this.password = loginService.getPassword();
      this.jwtToken = loginService.getToken();
   }

  ngOnInit(): void {
  }

  logInUser() {
    this.loginService.setUsername(this.username);
    this.loginService.setPassword(this.password);
    let user: UserLogin = {
        username: this.username,
        password: this.password
    };
    this.loginService.loginUser(user).subscribe(
        (jwt: any) => {
          console.log(jwt)
          this.jwtToken = this.splitFunction((JSON.stringify(jwt)).toString());
          // console.log(this.jwtToken[0]);
          localStorage.setItem('jwt', this.jwtToken);
          localStorage.setItem('loggedUser', this.username);
        }
    );
  }

  splitFunction(text: string) {
    let s = text.split(":")[1];
    let jwt = s.slice(0, -1);
    let jwtToken = jwt.slice(1, -1);
    return jwtToken;
  }

}
