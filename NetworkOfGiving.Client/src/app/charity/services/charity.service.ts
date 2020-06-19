import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { Charity } from 'src/models/Charity';
import { AuthService } from 'src/app/identity/services/auth.service';
import { GetCharity } from 'src/models/GetCharity';
import { ThrowStmt } from '@angular/compiler';
import { CharityList } from 'src/models/CharityList';
import { DonationService } from 'src/app/donation/donation.service';
import { Route, Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class CharityService {
    private baseCharityUrl = environment.apiUrl + 'charity';

    private options = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${this.auth.getToken()}`
      }),
    };

    constructor(private http: HttpClient, private auth: AuthService, private donationService:DonationService, private router:Router) { }
    
    upload(data): Observable<Charity> {
      return this.http.post<Charity>(this.baseCharityUrl, data, this.options);
    }

    edit(id, data): Observable<Charity> {
      return this.http.put<Charity>(this.baseCharityUrl + '/' + id, data, this.options);
    }

    getCharity(id): Observable<GetCharity> {
      return this.http.get<GetCharity>(this.baseCharityUrl + '/' +id);
    }

    deleteCharity(id){
      return this.http.delete(this.baseCharityUrl + '/' + id, this.options);
    }

    getAllCharities(): Observable<Array<CharityList>>{
      return this.http.get<Array<CharityList>>(this.baseCharityUrl);
    }

    isLogged(){
      return this.auth.isLogged();
    }
  }