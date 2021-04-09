import { Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from '@angular/core';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        
        return next.handle(req).subscribe(res => {
            console.log("passou no interceptor ", res)
            return next.handle(req)
            
        }, err => {
            console.log("não passou  ", err)
            let objError = err
            if(objError.error){
                objError = objError.error
            }if(!objError.status){
                objError = JSON.parse(objError)
            }
            console.log('erro detectado')
            console.log(objError)

            return objError
        }) as any
        
    }
}

export const ErrorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
}