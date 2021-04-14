import { NavController } from '@ionic/angular';
import { API_CONFIG } from './../../../config/api.config';
import { ClientService } from './../../../services/domain/client.service';
import { ClientDTO } from './../../../models/client.dto';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  client: ClientDTO 
  constructor(public auth: AuthService,
    public clientService: ClientService,
    public navCtrl: NavController,) { }

  ngOnInit() {
    if(this.auth.storage.getLocalUser){
      this.clientService.findByEmail(this.auth.storage.getLocalUser().email)
      .subscribe(res => {
        this.client = res
      }, err => {
        if(err.status == 403){
          this.navCtrl.navigateBack('/')
        }
      })
    }else{
      this.navCtrl.navigateBack('/')
    }
  }

  // getImageIfExist(){
  //   this.clientService.getImageFromBucketAWS(this.client.id)
  //   .subscribe(res => {
  //     this.client.imageUrl = `${API_CONFIG.bucketBaseUrl}/cp${this.client.id}.jpg`
  //   }, err => {})
  // }

}
