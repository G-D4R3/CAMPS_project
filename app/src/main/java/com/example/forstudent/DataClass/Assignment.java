package com.example.forstudent.DataClass;

import java.util.Calendar;

public class Assignment implements Comparable<Assignment>{
        private String Name=null;
        private Calendar Period;
        private String Sub; // 시간표 과목 class 생기면 대체할 것 & 생성자 오버로드
        String Memo=null;
        long sorting;
        boolean flag=false;


        public Assignment(String name, Calendar period, boolean flag){
            this.Name = name;
            this.Period = period;
            this.sorting = period.getTimeInMillis();
            this.flag = flag;
        }

        public Assignment(String name, Calendar period, String sub, boolean flag){
            this.Name = name;
            this.Period = period;
            this.Sub = sub;
            this.flag = flag;
            this.sorting = period.getTimeInMillis();
        }

        public void setName(String name){
            this.Name = name;
        }

        public void setPeriod(Calendar per){
            this.Period = per;
        }

        public void setFlag(boolean flag){
            this.flag = flag;
        }

        public String getName(){
            return Name;
        }

        public Calendar getPeriod(){
            return Period;
        }

        public String getMemo(){
            return Memo;
        }

        public boolean getFlag(){
            return flag;
        }


        @Override
        public int compareTo(Assignment a) {
            if(this.sorting<a.sorting){
                return -1;
            }
            else if(this.sorting==a.sorting){
            }
            return 1;
        }
}
