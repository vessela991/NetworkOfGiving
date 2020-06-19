import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './identity/login/login.component';
import { RegisterComponent } from './identity/register/register.component';
import { CharityCreateComponent } from './charity/charity-create/charity-create.component';
import { UserGetComponent } from './user/user-get/user-get.component';
import { CharityGetComponent } from './charity/charity-get/charity-get.component';
import { CharityListComponent } from './charity/charity-list/charity-list.component';
import { CharityEditComponent } from './charity/charity-edit/charity-edit.component';
import { LogoutComponent } from './identity/logout/logout.component';
import { UserHomeComponent } from './user/user-home/user-home.component';
import { DonateComponent } from './donation/donate/donate.component';
import { IndexPageNotLoggedComponent } from './index-page-not-logged/index-page-not-logged.component';
import { UserActivityComponent } from './user/user-activity/user-activity.component';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'create', component: CharityCreateComponent},
  {path: 'profile', component: UserGetComponent},
  {path: 'charity/:id', component: CharityGetComponent},
  {path: 'charities', component: CharityListComponent},
  {path: 'charity/edit/:id', component: CharityEditComponent},
  {path: 'home', component: UserHomeComponent},
  {path: 'charity/:id/donate', component: DonateComponent},
  {path: 'profile/activity', component: UserActivityComponent},
  {path: '', component: IndexPageNotLoggedComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
