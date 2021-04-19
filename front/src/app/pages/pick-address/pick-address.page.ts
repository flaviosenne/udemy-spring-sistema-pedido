import { AddressDTO } from './../../../models/address.dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pick-address',
  templateUrl: './pick-address.page.html',
  styleUrls: ['./pick-address.page.scss'],
})
export class PickAddressPage implements OnInit {

  addresses: AddressDTO[]

  constructor() { }

  ngOnInit() {
    this.addresses = [
      {
        id:"1",
        city:"Franca",
        complement:"complemento",
        number:1234,
        place:"Av martins",
        postalCode:"12345600",
        state:"SP",
        district:"primavera"
      },
      {
        id:"2",
        city:"Não sei",
        complement:"complemento",
        number:1234,
        place:"Av martins",
        postalCode:"12345600",
        state:"MG",
        district:"primavera"
      }
    ]
  }

}
