import { Component } from '@angular/core';
import { MenuController, NavController } from '@ionic/angular';

@Component({
    selector: 'page-home',
    templateUrl:'home.html'
})

export class HomePage {
    constructor(public navCtrl: NavController, private menu: MenuController){

    }
    openFirst() {
        this.menu.enable(true, 'first');
        this.menu.open('first');
      }
    
      openEnd() {
        this.menu.open('end');
      }
    
      openCustom() {
        this.menu.enable(true, 'custom');
        this.menu.open('custom');
      }
}

