import { ProductDTO } from './../../models/product.dto';
import { CartItems } from './../../models/cart-itens.dto';
import { StorageService } from './../storage.service';
import { Injectable } from "@angular/core";

@Injectable()
export class CartService{
    constructor(public storage: StorageService){
    }

    createOrClearCart(): CartItems{
        let cart: CartItems = {items: []}
        this.storage.setLocalCart(cart)
        return cart
    }
    
    getCart():CartItems{
        let cart: CartItems = this.storage.getLocalCart()
        if(cart == null){
            cart = this.createOrClearCart()
        }
        return cart
    }

    addProduct(product: ProductDTO):CartItems{
        let cart = this.getCart()
        let position = cart.items.findIndex(x=> x.product.id == product.id)
        if(position== -1){
            cart.items.push({quantity: 1, product})
        }
        this.storage.setLocalCart(cart)
        return cart
    }
}