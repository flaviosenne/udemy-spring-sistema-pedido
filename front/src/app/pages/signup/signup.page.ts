import { NavController } from '@ionic/angular';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {
 formGroup: FormGroup
 
  constructor(public navCtrl: NavController,
    private formBuilder: FormBuilder) { 
      this.formGroup = this.formBuilder.group({
        name: ['Joao',[Validators.required, Validators.minLength(5),Validators.maxLength(120)]],
        email: ['joao@email.com',[Validators.required, Validators.email]],
        password: ['123',[Validators.required, Validators.minLength(5),Validators.maxLength(120)]],
        type: ['1',[Validators.required]],
        cpfOrCnpj: ['111.222.333-44',[Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
        phone1: ['12345678910',[Validators.required, Validators.maxLength(11)]],
        phone2: ['',[]],
        place: ['rua ameda',[Validators.required]],
        number: ['1234',[Validators.required]],
        district: ['Aviação',[Validators.required]],
        complement: ['',[]],
        postalCode: ['1111222',[Validators.required]],
        state: ['1',[Validators.required]],
        city: ['1',[Validators.required]],
      })
    }

  ngOnInit() {
  }

  register(){
    console.log("register")
  }

}
