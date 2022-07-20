import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProcessComponent } from './process/process.component';
import { ResultsTableComponent } from './results-table/results-table.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'results',
    component: ResultsTableComponent,
  },
  {
    path: 'process',
    component: ProcessComponent,
  },
  { path: '**', component: HomeComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
