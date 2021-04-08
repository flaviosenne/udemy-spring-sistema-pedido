import { StorageService } from './storage.service';
import { LocalUser } from './../models/loval_user';
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

    successFullLogin(tokenValue: string){
        let token = tokenValue
        let user: LocalUser = {
            token
        }

        this.storage.setLocalUser(user)
    }
    logout(){
        this.storage.setLocalUser(null)
    }
}