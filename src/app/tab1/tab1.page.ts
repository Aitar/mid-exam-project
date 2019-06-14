import {Component, OnInit} from '@angular/core';
import {DataService} from '../data.service';
import {Word} from '../../assets/entity/Word';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit{

  ngOnInit(){

  }
  percent = this.userService.currUser.studied / this.userService.currWords.length;
  barStyleDemo = {
    border: '2px solid #108ee9'
  };

  constructor(private userService: DataService) {}
}
