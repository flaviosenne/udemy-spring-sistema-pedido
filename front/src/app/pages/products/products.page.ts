import { ProductService } from './../../../services/domain/product.service';
import { NavController, NavParams } from '@ionic/angular';
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
    public route: ActivatedRoute) { }

  ngOnInit() {
    const category = this.route.snapshot.params.category
    
    try{

      this.productService.findByCategoryName(category).subscribe(res => {
        this.products = res['content']
        console.log(res)
      })
    }catch(err){
      console.log(err)

    }
  }

}
