import { IonicModule } from '@ionic/angular';
import { NgModule } from "@angular/core";
import { HomePage } from "./home";

@NgModule({
    declarations: [HomePage],
    imports: [IonicModule.forRoot()]
})

export class HomeModule{

}