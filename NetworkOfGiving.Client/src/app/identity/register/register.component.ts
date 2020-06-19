import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  constructor(private formFuilder:FormBuilder, private authService:AuthService, private router:Router) {
    this.registerForm = this.formFuilder.group({
      'username': ['', [Validators.required, Validators.minLength(6), Validators.maxLength(30)]],
      'password': ['', [Validators.required, Validators.minLength(6), Validators.maxLength(30)]],
      'name': ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      'age': ['', [Validators.required, Validators.min(18), Validators.max(100)]],
      'gender': [''],
      'location': ['']
    })
   }

  ngOnInit(): void {
  }

  register(){
    
    if(this.gender.value===""){
        this.gender.setValue("NULL");
    }
    this.authService.register(this.registerForm.value).subscribe(data=>{
      console.log(data);
      this.navigate();
    });
  }

  navigate(){
    return this.router.navigate[('/charities')];
  }

  get username(){
    return this.registerForm.get('username');
  }
  get password(){
    return this.registerForm.get('password');
  }
  get name(){
    return this.registerForm.get('name');
  }
  get age(){
    return this.registerForm.get('age');
  }
  get gender(){
    return this.registerForm.get('gender');
  }
  get location(){
    return this.registerForm.get('location');
  }
}
