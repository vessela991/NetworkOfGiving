import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/identity/services/auth.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
  }

  isLogged(){
    return this.auth.isLogged();
  }
}
