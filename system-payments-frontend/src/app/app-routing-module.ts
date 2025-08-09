import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './home/home';
import { Profile } from './profile/profile';
import { Login } from './login/login';
import { LoadStudents } from './load-students/load-students';
import { LoadPayments } from './load-payments/load-payments';
import { Dashboard } from './dashboard/dashboard';
import { Students } from './students/students';
import { Payments } from './payments/payments';

const routes: Routes = [
  {path:"", component:Login},
  {path:"home", component:Home},
  {path:"profile", component:Profile},
  {path:"login", component:Login},
  {path:"loadStudents", component:LoadStudents},
  {path:"loadPayments", component:LoadPayments},
  {path:"dashboard", component:Dashboard},
  {path:"students", component:Students},
  {path:"payments", component:Payments}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
