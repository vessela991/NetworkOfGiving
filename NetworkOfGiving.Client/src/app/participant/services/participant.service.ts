import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/identity/services/auth.service';
import { Observable } from 'rxjs';
import { Participant } from 'src/models/Participant';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {
  
  private participantPath = environment.apiUrl + 'participant';

  constructor(private http:HttpClient, private auth: AuthService) {}
   private options = {
    headers: new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    }),
  };

  participate(data):Observable<Participant>{
    return this.http.post<Participant>(this.participantPath
      ,data,this.options);
  }
}
