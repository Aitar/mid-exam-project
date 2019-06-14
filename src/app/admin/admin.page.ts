import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.page.html',
  styleUrls: ['./admin.page.scss'],
})
export class AdminPage implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  //测试通过50次get获取50个word需要多久
  getWords(){
    for(let i=0 ;i<50; i++){
      this.http.get('http://localhost:8088/untitled2_war_exploded/word/'+i+'.json')
          .subscribe((data)=>{
            console.log(data);
          });
    }
  }

}
