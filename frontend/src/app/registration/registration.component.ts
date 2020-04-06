import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    companyAddress: new FormControl(''),
    companyName: new FormControl(''),
    companyNip: new FormControl(''),
    name: new FormControl(''),
    lastName: new FormControl(''),
  });

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(data) {
    this.userService.registerUser(data).subscribe(data => console.log(data));
  }

}
