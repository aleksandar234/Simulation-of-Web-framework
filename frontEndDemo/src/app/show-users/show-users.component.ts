import { Component, OnInit } from '@angular/core';
import {DeleteService} from '../services/delete.service';
import { GetUsersService } from '../services/get-users.service';
import {Users} from '../models';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {


   usersList: any;
   logged = localStorage.getItem('loggedUser');
   // u ngOnInit-u cu da proverim pre samog loadovanje strane da uzemem usera iz localStoraga
   // treba onda da posaljem zahtev backu za njegove permisije
   // prodjem kroz njegove permisije i pitam da li se permisija za read nalazi tu onda enableujem dugme
   // ako ne onda ga deaktiviram
   // isto kao u submitTextArea uzmem sve usere sacuvam ih negde, prolazim kroz njih nalazim username
   // koji se poklapa sa mojim
   // prodjem kroz permisije njegove i pitam da li moj user ima permisiju read
   // ako ima dacu mu da klikne dugme submit
   // ako nema necu da mu dam
   // ako ima u tom slucaju pitam da li ima deletePermisiju
   // ako ima enablujem delete
   // ako nema disablujem delete

   constructor(private getUsersService: GetUsersService, private deleteService: DeleteService){
   }

   ngOnInit(): void {
   }


    submitTextArea() {
       this.getUsersService.getUsers().subscribe((users: any) => {
          console.log(users);
          this.usersList = users;
       })
    }

    deleteUser(user: any) {
        this.deleteService.deleteUser(user).subscribe(user=>{
          this.submitTextArea()
        })
    }


}
