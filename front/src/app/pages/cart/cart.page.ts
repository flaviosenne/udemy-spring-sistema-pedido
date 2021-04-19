import { NavController } from '@ionic/angular';
import { ProductDTO } from './../../../models/product.dto';
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
  
  constructor(public cartService: CartService,
    public navControl: NavController) { }

  ngOnInit() {
    let cart = this.cartService.getCart()
    this.items = cart.items
    }

    removeItem(product: ProductDTO){
      this.items = this.cartService.removeProduct(product).items
    }
    incrementItem(product: ProductDTO){
      this.items = this.cartService.incrementProduct(product).items
    }
    decrementItem(product: ProductDTO){
      this.items = this.cartService.decrementProduct(product).items
    }
    total():number{
      return this.cartService.total()
    }

    goOn(){
      this.navControl.navigateBack('categories')
    }
    checkout(){
      this.navControl.navigateForward('pick-address')
    }
}
