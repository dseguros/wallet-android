package bd.com.bdwallet.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class DensityUtil {

    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }    


    /**
     *  dp   px()
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}