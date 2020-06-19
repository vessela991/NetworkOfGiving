import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CharityService } from '../services/charity.service';
import { Charity } from 'src/models/Charity';
import { GetCharity } from 'src/models/GetCharity';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-charity-edit',
  templateUrl: './charity-edit.component.html',
  styleUrls: ['./charity-edit.component.css']
})
export class CharityEditComponent implements OnInit {
  charity:Charity;
  charityEditForm: FormGroup;
  image: File;
  private routeId: any;
  img: any;

  constructor(private route: ActivatedRoute,  private sanitizer: DomSanitizer, private router:Router,private formBuilder: FormBuilder, private charityService : CharityService) { 

    this.charity
    this.route.params.subscribe(res => {
      this.routeId = res['id'];
      })

    this.charityEditForm = this.formBuilder.group({
      'name' : [''],
      'thumbnail' : ['' ],
      'description' : [''],
      'budgetRequired' : [''],
      'numberOfParticipants' : ['']
    })
  }

  ngOnInit(): void {
    this.charityService.getCharity(this.routeId).subscribe(data=>{
      this.charityEditForm.patchValue({
        name: data.name,
        description: data.description,
        budgetRequired: data.budgetRequired,
        numberOfParticipants: data.maxNumberOfParticipants
      })
      this.img = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + data.thumbnail);
    })
  }

  public onFileChanged(event) {
    this.image = event.target.files[0];
  }

  edit() {
    const formData = new FormData();
    formData.append('thumbnail', this.image)
    formData.append('name', this.charityEditForm.value.name);
    formData.append('description', this.charityEditForm.value.description);
    formData.append('numberOfParticipants', this.charityEditForm.value.numberOfParticipants);
    formData.append('budgetRequired', this.charityEditForm.value.budgetRequired);

    this.charityService.edit(this.routeId, formData).subscribe(data => {
        console.log(data)
        this.router.navigate(['/charity/'+data.id]);
    });
  }

  get id(){
    return this.charity.id;
  }

  get name(){
    return this.charityEditForm.get('name');
  }

  get thumbnail(){
    return this.charityEditForm.get('thumbnail');
  }

  get description(){
    return this.charityEditForm.get('description');
  }

  get numberOfParticipants(){
    return this.charityEditForm.get('numberOfParticipants');
  }

  get budgetRequired(){
    return this.charityEditForm.get('budgetRequired');
  }

}
