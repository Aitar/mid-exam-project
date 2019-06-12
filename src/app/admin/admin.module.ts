import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { AdminPage } from './admin.page';
import {UserManageComponent} from './user-manage/user-manage.component';
import {WordManageComponent} from './word-manage/word-manage.component';

const routes: Routes = [
  {path: '', component: AdminPage,
    children:[
      {path: 'users', component: UserManageComponent},
      {path: 'words', component: WordManageComponent}
    ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [AdminPage, UserManageComponent, WordManageComponent]
})
export class AdminPageModule {}
