import { AuthService } from './../auth.service';
import { API_CONFIG } from './../../config/api.config';
import { ClientDTO } from './../../models/client.dto';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";

@Injectable()
export class ClientService {

    constructor(public http: HttpClient,
        public auth: AuthService){}

    findByEmail(email: string) {
        // const token = this.auth.storage.getLocalUser().token
        // const headers = new HttpHeaders({'Authorization': 'Bearer '+ token}) 
        return this.http.get(
            `${API_CONFIG.baseUrl}/clients/email?value=${email}`)
    }

    // getImageFromBucketAWS(id: string): Observable<any>{
    //     let url = `${API_CONFIG.bucketBaseUrl}/cp${id}.jpg`
    //     return this.http.get(url, { responseType: 'blob'})
    // }

    save(client: ClientDTO){
        return this.http.post<ClientDTO>(
            `${API_CONFIG.baseUrl}/clients`, client
        )
    }
}