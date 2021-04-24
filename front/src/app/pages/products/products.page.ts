import { ProductService } from './../../../services/domain/product.service';
import { LoadingController, NavController, NavParams } from '@ionic/angular';
import { ProductDTO } from './../../../models/product.dto';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.scss'],
})
export class ProductsPage implements OnInit {

  products: ProductDTO[]
  constructor(public navControl: NavController,
    public productService: ProductService,
    public route: ActivatedRoute,
    public loading: LoadingController) { }

  ngOnInit() {
    const category = this.route.snapshot.params.category
    let loader = this.presentLoading()
    try{

      this.productService.findByCategoryName(category).subscribe(res => {
        this.products = res['content']
        loader.then(res => {
          res.dismiss()
        })
      })
    }catch(err){
      console.log(err)

    }
  }
  productDetails(id: number){
    this.navControl.navigateForward('product-details/'+id)
  }

  presentLoading(){
    let loader = this.loading.create({
      message: 'Carregando'
    })

    loader.then(res => {
      res.present()
    })
    return loader
  }

}
