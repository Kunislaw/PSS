import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';

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

  constructor(private service: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(data) {
    let response = this.service.login(data.email,data.password);
    response.subscribe((data) => {
      console.log(data);
    });
  }

}
