import { ClientDTO } from './../../../models/client.dto';
import { ClientService } from './../../../services/domain/client.service';
import { HttpClient } from '@angular/common/http';
import { NavController, AlertController } from '@ionic/angular';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {
 formGroup: FormGroup
 client: ClientDTO
 states: any = []
 cities: any = []
 
  constructor(public navCtrl: NavController,
    private formBuilder: FormBuilder,
    public http: HttpClient,
    public clientService: ClientService,
    public alertController: AlertController) { 

      this.formGroup = this.formBuilder.group({
        name: ['',[Validators.required, Validators.minLength(5),Validators.maxLength(120)]],
        email: ['',[Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(5),Validators.maxLength(120)]],
        cpfOrCnpj: ['',[Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
        phone1: ['',[Validators.required,Validators.minLength(11), Validators.maxLength(11)]],
        phone2: ['',[]],
        place: ['',[Validators.required]],
        number: ['',[Validators.required]],
        district: ['',[Validators.required]],
        complement: ['',[]],
        postalCode: ['',[Validators.required]],
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
    try{

      this.http.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${state_id}/municipios`).subscribe(res => {
        this.cities = res
        this.formGroup.controls.city.setValue(null)
      })
    }catch(err){
      let show = this.alertController.create({
        header:'cep não encontrado',
        message:'cep informado não encontrado',
        backdropDismiss:false,
        buttons: [{text: 'não encontrado'}]
      })
  
      show.then(res => {
        res.present()
      })
    }
    
  }

  changeCityWithCEP(cityParams: string){
    try{

      let state_id = this.formGroup.value.state
      this.http.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${state_id}/municipios`).subscribe(res => {
        this.cities = res
        this.formGroup.controls['city'].setValue(this.cities.filter(city => city.nome == cityParams)[0].id)
        
      })
    }
    catch(err){
      let show = this.alertController.create({
        header:'cep não encontrado',
        message:'cep informado não encontrado',
        backdropDismiss:false,
        buttons: [{text: 'não encontrado'}]
      })
  
      show.then(res => {
        res.present()
      })
    }
    
  }
  
  loadAdresWithCEPInserted(){
    const postalCode = this.formGroup.value['postalCode']
    this.http.get(`https://viacep.com.br/ws/${postalCode}/json`).subscribe(res => {
      
      this.formGroup.controls['place'].setValue(res['logradouro'])
      this.formGroup.controls['district'].setValue(res['bairro'])
      this.formGroup.controls['complement'].setValue(res['complement'])
      this.formGroup.controls['state'].setValue(this.states.filter(state => state.sigla == res['uf'])[0].id)
      this.changeCityWithCEP(res['localidade'])
    })
    
  }
  register(){
    this.client = this.formGroup.value
    this.clientService.save(this.client).subscribe(res => {
      this.showInsertOk()
    }, error => {})
  }

  showInsertOk(){
    let show = this.alertController.create({
      header:'sucesso',
      message:'cadastro efetuado com sucesso',
      backdropDismiss:false,
      buttons: [{text: 'ok', handler: ()=>{
        this.navCtrl.back();
      }}]
    })

    show.then(res => {
      res.present()
    })
  }
}
