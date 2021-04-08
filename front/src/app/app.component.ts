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
    { title: 'Perfil', url: '/profile', icon: 'person' }
  ];
  constructor() {}
}
