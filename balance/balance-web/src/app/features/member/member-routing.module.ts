import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalanceDetailsComponent } from './balance-details/balance-details.component';
import { BalanceEditComponent } from './balance-edit/balance-edit.component';
import { BalanceHomeComponent } from './balance-home/balance-home.component';
import { BalanceListComponent } from './balance-list/balance-list.component';
import { MemberComponent } from './member.component';

const routes: Routes = [{
    path: '',
    component: MemberComponent,
    children: [
      {path: 'home', component: BalanceHomeComponent},
      {path: 'list/:type', children: [
        {path: 'edit', component: BalanceEditComponent},
        {path: 'details', component: BalanceDetailsComponent},
        {path: '', component: BalanceListComponent}
      ]},
      {path: '', pathMatch: 'full', redirectTo: 'home'}
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
