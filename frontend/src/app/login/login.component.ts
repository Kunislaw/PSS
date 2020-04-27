import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { DataSharingService } from '../datasharing.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private service: UserService, private router:Router, private dataSharingService: DataSharingService) { }

  ngOnInit(): void {
  }

  onSubmit(data) {
    let response = this.service.login(data.email,data.password);
    response.subscribe((d) => {
      localStorage.setItem("isLogged", "true");
      localStorage.setItem("credentials", btoa(data.email+":"+data.password));
      localStorage.setItem("userId", d.toString());
      this.router.navigate([""]);
      this.dataSharingService.isUserLoggedIn.next(true);
    });
  }

}
