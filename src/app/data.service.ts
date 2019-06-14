import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../assets/entity/User';
import {Word} from '../assets/entity/Word';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient,private router: Router) { }

  currUser: User;
  currWords: Word[] = [];

  /**
   * 用于用户在登录时获取用户的信息
   * @param id
   */
  userLogin(id: number){
    this.http.get('http://localhost:8088/untitled2_war_exploded/user/'+ id + '.json')
        .subscribe((data: any)=> {
              console.log(data);
              this.currUser = new User(data.username, data.password, data.nickname, data.lastlogin, data.dailyWords, data.gender, data.studied);
              console.log(this.currUser);
              this.getAllWords();
              this.router.navigateByUrl("user");
            }
        )
  };

  /**
   * 获取用户学的所有单词
   */
  getAllWords(){
    this.http.get('http://localhost:8088/untitled2_war_exploded/word.json').
    subscribe((data: any[])=>
        {
          for(let d of data){
            let word = new Word(d.id, d.en, d.zh, 0);
            this.currWords.push(word);
          }
        }
    )
  }
}
