import { CartItems } from './../models/cart-itens.dto';
import { STORAGE_KEYS } from './../config/storage-keys.config';
import { LocalUser } from '../models/local_user';
import { Injectable } from '@angular/core';
@Injectable()
export class StorageService{

    getLocalUser(): LocalUser{
        let user = localStorage.getItem(STORAGE_KEYS.localUser)
        if(user == null){
            return null
        }
        else{
            return JSON.parse(user)
        }
    }

    setLocalUser(obj: LocalUser){
        if(obj == null){
            localStorage.removeItem(STORAGE_KEYS.localUser)
        }else{
            localStorage.setItem(STORAGE_KEYS.localUser, JSON.stringify(obj))
        }
    }
    getLocalCart(): CartItems{
        let user = localStorage.getItem(STORAGE_KEYS.cart)
        if(user == null){
            return null
        }
        else{
            return JSON.parse(user)
        }
    }

    setLocalCart(obj: CartItems){
        if(obj == null){
            localStorage.removeItem(STORAGE_KEYS.cart)
        }else{
            localStorage.setItem(STORAGE_KEYS.cart, JSON.stringify(obj))
        }
    }
}