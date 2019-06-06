import { Component, OnInit } from '@angular/core';
import {Word} from '../../../assets/entity/Word';
import {mockWords} from '../../../assets/mock-data/mock-words';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss'],
})
export class ReviewComponent implements OnInit {

  words: Word[] = []; //将要学习的单词数组
  currentWords: Word[] = []; //当前学习的单词与三个错误单词
  curWord: Word;
  studied: number = 0; //已学习的单词数量

  constructor() { }

  /**
   * 进入下一个单词的学习的方法
   */
  next(){
    this.studied++;
    this.currentWords = this.getStudyWords(this.studied);
    console.log(this.currentWords);
  }

  /**
   * 返回一个四个单词对象的数组，一个是正确的，三个是错误的
   * @param num
   */
  getStudyWords(num: number): Word[]{
    let words = [];
    let threRadom = this.getThreRadom(num);
    this.curWord = this.words[num];

    for(let i=0; i<threRadom.length; i++)
      words.push(this.words[threRadom[i]]);
    return words;
  }

  /**
   * 返回一个包含四个数字的数组
   * @param num 找出三个非num的数字与num一起返回
   */
  getThreRadom(num: number): number[]{
    let threRadom = [];
    let i = 0;
    let flag = false; //判断num是否在threRadom中

    //找出非num的三个数
    while (i < 3){
      let temp = ReviewComponent.randomNum(0, this.words.length-1);
      if(threRadom.includes(temp)) continue;

      if(temp == num){
        threRadom.push(num);
        flag = true;
        continue;
      }else if(temp > num){
        if(flag)  threRadom.push(temp);
        else{
          threRadom.push(temp);
          threRadom.push(num);
          flag = true;
        }
      }else if(temp < num){
        if(flag)  threRadom.push(temp);
        else{
          threRadom.push(num);
          threRadom.push(temp);
          flag = true;
        }
      }
      i++;
    }
    // console.log(threRadom);
    return threRadom;
  }

  /**
   * 产生大于等于min，小于等于max的随机数
   * @param min
   * @param max
   */
  static randomNum(min: number, max: number): number{
    let range = max - min;
    let rand = Math.random();
    return min + Math.round(rand * range);
  }

  isRight(word: Word){
    if(word != this.curWord)  this.curWord.wrongTime++;
    else {
      this.curWord.wrongTime--;

      //学习完成后从需要复习的单词列表中删除这个单词
      if(this.curWord.wrongTime <= 0){
        console.log(this.curWord.en + "学习完毕");
        this.words.splice(this.words.indexOf(this.curWord), 1);
        console.log(this.words);
      }
    }
    this.next();
  }
  ngOnInit() {
    this.words = mockWords;
    this.currentWords = this.getStudyWords(this.studied);
    console.log(this.currentWords);
  }

}
