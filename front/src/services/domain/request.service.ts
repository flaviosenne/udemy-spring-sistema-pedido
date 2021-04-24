import { RequestDTO } from './../../models/request.dto';
import { API_CONFIG } from './../../config/api.config';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class RequestService {

    constructor(public http: HttpClient){}

    insert(obj: RequestDTO){
        return this.http.post(`${API_CONFIG.baseUrl}/request`, obj, 
        {observe: 'response', responseType: 'text'})
    }
   
}