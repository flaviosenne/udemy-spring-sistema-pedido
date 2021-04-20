import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RequestConfirmationPage } from './request-confirmation.page';

const routes: Routes = [
  {
    path: '',
    component: RequestConfirmationPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RequestConfirmationPageRoutingModule {}
