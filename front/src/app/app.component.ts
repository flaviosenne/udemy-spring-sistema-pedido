import { AlertController, NavController } from '@ionic/angular';
import { AuthService } from './../services/auth.service';
import { Component } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  public appPages = [
    { title: 'Home', url: '/', icon: 'home' },
    { title: 'Categories', url: '/categories', icon: 'images' },
    { title: 'Perfil', url: '/profile', icon: 'person' },
    { title: 'Logout', icon: 'power', url: '/' }
  ];
  constructor(public auth: AuthService,
    public alertCtrl: AlertController,
    public navCtrl: NavController) { }

  logout(e: { title: string, url: string, icon: string }) {
    if (e.title == 'Logout') {
      let show = this.alertCtrl.create({
        header: `Logout`,
        message: "até mais " + this.auth.storage.getLocalUser().email,
        backdropDismiss: false,
        buttons: [
          { text: 'Ok' }
        ]
      })
      show.then(res => {
        res.present()
        this.auth.logout()
        this.navCtrl.navigateBack('/')
      })
    }
  }
}
