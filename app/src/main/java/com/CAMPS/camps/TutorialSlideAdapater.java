package com.CAMPS.camps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class TutorialSlideAdapater extends PagerAdapter {
    public View.OnClickListener listener;
    Context context;
    LayoutInflater layoutInflater;

    public int images[] = {R.mipmap.slide1, R.mipmap.slide2, R.mipmap.slide3, R.mipmap.slide4, R.mipmap.slide5};

    public String titles[] = {"1. 종강 날짜 설정과 학교 지도 보기",
                                "2. 한눈에 보는 시간표",
                                "3. 꾹 눌러서 일정 관리" ,
                                "4. 중요한 과제도 따로",
                                "5. 시험 일정과 학점 계산기"};

    public String contents[] = {"종강까지 얼마나 남았는지 알 수 있어요",
                                "위젯과 갤러리에서도 쉽게 확인할 수 있어요",
                                "과제와 시험 일정까지 한눈에!",
                                "까먹지 않도록 푸시 알림도 함께!",
                                "시간표에서 시험 과목을 불러올 수 있어요"};


    public TutorialSlideAdapater(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return (view == (View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tutorial_slider, container, false);

        TextView mTitle = (TextView)view.findViewById(R.id.tuto_title);
        TextView mContent = (TextView)view.findViewById(R.id.tuto_content);
        ImageView mImage = (ImageView)view.findViewById(R.id.tuto_image);
        TextView mStart = (TextView)view.findViewById(R.id.tuto_start);

        mTitle.setText(titles[position]);
        mContent.setText(contents[position]);
        mImage.setImageResource(images[position]);
        mStart.setVisibility(View.INVISIBLE);

        if(position==4){
            mStart.setVisibility(View.VISIBLE);
            mStart.setOnClickListener(listener);
        }

        container.addView(view);


        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
