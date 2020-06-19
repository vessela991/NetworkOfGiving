import { Injectable } from '@angular/core';

import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../identity/services/auth.service';
import { environment } from 'src/environments/environment';
import { CharityGetComponent } from '../charity/charity-get/charity-get.component';
import { Observable } from 'rxjs';
import { Donation } from 'src/models/Donation';

@Injectable({
  providedIn: 'root'
})
export class DonationService {

  private donationPath = environment.apiUrl + 'donation';

  constructor(private http:HttpClient, private auth: AuthService) {}
   private options = {
    headers: new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    }),
  };
  
  donate(data): Observable<Donation>{
    return this.http.post<Donation>(this.donationPath,data,this.options);
  }

  get amount(){
    return this.amount;
  }
}
