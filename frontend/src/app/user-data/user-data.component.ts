import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { DataSharingService } from '../datasharing.service';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService, private router:Router, private dataSharingService: DataSharingService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    if(localStorage.getItem("isLogged") === "true"){
      this.userService.getAllUsers().subscribe(users => {
        this.users = users
        this.dataSharingService.isUserLoggedIn.next(true);
      });
    } else {
      this.router.navigate(["login"])
      this.dataSharingService.isUserLoggedIn.next(false);
    }

  }
}
