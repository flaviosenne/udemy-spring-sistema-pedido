import { Component } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';

@Component({
    selector: 'page-home',
    templateUrl:'home.html',
    styleUrls: ['home.scss']
})

export class HomePage {

    constructor(public navCtrl: NavController, public menu: MenuController){}

    login(){
        this.navCtrl.navigateBack('categories')
    }

    // ionViewWillEnter(){
    //     this.menu.swipeGesture(false)
    // }
    // ionViewDidLeave(){
    //     this.menu.swipeGesture(true)
    // }
    
}

