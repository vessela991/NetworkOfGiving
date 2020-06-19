import { Component, OnInit } from '@angular/core';
import { Charity } from 'src/models/Charity';
import { CharityService } from '../services/charity.service';
import { HttpClient } from '@angular/common/http';
import { GetCharity } from 'src/models/GetCharity';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { DonationService } from 'src/app/donation/donation.service';
import { CharityEditComponent } from '../charity-edit/charity-edit.component';
import { AuthService } from 'src/app/identity/services/auth.service';

@Component({
  selector: 'app-charity-get',
  templateUrl: './charity-get.component.html',
  styleUrls: ['./charity-get.component.css']
})
export class CharityGetComponent implements OnInit {
  id: number;
  charity: GetCharity;
  imagePath: any;
  static getImagePath: any;
  isCreator: boolean;

  constructor(private auth:AuthService,private charityEditComponent:CharityEditComponent,private route: ActivatedRoute,private router:Router, private charityService:CharityService, private sanitizer: DomSanitizer) {
    this.route.params.subscribe(res => {
      this.id = res['id'];
      this.charityService.getCharity(this.id).subscribe(data => {
        this.charity = data;
        this.imagePath = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + this.charity.thumbnail);
      })
    })
  }

  ngOnInit(): void {
    this.isCreatorCheck();
  }

  
  deleteCharity(id) {
    return this.charityService.deleteCharity(id).subscribe(data=>{
      console.log(data);
      this.router.navigate(['/charities']);
    })
  }
  
  isLogged(){
    return this.auth.isLogged();
  }

  isCreatorCheck(){
    this.auth.getUser().subscribe(data=>{
      if(data.username == this.charity.username) this.isCreator=true
      
    })
    this.isCreator=false
  }

  donate(){
    return this.router.navigate(['/charity/'+ this.id +'/donate']);  }

  edit(id){
    return this.router.navigate(['/charity/edit/'+id]);
  }

  getImagePath(){
    return this.imagePath;
  }
}
