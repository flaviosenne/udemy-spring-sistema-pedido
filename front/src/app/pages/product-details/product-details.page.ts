import { CartService } from './../../../services/domain/cart.service';
import { NavController } from '@ionic/angular';
import { ProductDTO } from './../../../models/product.dto';
import { ProductService } from './../../../services/domain/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.page.html',
  styleUrls: ['./product-details.page.scss'],
})
export class ProductDetailsPage implements OnInit {
  public product: ProductDTO

  constructor(public route: ActivatedRoute,
    public productService: ProductService,
    public navCrontol: NavController,
    public cartService: CartService) { }

  ngOnInit() {
    const id = this.route.snapshot.params.id
      this.productService.findById(id).subscribe(res => {
        this.product = res
      })
  }
  addCart(product: ProductDTO){
    this.cartService.addProduct(product)
    this.navCrontol.navigateForward('cart')
  }

}
