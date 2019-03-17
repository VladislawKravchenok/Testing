package by.vladislaw.kravchenok.projectfortests.customUi.timepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.vladislaw.kravchenok.projectfortests.R;

/**
 * Created by Vladislaw Kravchenok on 16.02.2019.
 */
public class TimePickerSlidePageFragment extends Fragment {
    public static final String KEY = "time";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_values_time_picker, container, false);
        ((TextView) rootView.findViewById(R.id.time)).setText(String.valueOf(getArguments().getInt(KEY)));

        return rootView;
    }
}
