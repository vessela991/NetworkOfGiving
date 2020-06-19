import { Component, OnInit } from '@angular/core';
import { AuthService } from '../identity/services/auth.service';

@Component({
  selector: 'app-index-page-not-logged',
  templateUrl: './index-page-not-logged.component.html',
  styleUrls: ['./index-page-not-logged.component.css']
})
export class IndexPageNotLoggedComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
  }

  isLogged(){
    return this.auth.isLogged();
  }
}
