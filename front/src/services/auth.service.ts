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
        return this.http.post(`${API_CONFIG.baseUrl}/login`, creds, {
            observe: 'response',
            responseType: 'text'
        })
    }

    successFullLogin(tokenValue: string){
        let token = tokenValue.substring(7)
        let user: LocalUser = {
            token
        }

        this.storage.setLocalUser(user)
    }
    logout(){
        this.storage.setLocalUser(null)
    }
}