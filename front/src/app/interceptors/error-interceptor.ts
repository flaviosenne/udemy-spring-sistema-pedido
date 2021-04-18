import { AlertController, NavController } from '@ionic/angular';
import { StorageService } from './../../services/storage.service';
import { Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { ɵangular_packages_platform_browser_platform_browser_k } from '@angular/platform-browser';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(public storage: StorageService,
        public navCtrl: NavController,
        public alertCtrl: AlertController) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        return next.handle(req).toPromise().catch(err => {
            if (!err) return next.handle(req)

            switch (err.status) {
                case 401:
                    this.handle401()
                    break;

                case 403:
                    this.handle403()
                    break;

                default:
                    this.handleDefaultError(err)
                    break;

            }

        }) as any

    }

    handle403() {
        this.storage.setLocalUser(null)
        this.navCtrl.navigateBack('/')
    }

    handle401() {
        let show = this.alertCtrl.create({
            header: 'Erro 401: Falha de Autenticação',
            message: 'Email ou senha incorretos',
            backdropDismiss: false,
            buttons: [
                { text: 'Ok' }
            ]
        })
        show.then(res => {
            res.present()
            this.navCtrl.navigateBack('/')
        })
    }

    handleDefaultError(err) {
        let show = this.alertCtrl.create({
            header: `Erro ${err.status}: interno Servidor`,
            message: err.message,
            backdropDismiss: false,
            buttons: [
                { text: 'Ok' }
            ]
        })
        show.then(res => {
            res.present()
            this.navCtrl.navigateBack('/')
        })
    }

}

export const ErrorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
}