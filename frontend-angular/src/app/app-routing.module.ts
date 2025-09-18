import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialListComponent } from './components/material-list/material-list.component';
import { MaterialFormComponent } from './components/material-form/material-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'materiales', pathMatch: 'full' },
  { path: 'materiales', component: MaterialListComponent },
  { path: 'materiales/nuevo', component: MaterialFormComponent },
  { path: 'materiales/editar/:id', component: MaterialFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
