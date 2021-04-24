import { LoadingController, NavController } from '@ionic/angular';
import { RequestService } from './../../../services/domain/request.service';
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
  codRequest: string

  constructor(public route: ActivatedRoute,
    public cartService: CartService,
    public clientServie: ClientService,
    public requestService: RequestService,
    public navControl: NavController,
    public loading: LoadingController) { 
    const request = JSON.parse(window.localStorage.getItem('request'))
    this.request = request
    }

  ngOnInit() {
    this.cartItems = this.cartService.getCart()
    
    this.clientServie.findById(this.request.client.id).subscribe(res => {
      this.client = res as ClientDTO
      this.address = res['adresses'].filter(res => this.request.deliveryAdress.id == res.id)[0]
    })
  }

  total(){
    return this.cartService.total()
  }

  confirmRequest(){
    let loader = this.presentLoading()
    this.requestService.insert(this.request).subscribe(res => {
      this.cartService.createOrClearCart()
      this.codRequest = this.extractId(res.body)
      loader.then(res => {
        res.dismiss()
      })
      // this.navControl.navigateForward('/cart')
    }, err => {
      if(err.status == 403){
        this.navControl.navigateRoot('/')
      }
  })
  }

  backCart(){
    this.navControl.navigateForward('/cart')
  }
  backCategories(){
    this.navControl.navigateForward('/categories')
  }

  extractId(url: string): string{
    let position = url.lastIndexOf('/')
    return url.substring(position+1, url.length)

  }

  presentLoading(){
    let loader = this.loading.create({
      message: 'Salvando pedido'
    })

    loader.then(res => {
      res.present()
    })
    return loader
  }

}
