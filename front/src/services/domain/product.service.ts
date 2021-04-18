import { ProductDTO } from './../../models/product.dto';
import { API_CONFIG } from './../../config/api.config';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

    constructor(public http: HttpClient){}

    findByCategoryName(categoryId: string): Observable<ProductDTO[]> {
        return this.http.get<ProductDTO[]>
        (`${API_CONFIG.baseUrl}/products?categories=${categoryId}`)
    }
    findById(id: number): Observable<ProductDTO>{
        return this.http.get<ProductDTO>
        (`${API_CONFIG.baseUrl}/products/${id}`)
    }

}