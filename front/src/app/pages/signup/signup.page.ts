import { HttpClient } from '@angular/common/http';
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
 states: any = []
 cities: any = []
 
  constructor(public navCtrl: NavController,
    private formBuilder: FormBuilder,
    private http: HttpClient) { 

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
        state: [null,[Validators.required]],
        city: [null,[Validators.required]],
      })
    }

  ngOnInit() {
    this.http.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados").subscribe(res => {
      this.states = res
      this.formGroup.controls.state.setValue(this.states[0].id)
      this.updateCities()
    })
  }

  updateCities(){
    let state_id = this.formGroup.value.state
    this.http.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${state_id}/municipios`).subscribe(res => {
      this.cities = res
      this.formGroup.controls.city.setValue(null)
    })
    
  }
  register(){
    
    console.log("register")
  }

}
