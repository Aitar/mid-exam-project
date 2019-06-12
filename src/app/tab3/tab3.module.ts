import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tab3Page } from './tab3.page';
import {InfoEditComponent} from './info-edit/info-edit.component';
import {NgZorroAntdMobileModule} from 'ng-zorro-antd-mobile';
import {InfoComponent} from './info/info.component';

@NgModule({
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RouterModule.forChild([
            {path: '', component: Tab3Page, children:[
                    {path: '', component: InfoComponent }
                ]},
            ]),
        NgZorroAntdMobileModule
    ],
  declarations: [Tab3Page, InfoComponent]
})
export class Tab3PageModule {}
