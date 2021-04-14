import { StorageService } from './storage.service';
import { LocalUser } from '../models/local_user';
import { API_CONFIG } from './../config/api.config';
import { HttpClient } from '@angular/common/http';
import { CredentialsDTO } from './../models/credentials.dto';
import { Injectable } from "@angular/core";

@Injectable()
export class AuthService {

    constructor(public http: HttpClient, 
        public storage: StorageService){}

    authenticate(creds: CredentialsDTO){
        return this.http.post(`${API_CONFIG.baseUrl}/auth/login`, creds, {
            observe: 'response',
            responseType: 'json'
        })
    }

    refreshToken(){
        return this.http.post(`${API_CONFIG.baseUrl}/auth/refresh-token`, {}, {
            observe: 'response',
            responseType: 'text'
        })
    }
    successFullLogin(tokenValue: string, email: string){
        let token = tokenValue
        let user: LocalUser = {
            token,
            email
        }

        this.storage.setLocalUser(user)
    }
    logout(){
        this.storage.setLocalUser(null)
    }


}