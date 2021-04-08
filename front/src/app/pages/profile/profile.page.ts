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
    public clientService: ClientService) { }

  ngOnInit() {
    if(this.auth.storage.getLocalUser){
      this.clientService.findByEmail(this.auth.storage.getLocalUser().email)
      .subscribe(res => {
        this.client = res
      }, err => console.log("error"))
      console.log(this.client)
    }
  }

}
