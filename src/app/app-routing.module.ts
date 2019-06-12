import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {InfoEditComponent} from './tab3/info-edit/info-edit.component';
import {StudyComponent} from './tab1/study/study.component';
import {ReviewComponent} from './tab1/review/review.component';

const routes: Routes = [
  { path: 'user', loadChildren: './tabs/tabs.module#TabsPageModule',},
  { path: '', loadChildren: './login/login.module#LoginPageModule' },
  { path: 'register', loadChildren: './register/register.module#RegisterPageModule' },
  { path: 'admin', loadChildren: './admin/admin.module#AdminPageModule' },
  { path: 'edit', component: InfoEditComponent },
  { path: 'study', component: StudyComponent },
  { path: 'review', component: ReviewComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
