import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CharityService } from '../services/charity.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-charity-create',
  templateUrl: './charity-create.component.html',
  styleUrls: ['./charity-create.component.css']
})
export class CharityCreateComponent implements OnInit {
  charityForm: FormGroup;
  image: File;

  constructor(private router:Router,private formBuilder: FormBuilder, private charityService : CharityService) { 
    this.charityForm = this.formBuilder.group({
      'name' : ['', Validators.required],
      'thumbnail' : ['', Validators.required],
      'description' : ['', Validators.required],
      'budgetRequired' : [''],
      'numberOfParticipants' : ['']
    })
  }

  ngOnInit(): void {
  }

  public onFileChanged(event) {
    this.image = event.target.files[0];
  }

  upload() {
    const formData = new FormData();
    formData.append('thumbnail', this.image)
    formData.append('name', this.charityForm.value.name);
    formData.append('description', this.charityForm.value.description);
    formData.append('numberOfParticipants', this.charityForm.value.numberOfParticipants);
    formData.append('budgetRequired', this.charityForm.value.budgetRequired);

    this.charityService.upload(formData).subscribe(data => {
        console.log(data)
        this.router.navigate(['/charity/'+data.id]);
    });
  }

  get name(){
    return this.charityForm.get('name');
  }

  get thumbnail(){
    return this.charityForm.get('thumbnail');
  }

  get description(){
    return this.charityForm.get('description');
  }

  get numberOfParticipants(){
    return this.charityForm.get('numberOfParticipants');
  }

  get budgetRequired(){
    return this.charityForm.get('budgetRequired');
  }
}
