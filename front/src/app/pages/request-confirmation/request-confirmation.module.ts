import { RequestService } from './../../../services/domain/request.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RequestConfirmationPageRoutingModule } from './request-confirmation-routing.module';

import { RequestConfirmationPage } from './request-confirmation.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RequestConfirmationPageRoutingModule
  ],
  declarations: [RequestConfirmationPage],
  providers:[
    RequestService
  ]
})
export class RequestConfirmationPageModule {}
