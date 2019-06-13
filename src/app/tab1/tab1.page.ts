import { Component } from '@angular/core';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {
  percent = 50;
  barStyleDemo = {
    border: '2px solid #108ee9'
  };

  constructor() {}

  add() {
    this.percent += 10;
    if (this.percent >= 100) {
      this.percent = 0;
    }
  }
}
