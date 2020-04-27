import { Component, OnInit } from '@angular/core';
import { DataSharingService } from '../datasharing.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isUserLoggedIn: boolean;
  constructor(private dataSharingService: DataSharingService) {
    this.dataSharingService.isUserLoggedIn.subscribe(value => {
      this.isUserLoggedIn = value;
    });
  }

  ngOnInit(): void {
  }

}
