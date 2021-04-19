import { CartService } from './../../../services/domain/cart.service';
import { RequestDTO } from './../../../models/request.dto';
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

  request: RequestDTO

  constructor(public auth: AuthService,
    public navCtrl: NavController,
    public clientService: ClientService,
    public cartService: CartService) { }

  ngOnInit() {
    if(this.auth.storage.getLocalUser){
      this.clientService.findByEmail(this.auth.storage.getLocalUser().email)
      .subscribe(res => {
        this.addresses = res['adresses']
        
        let cart = this.cartService.getCart()
        
        this.request = {
          client: { id: res['id']},
          deliveryAdress: null,
          payment: null,
          itens: cart.items.map(
            iten => {
              return {quantity: iten.quantity, product: {id: iten.product.id}}})
          
        }
      }, err => {
        if(err.status == 403){
          this.navCtrl.navigateBack('/')
        }
      })
    }else{
      this.navCtrl.navigateBack('/')
    }
  }

  nextPage(address: AddressDTO){
    this.request.deliveryAdress = {id: address.id}
    window.localStorage.setItem('request', JSON.stringify(this.request))
    this.navCtrl.navigateBack('payment')
  }
}
