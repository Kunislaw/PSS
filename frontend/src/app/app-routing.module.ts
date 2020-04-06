import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { UserDataComponent } from './user-data/user-data.component';


const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'panel', component: UserDataComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
