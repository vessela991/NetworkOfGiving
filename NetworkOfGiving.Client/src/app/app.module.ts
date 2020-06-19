import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './identity/login/login.component';
import { AuthService } from './identity/services/auth.service';
import { RegisterComponent } from './identity/register/register.component';
import { CharityComponent } from './charity/charity/charity.component';
import { CharityEditComponent } from './charity/charity-edit/charity-edit.component';
import { CharityService } from './charity/services/charity.service';
import { CharityCreateComponent } from './charity/charity-create/charity-create.component';
import { CharityGetComponent } from './charity/charity-get/charity-get.component';
import { UserGetComponent } from './user/user-get/user-get.component';
import { UserService } from './user/services/user.service';
import { CharityDeleteComponent } from './charity/charity-delete/charity-delete.component';
import { CharityListComponent } from './charity/charity-list/charity-list.component';
import { DonateComponent } from './donation/donate/donate.component';
import { DonationService } from './donation/donation.service';
import { LogoutComponent } from './identity/logout/logout.component';
import { UserHomeComponent } from './user/user-home/user-home.component';
import { IndexPageNotLoggedComponent } from './index-page-not-logged/index-page-not-logged.component';
import { UserActivityComponent } from './user/user-activity/user-activity.component';
import { ParticipantComponent } from './participant/participant.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    CharityComponent,
    CharityEditComponent,
    CharityCreateComponent,
    CharityGetComponent,
    UserGetComponent,
    CharityDeleteComponent,
    CharityListComponent,
    DonateComponent,
    LogoutComponent,
    UserHomeComponent,
    IndexPageNotLoggedComponent,
    UserActivityComponent,
    ParticipantComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    CharityService,
    UserService,
    DonationService,
    CharityEditComponent
    // ,
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: TokenInterceptorService,
    //   multi: true
    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
