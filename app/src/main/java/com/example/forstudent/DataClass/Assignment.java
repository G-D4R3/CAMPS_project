package com.example.forstudent.DataClass;

import java.util.Calendar;

public class Assignment extends Event implements Comparable<Assignment>{
        private String Name=null;
        private Calendar Period;
        String Memo;
        long sorting;
        boolean flag=false;


        public Assignment(String name, Calendar period, String memo, boolean flag){
            this.Name = name;
            this.Period = period;
            this.Memo = memo;
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

    @Override
    public String toString() {
        return "Assignment{" +
                "Name='" + Name + '\'' +
                ", Period=" + Period +
                ", Memo='" + Memo + '\'' +
                ", sorting=" + sorting +
                ", flag=" + flag +
                '}';
    }
}
