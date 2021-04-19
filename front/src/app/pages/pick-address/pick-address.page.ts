import { ClientService } from './../../../services/domain/client.service';
import { NavController } from '@ionic/angular';
import { AuthService } from './../../../services/auth.service';
import { AddressDTO } from './../../../models/address.dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pick-address',
  templateUrl: './pick-address.page.html',
  styleUrls: ['./pick-address.page.scss'],
})
export class PickAddressPage implements OnInit {

  addresses: AddressDTO[]

  constructor(public auth: AuthService,
    public navCtrl: NavController,
    public clientService: ClientService) { }

  ngOnInit() {
    if(this.auth.storage.getLocalUser){
      this.clientService.findByEmail(this.auth.storage.getLocalUser().email)
      .subscribe(res => {
        this.addresses = res['adresses']
      }, err => {
        if(err.status == 403){
          this.navCtrl.navigateBack('/')
        }
      })
    }else{
      this.navCtrl.navigateBack('/')
    }
  }
}
