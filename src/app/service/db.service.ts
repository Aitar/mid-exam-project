import {Injectable} from '@angular/core';
import {SQLite, SQLiteObject} from '@ionic-native/sqlite/ngx';
import {BehaviorSubject} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DbService {
    db: SQLiteObject;

    /**
     * 初始化创建一个叫数据库并在数据库中创建一个users表，一个words表
     * @param sqLite
     */
    constructor(private sqLite: SQLite) {
        sqLite.create({
            name: 'wordsDB.db',
            location: 'default'
        }).then(  //执行成功
            (db: SQLiteObject) => {
                db.executeSql('create table if not exists users(id integer primary key autoIncrement, username nvarchar(50), nickname nvarchar(50), lastNum integer)')
                    .then(() => {
                        this.load("users");
                    })
                    .catch(reason => {
                        console.log(reason);
                        alert('创建用户表失败!');
                    });//sqlite是无类型的
                db.executeSql('create table if not exists words(id integer primary key autoIncrement, en nvarchar(50), zh nvarchar(50))')
                    .then(() => {
                        this.load("words");
                    })
                    .catch(reason => {
                        console.log(reason);
                        alert('创建单词表失败!');
                    });//sqlite是无类型的
            }
        ).catch(  //执行失败
            (reason) => {
                console.log(reason);
                alert('数据库创建或打开失败!');
            }
        );  //异步方法用then和catch处理后续操作
    }


    /**
     * 读取指定表中所有的数据(未完成)
     * @param tableName
     */
    load(tableName: string) {
        this.db.executeSql('select * from ' + tableName, [])
            .then(
                data => {
                    let itmes = [];
                    for (let i = 0; i < data.rows.length; i++) {
                        let note = data.rows.item(i);   //获取data中的每一行数据，itme(1)代表获取第一行
                        itmes.push(note);
                    }
                })
            .catch();
    }
}
