import { CartService } from './../../../services/domain/cart.service';
import { CartItem } from './../../../models/cart-item.dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.page.html',
  styleUrls: ['./cart.page.scss'],
})
export class CartPage implements OnInit {
  items: CartItem[]
  total: number = 0

  constructor(public cartService: CartService) { }

  ngOnInit() {
    this.total =0
    let cart = this.cartService.getCart()
    this.items = cart.items
    this.items.map(res => this.total += Number(res.product.price))
    }

}
