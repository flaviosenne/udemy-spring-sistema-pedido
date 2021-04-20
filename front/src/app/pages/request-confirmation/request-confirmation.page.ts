import { ClientService } from './../../../services/domain/client.service';
import { AddressDTO } from './../../../models/address.dto';
import { ClientDTO } from './../../../models/client.dto';
import { CartService } from './../../../services/domain/cart.service';
import { CartItems } from './../../../models/cart-itens.dto';
import { ActivatedRoute } from '@angular/router';
import { RequestDTO } from './../../../models/request.dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-request-confirmation',
  templateUrl: './request-confirmation.page.html',
  styleUrls: ['./request-confirmation.page.scss'],
})
export class RequestConfirmationPage implements OnInit {
  request: RequestDTO
  cartItems: CartItems
  client: ClientDTO
  address: AddressDTO 

  constructor(public route: ActivatedRoute,
    public cartService: CartService,
    public clientServie: ClientService) { 
    const request = JSON.parse(window.localStorage.getItem('request'))
    this.request = request
    }

  ngOnInit() {
    this.cartItems = this.cartService.getCart()
    
    this.clientServie.findById(this.request.client.id).subscribe(res => {
      this.client = res as ClientDTO
      this.address = res['adresses'].filter(res => this.request.deliveryAdress.id == res.id)[0]
      console.log('client ', this.client)
      console.log('address ', this.address)
      console.log('cart ', this.cartItems)
      console.log('request ', this.request)
    })
  }

  total(){
    return this.cartService.total()
  }

  confirmRequest(){
    console.log("finish")
  }

}
