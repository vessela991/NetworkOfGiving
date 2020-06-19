import { Component, OnInit,Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DonationService } from '../donation.service';
import {GetCharity} from '../../../models/GetCharity'
import { CharityService } from 'src/app/charity/services/charity.service';
import { Donation } from 'src/models/Donation';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-donate',
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
})
export class DonateComponent implements OnInit {
  donationForm: FormGroup;
  donation:Donation;
  
  private routeSub: Subscription;
  charityIdFromRoute: string;

  constructor(private route: ActivatedRoute,
              private charityService:CharityService,
              private router:Router,
              private formBuilder: FormBuilder,
              private donationService : DonationService) { 
    this.charityIdFromRoute = this.route.snapshot.paramMap.get('id');
    let id: number = +this.charityIdFromRoute;
    this.donationForm = this.formBuilder.group({
      'amount' : ['', Validators.required],
      'charityId' : [id],
    })
  }

  ngOnInit(): void {
    //const charityIdFromRoute = this.route.snapshot.paramMap.get('id');
  }

  donate(){

    this.donationService.donate(this.donationForm.value).subscribe(data=>{
      console.log(data);
    })
  }

  get getAmount(){
    return this.donationForm.get('amount');
  }
  @Input() charity:GetCharity;
}
