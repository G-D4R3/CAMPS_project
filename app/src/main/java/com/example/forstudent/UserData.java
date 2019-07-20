package com.example.forstudent;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.IndexType;
import io.objectbox.annotation.NameInDb;

/*Entity에 요소 추가하는 법

1. 프로젝트가 있는 폴더안에 app->objectbox-models->default.json을 삭제함
2. 해당 Entity를 수정
3. 생성자에 추가 (중요)
4. Build->Rebuild project
5. 앱 실행*/

@Entity
public class UserData {
    @Id
    long id;

    @NameInDb("USERNAME")
    @Index(type = IndexType.VALUE)
    String name;

    Date lastDay;

    int hello;

    //생성자에 모든 요소들을 넣어줘야함. 당장 쓰지 않더라도 디폴트 값이라도 넣어줄 것.
    public UserData(long id,String name,Date lastDay,int hello){
        this.id = id;
        this.name = name;
        this.lastDay = lastDay;
        this.hello  =hello;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setLastDay(Date lastDay){
        this.lastDay = lastDay;
    }
}
