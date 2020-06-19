import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService) 
  {
    this.loginForm = this.formBuilder.group({
      'username': ['', [Validators.required, Validators.minLength(2), Validators.maxLength(6)]],
      'password': ['', [Validators.required]]
    })
    console.log(this.loginForm);
   }

  ngOnInit(): void {
  }

  navigate(){
    return this.router.navigate[('/home')]
  }

  login() {
    this.authService.login(this.loginForm.value).subscribe(data => {
      localStorage.setItem('token', data.jwt);
    });
    return this.router.navigate(['/home']);
  }

  get username() {
    return this.loginForm.get('username');
  }

  
  get password() {
    return this.loginForm.get('password');
  }
}
