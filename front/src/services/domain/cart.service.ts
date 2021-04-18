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

    removeProduct(product: ProductDTO):CartItems{
        let cart = this.getCart()
        let position = cart.items.findIndex(x=> x.product.id == product.id)
        if(position != -1){
            cart.items.splice(position,1)
        }
        this.storage.setLocalCart(cart)
        return cart
    }
    incrementProduct(product: ProductDTO):CartItems{
        let cart = this.getCart()
        let position = cart.items.findIndex(x=> x.product.id == product.id)
        if(position != -1){
            cart.items[position].quantity++
        }
        this.storage.setLocalCart(cart)
        return cart
    }
    decrementProduct(product: ProductDTO):CartItems{
        let cart = this.getCart()
        let position = cart.items.findIndex(x=> x.product.id == product.id)
        if(position != -1){
            cart.items[position].quantity--
            if(cart.items[position].quantity < 1){ 
                cart = this.removeProduct(product)
            }
        }
        this.storage.setLocalCart(cart)
        return cart
    }

    total():number{
        let cart = this.getCart()
        let aux =0
        for(let i =0; i<cart.items.length; i++){
            aux += cart.items[i].product.price * cart.items[i].quantity
        }
        return aux
    }
}