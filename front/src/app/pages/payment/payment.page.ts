import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RequestDTO } from './../../../models/request.dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.page.html',
  styleUrls: ['./payment.page.scss'],
})
export class PaymentPage implements OnInit {
  request: RequestDTO
  plots: number[]=[1,2,3,4,5,6,7,8,10] 
  formGroup: FormGroup

  constructor(public formBuilder: FormBuilder,
    public navControl: NavController,
    public route: ActivatedRoute) {

      this.request = this.route.snapshot.params['request']
      this.formGroup = this.formBuilder.group({
        plotsNumber: [1, Validators.required],
        "@type": ['paymentWithCard', Validators.required]
      })
     }

  ngOnInit() {
    const request = JSON.parse(window.localStorage.getItem('request'))
    this.request = request
  }

  nextPage(){
    this.request.payment = this.formGroup.value
    window.localStorage.setItem('request', JSON.stringify(this.request))
    this.navControl.navigateBack('request-confirmation')
  }
}
