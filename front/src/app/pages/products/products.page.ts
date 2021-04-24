import { ProductService } from './../../../services/domain/product.service';
import { IonInfiniteScroll, LoadingController, NavController, NavParams } from '@ionic/angular';
import { ProductDTO } from './../../../models/product.dto';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.scss'],
})
export class ProductsPage implements OnInit {
  @ViewChild(IonInfiniteScroll) infiniteScroll: IonInfiniteScroll;

  products: ProductDTO[] = []
  page : number = 0

  constructor(public navControl: NavController,
    public productService: ProductService,
    public route: ActivatedRoute,
    public loading: LoadingController) { }

  ngOnInit() {
    const category = this.route.snapshot.params.category
    let loader = this.presentLoading()
    try{
      this.productService.findByCategoryName(category, this.page, 3).subscribe(res => {
        let start = this.products.length
        this.products = this.products.concat(res['content'])
        let end = this.products.length - 1
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

  doRefresh(event) {
    this.page = 0
    this.products = []
    this.ngOnInit()
    setTimeout(() => {
      event.target.complete();
    }, 500);
  }

  loadData(event) {
    this.page++
    this.ngOnInit()
    setTimeout(() => {
      event.target.complete();
    }, 500);
  }

  // toggleInfiniteScroll() {
  //   this.infiniteScroll.disabled = !this.infiniteScroll.disabled;
  // }

}
