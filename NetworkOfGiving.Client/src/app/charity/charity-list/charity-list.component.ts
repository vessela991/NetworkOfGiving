import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CharityService } from '../services/charity.service';
import { GetCharity } from 'src/models/GetCharity';
import { CharityGetComponent } from '../charity-get/charity-get.component';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { createHostListener } from '@angular/compiler/src/core';
import { CharityList } from 'src/models/CharityList';
import { AuthService } from 'src/app/identity/services/auth.service';

@Component({
  selector: 'app-charity-list',
  templateUrl: './charity-list.component.html',
  styleUrls: ['./charity-list.component.css']
})
export class CharityListComponent implements OnInit {
    charities: Array<CharityList>;
    //imagePath: Array<any>
  //i:number=0;

  constructor(private router:Router, private charityService:CharityService, private sanitizer: DomSanitizer,private auth:AuthService) {
    this.getAllCharities()
   }
  ngOnInit(): void {
  }

  getAllCharities(){
    this.charityService.getAllCharities().subscribe(data=>{
        this.charities = data;
        this.applyImage()
    })
  }

  navigate(id){
    console.log(id)
      return this.router.navigate(['/charity/'+id]);
  }
  applyImage() {
    this.charities.forEach(charity => {
      charity.thumbnail = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + charity.thumbnail);
    });
  }
  isLogged(){
    return this.auth.isLogged();
  }
}
