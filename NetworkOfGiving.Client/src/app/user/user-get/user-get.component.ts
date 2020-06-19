import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from 'src/models/User';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserGet } from 'src/models/UserGet';

@Component({
  selector: 'app-user-get',
  templateUrl: './user-get.component.html',
  styleUrls: ['./user-get.component.css']
})
export class UserGetComponent implements OnInit {
  user: UserGet;
  constructor(private userService:UserService, private http:HttpClient) { }

  ngOnInit(): void {
    this.getUser();
  }
  getUser(){
    this.userService.getUser().subscribe(data=>{
      this.user = data;
    })
  }
}
