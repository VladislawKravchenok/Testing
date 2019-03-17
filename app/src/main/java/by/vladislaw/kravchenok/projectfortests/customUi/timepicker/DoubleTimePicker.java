package by.vladislaw.kravchenok.projectfortests.customUi.timepicker;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import by.vladislaw.kravchenok.projectfortests.R;
import by.vladislaw.kravchenok.projectfortests.tools.DrawableManager;

import static by.vladislaw.kravchenok.projectfortests.tools.DrawableManager.drawableToBitmap;
import static by.vladislaw.kravchenok.projectfortests.tools.Metrics.dpToPx;

/**
 * Created by Vladislaw Kravchenok on 15.02.2019.
 */
public class DoubleTimePicker extends RelativeLayout {
    public DoubleTimePicker(Context context) {
        super(context);
        this.context = context;
        draw();
    }

    public DoubleTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        draw();
    }

    public DoubleTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        draw();
    }

    public DoubleTimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        draw();
    }

    private static FragmentActivity activity;

    public static void provideFragmentActivity(FragmentActivity activity) {
        DoubleTimePicker.activity = activity;
    }

    private Context context;

    private RelativeLayout background;

    private RelativeLayout headerLayout;
    private ImageButton close;
    private ImageButton accept;


    //FROM
    private LinearLayout fromTimePickerLayout;

    private LinearLayout fromHourLayout;
    private ImageView fromHourUp;
    private ViewPager fromHourViewPager;
    private PagerAdapter fromHourViewPagerAdapter;
    private ImageView fromHourDown;

    private LinearLayout fromMinuteLayout;
    private ImageView fromMinuteUp;
    private ViewPager fromMinuteViewPager;
    private PagerAdapter fromMinuteViewPagerAdapter;
    private ImageView fromMinuteDown;

    //TO
    private LinearLayout toTimePickerLayout;

    private LinearLayout toHourLayout;
    private ImageView toHourUp;
    private ViewPager toHourViewPager;
    private PagerAdapter toHourViewPagerAdapter;
    private ImageView toHourDown;

    private LinearLayout toMinuteLayout;
    private ImageView toMinuteUp;
    private ViewPager toMinuteViewPager;
    private PagerAdapter toMinuteViewPagerAdapter;
    private ImageView toMinuteDown;

    private void drawToTimePickerLayout() {
        toTimePickerLayout = new LinearLayout(context);
        toTimePickerLayout.setId(View.generateViewId());
        LayoutParams toLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toLayoutParams.addRule(BELOW, fromTimePickerLayout.getId());


        //****************************HOUR PART**************************************
        toHourLayout = new LinearLayout(context);
        toHourLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams toHourLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toHourLayoutParams.setMargins(24,4,0,4);

        toHourUp = new ImageView(context);
        LinearLayout.LayoutParams toHourUpParams = new LinearLayout.LayoutParams(dpToPx(44), dpToPx(44));
        toHourUpParams.gravity = Gravity.CENTER_HORIZONTAL;
        toHourUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (toHourViewPager.getCurrentItem() - 1 >= 0) {
                    toHourViewPager.setCurrentItem(toHourViewPager.getCurrentItem() - 1);
                }
            }
        });
        toHourUp.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        toHourUp.setImageDrawable(context.getResources().getDrawable(R.drawable.arrow_up));
        toHourUp.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        toHourViewPager = new VerticalViewPager(context);
        toHourViewPager.setId(View.generateViewId());
        LinearLayout.LayoutParams toHourViewPagerParams = new LinearLayout.LayoutParams(dpToPx(100), dpToPx(100));
        toHourViewPagerParams.gravity = Gravity.CENTER_HORIZONTAL;
        toHourViewPagerAdapter = new TimePickerHourPager(activity.getSupportFragmentManager());
        toHourViewPager.setAdapter(toHourViewPagerAdapter);


        toHourDown = new ImageView(context);
        toHourDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toHourViewPager.getCurrentItem() + 1 <= 60) {
                    toHourViewPager.setCurrentItem(toHourViewPager.getCurrentItem() + 1);
                }
            }
        });
        toHourDown.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        toHourDown.setImageDrawable(context.getResources().getDrawable(R.drawable.arrow_down));
        toHourDown.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        toHourLayout.addView(toHourUp,toHourUpParams);
        toHourLayout.addView(toHourViewPager, toHourViewPagerParams);
        toHourLayout.addView(toHourDown,toHourUpParams);

        toTimePickerLayout.addView(toHourLayout, toHourLayoutParams);


        //****************************MINUTE PART**************************************
        toMinuteLayout = new LinearLayout(context);
        toMinuteLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams toMinuteLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toMinuteLayoutParams.setMargins(24,4,0,4);

        toMinuteUp = new ImageView(context);
        toMinuteUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (toMinuteViewPager.getCurrentItem() - 1 >= 0) {
                    toMinuteViewPager.setCurrentItem(toMinuteViewPager.getCurrentItem() - 1);
                }
            }
        });
        toMinuteUp.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        toMinuteUp.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.arrow_up)), dpToPx(44), dpToPx(44)));
        toMinuteUp.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        toMinuteViewPager = new VerticalViewPager(context);
        toMinuteViewPager.setId(View.generateViewId());
        LinearLayout.LayoutParams toMinuteViewPagerParams = new LinearLayout.LayoutParams(dpToPx(100), dpToPx(100));
        toMinuteViewPagerParams.gravity = Gravity.CENTER_HORIZONTAL;
        toMinuteViewPagerAdapter = new TimePickerMinutePager(activity.getSupportFragmentManager());
        toMinuteViewPager.setAdapter(toMinuteViewPagerAdapter);


        toMinuteDown = new ImageView(context);
        toMinuteDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toMinuteViewPager.getCurrentItem() + 1 <= 60) {
                    toMinuteViewPager.setCurrentItem(toMinuteViewPager.getCurrentItem() + 1);
                }
            }
        });
        toMinuteDown.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        toMinuteDown.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.arrow_down)), dpToPx(44), dpToPx(44)));
        toMinuteDown.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        toMinuteLayout.addView(toMinuteUp);
        toMinuteLayout.addView(toMinuteViewPager, toMinuteViewPagerParams);
        toMinuteLayout.addView(toMinuteDown);

        toTimePickerLayout.addView(toMinuteLayout, toMinuteLayoutParams);


        //attack layout
        background.addView(toTimePickerLayout, toLayoutParams);
    }

    private void drawFromTimePickerLayout() {
        fromTimePickerLayout = new LinearLayout(context);
        fromTimePickerLayout.setId(View.generateViewId());
        LayoutParams fromLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fromLayoutParams.addRule(BELOW, headerLayout.getId());


        //****************************HOUR PART**************************************
        fromHourLayout = new LinearLayout(context);
        fromHourLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams fromHourLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fromHourLayoutParams.setMargins(24,4,0,4);

        fromHourUp = new ImageView(context);
        LinearLayout.LayoutParams fromHourUpParams = new LinearLayout.LayoutParams(dpToPx(44), dpToPx(44));
        fromHourUpParams.gravity = Gravity.CENTER_HORIZONTAL;
        fromHourUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fromHourViewPager.getCurrentItem() - 1 >= 0) {
                    fromHourViewPager.setCurrentItem(fromHourViewPager.getCurrentItem() - 1);
                }
            }
        });
        fromHourUp.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        fromHourUp.setImageDrawable(context.getResources().getDrawable(R.drawable.arrow_up));
        fromHourUp.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        fromHourViewPager = new VerticalViewPager(context);
        fromHourViewPager.setId(View.generateViewId());
        LinearLayout.LayoutParams fromHourViewPagerParams = new LinearLayout.LayoutParams(dpToPx(100), dpToPx(100));
        fromHourViewPagerParams.gravity = Gravity.CENTER_HORIZONTAL;
        fromHourViewPagerAdapter = new TimePickerHourPager(activity.getSupportFragmentManager());
        fromHourViewPager.setAdapter(fromHourViewPagerAdapter);


        fromHourDown = new ImageView(context);
        fromHourDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromHourViewPager.getCurrentItem() + 1 <= 60) {
                    fromHourViewPager.setCurrentItem(fromHourViewPager.getCurrentItem() + 1);
                }
            }
        });
        fromHourDown.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        fromHourDown.setImageDrawable(context.getResources().getDrawable(R.drawable.arrow_down));
        fromHourDown.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        fromHourLayout.addView(fromHourUp,fromHourUpParams);
        fromHourLayout.addView(fromHourViewPager, fromHourViewPagerParams);
        fromHourLayout.addView(fromHourDown,fromHourUpParams);

        fromTimePickerLayout.addView(fromHourLayout, fromHourLayoutParams);


        //****************************MINUTE PART**************************************
        fromMinuteLayout = new LinearLayout(context);
        fromMinuteLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams fromMinuteLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fromMinuteLayoutParams.setMargins(24,4,0,4);

        fromMinuteUp = new ImageView(context);
        fromMinuteUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fromMinuteViewPager.getCurrentItem() - 1 >= 0) {
                    fromMinuteViewPager.setCurrentItem(fromMinuteViewPager.getCurrentItem() - 1);
                }
            }
        });
        fromMinuteUp.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        fromMinuteUp.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.arrow_up)), dpToPx(44), dpToPx(44)));
        fromMinuteUp.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        fromMinuteViewPager = new VerticalViewPager(context);
        fromMinuteViewPager.setId(View.generateViewId());
        LinearLayout.LayoutParams fromMinuteViewPagerParams = new LinearLayout.LayoutParams(dpToPx(100), dpToPx(100));
        fromMinuteViewPagerParams.gravity = Gravity.CENTER_HORIZONTAL;
        fromMinuteViewPagerAdapter = new TimePickerMinutePager(activity.getSupportFragmentManager());
        fromMinuteViewPager.setAdapter(fromMinuteViewPagerAdapter);


        fromMinuteDown = new ImageView(context);
        fromMinuteDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromMinuteViewPager.getCurrentItem() + 1 <= 60) {
                    fromMinuteViewPager.setCurrentItem(fromMinuteViewPager.getCurrentItem() + 1);
                }
            }
        });
        fromMinuteDown.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        fromMinuteDown.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.arrow_down)), dpToPx(44), dpToPx(44)));
        fromMinuteDown.setColorFilter(context.getResources().getColor(R.color.acceptButton));

        fromMinuteLayout.addView(fromMinuteUp);
        fromMinuteLayout.addView(fromMinuteViewPager, fromMinuteViewPagerParams);
        fromMinuteLayout.addView(fromMinuteDown);

        fromTimePickerLayout.addView(fromMinuteLayout, fromMinuteLayoutParams);


        //attack layout
        background.addView(fromTimePickerLayout, fromLayoutParams);
    }

    private void draw() {
        drawBackground();
        drawHeaderLayout();
        drawFromTimePickerLayout();
        drawToTimePickerLayout();


    }

    private void drawBackground() {
        background = new RelativeLayout(context);
        background.setBackground(context.getResources().getDrawable(R.drawable.background_double_time_picker));
        background.setElevation(4);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(dpToPx(24), dpToPx(42), dpToPx(42), dpToPx(24));
        addView(background, layoutParams);
    }

    private void drawHeaderLayout() {
        headerLayout = new RelativeLayout(context);
        headerLayout.setId(View.generateViewId());
        headerLayout.setBackground(context.getResources().getDrawable(R.drawable.background_double_time_picker_header));
        LayoutParams headerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        drawCloseButton();
        drawAcceptButton();

        background.addView(headerLayout, headerLayoutParams);
    }

    private void drawCloseButton() {
        close = new ImageButton(context);
        close.setId(View.generateViewId());
        close.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        close.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.close)), dpToPx(44), dpToPx(44)));
        close.setColorFilter(context.getResources().getColor(R.color.closeButton));
        close.setClickable(true);
        close.setFocusable(true);
        LayoutParams closeParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        closeParams.setMargins(dpToPx(16), dpToPx(8), 0, dpToPx(8));
        closeParams.addRule(ALIGN_PARENT_START);

        headerLayout.addView(close, closeParams);
    }

    private void drawAcceptButton() {
        accept = new ImageButton(context);
        accept.setId(View.generateViewId());
        accept.setBackground(context.getResources().getDrawable(R.drawable.background_transparent));
        accept.setImageBitmap(DrawableManager.resizeBitmap(drawableToBitmap(context.getResources().getDrawable(R.drawable.accept)), dpToPx(44), dpToPx(44)));
        accept.setColorFilter(context.getResources().getColor(R.color.acceptButton));
        accept.setClickable(true);
        accept.setFocusable(true);
        LayoutParams acceptParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        acceptParams.setMargins(0, dpToPx(8), dpToPx(16), dpToPx(8));
        acceptParams.addRule(ALIGN_PARENT_END);

        headerLayout.addView(accept, acceptParams);
    }

}
