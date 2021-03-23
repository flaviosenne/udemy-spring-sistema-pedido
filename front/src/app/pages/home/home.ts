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

    constructor(public navCtrl: NavController, public menu: MenuController){}

    login(){
        console.log(this.credentials)
        // this.navCtrl.navigateBack('categories')
    }

    ngOnInit() {
    
      console.log("carregou")
    }

    // ionViewWillEnter(){
    //     this.menu.swipeGesture(false)
    // }
    // ionViewDidLeave(){
    //     this.menu.swipeGesture(true)
    // }
    
}

