import { CartService } from './../services/domain/cart.service';
import { ProductService } from './../services/domain/product.service';
import {  AuthInterceptorProvider } from './interceptors/auth-interceptor';
import {  ErrorInterceptorProvider } from './interceptors/error-interceptor';
import { ClientService } from './../services/domain/client.service';
import { CategoryService } from 'src/services/domain/category.service';
import { StorageService } from './../services/storage.service';
import { AuthService } from './../services/auth.service';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
import {HttpClientModule} from '@angular/common/http'

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, HttpClientModule],
  providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }, 
    AuthInterceptorProvider,
    ErrorInterceptorProvider,
    CategoryService,
    AuthService, 
    StorageService,
    ClientService,
    ProductService,
    CartService    
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
