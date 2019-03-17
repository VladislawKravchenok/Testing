package by.vladislaw.kravchenok.projectfortests.customUi.timepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Button;

import static by.vladislaw.kravchenok.projectfortests.customUi.timepicker.TimePickerSlidePageFragment.KEY;

/**
 * Created by Vladislaw Kravchenok on 16.02.2019.
 */
public class TimePickerHourPager extends FragmentStatePagerAdapter {


    public TimePickerHourPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position + 1);
        TimePickerSlidePageFragment picker = new TimePickerSlidePageFragment();
        picker.setArguments(bundle);
        return picker;
    }

    @Override
    public int getCount() {
        return 24;
    }
}
