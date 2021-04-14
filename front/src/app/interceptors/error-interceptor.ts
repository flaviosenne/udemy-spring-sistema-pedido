import { NavController } from '@ionic/angular';
import { StorageService } from './../../services/storage.service';
import { Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from '@angular/core';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(public storage: StorageService, 
        public navCtrl: NavController) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        return next.handle(req).toPromise().catch(err => {
            if (!err) return next.handle(req)

            switch (err.status) {
                case 403:
                    this.handle403()
                    break;

                case 404:
                    alert('usuário não encontrado')
                    break;
            }

        }) as any

    }

    handle403() {
        this.storage.setLocalUser(null)
        alert('não autorizado')
        this.navCtrl.navigateBack('/')
    }

}

export const ErrorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
}