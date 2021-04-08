import { AuthService } from './../../../services/auth.service';
import { CredentialsDTO } from './../../../models/credentials.dto';
import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';



@Component({
    selector: 'page-home',
    templateUrl:'home.html',
    styleUrls: ['home.scss']
})

export class HomePage implements  OnInit{
    public credentials: CredentialsDTO = {
        email: '',
        password: ''
    }

    constructor(public navCtrl: NavController,
         public menu: MenuController, public auth: AuthService){}

    login(){
        this.auth.authenticate(this.credentials)
        .subscribe(res => {
            this.auth.successFullLogin(res.body['token'])
            this.navCtrl.navigateBack('categories')
        })
    }

    ngOnInit() {
    }
}

