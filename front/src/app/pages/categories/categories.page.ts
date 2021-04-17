import { NavController } from '@ionic/angular';
import { CategoryDTO } from './../../../models/category.dto';
import { CategoryService } from './../../../services/domain/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.page.html',
  styleUrls: ['./categories.page.scss'],
})
export class CategoriesPage implements OnInit {
  items: CategoryDTO[];

  constructor(public categoryService: CategoryService,
    public navControler: NavController) { }

  ngOnInit() {
    
    this.categoryService.findAll().subscribe((res)=> {
        this.items = res
    })
  }

  viewProducts(categoryName: string){
    this.navControler.navigateBack('products/'+categoryName)
  }

}
