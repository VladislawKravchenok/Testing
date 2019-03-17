package by.vladislaw.kravchenok.projectfortests.tools;

import android.util.DisplayMetrics;

import static by.vladislaw.kravchenok.projectfortests.App.context;

/**
 * Created by Vladislaw Kravchenok on 15.02.2019.
 */
public class Metrics {
    public static int dpToPx(int dp) {
        return dp * (context().getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
