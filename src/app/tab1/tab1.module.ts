import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tab1Page } from './tab1.page';
import {StudyComponent} from './study/study.component';
import {ReviewComponent} from './review/review.component';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RouterModule.forChild([
        { path: '', component: Tab1Page },
        { path:  'study', component: StudyComponent },
        { path:  'review', component: ReviewComponent },
        ])
  ],
  declarations: [
      Tab1Page,
      StudyComponent,
      ReviewComponent
  ]
})
export class Tab1PageModule {}
