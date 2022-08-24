import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberComponent } from './member.component';
import { WidgetsModule } from '../widgets/widgets.module';
import { MyProductsComponent } from './my-products/my-products.component';
import { ChangePassComponent } from './change-pass/change-pass.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { PersonalInfoComponent } from './edit-profile/personal-info/personal-info.component';
import { BankInfoComponent } from './edit-profile/bank-info/bank-info.component';
import { ShippingInfoComponent } from './edit-profile/shipping-info/shipping-info.component';
import { ShippingEditFormComponent } from './edit-profile/shipping-info/shipping-edit-form/shipping-edit-form.component';
import { ProductEditComponent } from './my-products/product-edit/product-edit.component';
import { ProductListComponent } from './my-products/product-list/product-list.component';
import { ProductInfoComponent } from './my-products/product-edit/product-info/product-info.component';
import { ProductFeaturesComponent } from './my-products/product-edit/product-features/product-features.component';
import { ProductPhotosComponent } from './my-products/product-edit/product-photos/product-photos.component';
import { MessageListComponent } from './messages/message-list/message-list.component';
import { MessageDetailsComponent } from './messages/message-details/message-details.component';
import { SaleListComponent } from './sales/sale-list/sale-list.component';
import { SaleDetailsComponent } from './sales/sale-details/sale-details.component';

@NgModule({
  declarations: [
    MemberComponent,
    MyProductsComponent,
    ChangePassComponent,
    EditProfileComponent,
    PersonalInfoComponent,
    BankInfoComponent,
    ShippingInfoComponent,
    ShippingEditFormComponent,
    ProductEditComponent,
    ProductListComponent,
    ProductInfoComponent,
    ProductFeaturesComponent,
    ProductPhotosComponent,
    MessageListComponent,
    MessageDetailsComponent,
    SaleListComponent,
    SaleDetailsComponent,
  ],
  imports: [
    CommonModule,
    MemberRoutingModule,
    WidgetsModule,
    ReactiveFormsModule
  ]
})
export class MemberModule { }
