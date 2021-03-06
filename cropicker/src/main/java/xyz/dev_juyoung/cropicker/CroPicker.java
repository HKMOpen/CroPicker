package xyz.dev_juyoung.cropicker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import xyz.dev_juyoung.cropicker.activities.DirectoryActivity;

/**
 * Created by juyounglee on 2017. 4. 15..
 *
 * Builder class to ease Intent setup.
 */

public class CroPicker {
    public static final int REQUEST_ALBUM = 3641;
    public static final int REQUEST_MEDIA = 2172;
    public static final int INDEX_VIEW_TYPE_TEXT = 123;
    public static final int INDEX_VIEW_TYPE_ICON = 321;
    public static final int MESSAGE_VIEW_TYPE_TOAST = 7;
    public static final int MESSAGE_VIEW_TYPE_SNACKBAR = 14;

    private static final String EXTRA_PREFIX = BuildConfig.APPLICATION_ID;
    public static final String EXTRA_INIT_CONFIGS = EXTRA_PREFIX + ".InitializeConfigs";
    public static final String EXTRA_RESULT_IMAGES = EXTRA_PREFIX + ".Results";

    private Activity requestedActivity;
    private Intent mIntent;
    private Bundle mOptionBundle;

    /**
     * This method creates a new CroPicker instance and creates an Intent builder.
     *
     * @param activity
     */
    public static CroPicker init(@NonNull Activity activity) {
        return new CroPicker(activity);
    }

    private CroPicker(@NonNull Activity activity) {
        requestedActivity = activity;
        mIntent = new Intent();
        mOptionBundle = new Bundle();
        mOptionBundle.putBoolean(EXTRA_INIT_CONFIGS, true);
    }

    public CroPicker withOptions(@NonNull Options options) {
        mOptionBundle.putAll(options.getOptionBundle());
        return this;
    }

    public void start() {
        start(REQUEST_ALBUM);
    }

    public void start(int requestCode) {
        if (ContextCompat.checkSelfPermission(requestedActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            throw new RuntimeException("Missing required permission(READ_EXTERNAL_STORAGE). Did you checking required permission?");
        }

        requestedActivity.startActivityForResult(getIntent(requestedActivity), requestCode);
    }

    private Intent getIntent(@NonNull Context context) {
        mIntent.setClass(context, DirectoryActivity.class);
        mIntent.putExtras(mOptionBundle);
        return mIntent;
    }

    /**
     * Class that helps to setup configs.
     * Use it with method {@link #withOptions(Options)}
     */
    public static class Options {
        public static final String EXTRA_TOOLBAR_COLOR = EXTRA_PREFIX + ".ToolbarColor";
        public static final String EXTRA_STATUSBAR_COLOR = EXTRA_PREFIX + ".StatusBarColor";
        public static final String EXTRA_TOOLBAR_WIDGET_COLOR = EXTRA_PREFIX + ".ToolbarWidgetColor";
        public static final String EXTRA_TOOLBAR_TITLE_TEXT = EXTRA_PREFIX + ".ToolbarTitleText";
        public static final String EXTRA_TOOLBAR_BACK_ARROW_DRAWABLE = EXTRA_PREFIX + ".BackArrowDrawable";
        public static final String EXTRA_TOOLBAR_DONE_DRAWABLE = EXTRA_PREFIX + ".DoneDrawable";
        public static final String EXTRA_GRID_SPAN_COUNT_OF_ALBUM = EXTRA_PREFIX + ".GridSpanCountOfAlbum";
        public static final String EXTRA_GRID_SPAN_COUNT_OF_MEDIA = EXTRA_PREFIX + ".GridSpanCountOfMedia";
        public static final String EXTRA_GRID_SPACING = EXTRA_PREFIX + ".GridSpacing";
        public static final String EXTRA_OVERLAY_VIEW_BACKGROUND_COLOR = EXTRA_PREFIX + ".OverlayViewBackgroundColor";
        public static final String EXTRA_OVERLAY_VIEW_STROKE_COLOR = EXTRA_PREFIX + ".OverlayViewStrokeColor";
        public static final String EXTRA_OVERLAY_VIEW_STROKE_WIDTH = EXTRA_PREFIX + ".OverlayViewStrokeWidth";
        public static final String EXTRA_INDEX_VIEW_TYPE = EXTRA_PREFIX + ".IndexViewType";
        public static final String EXTRA_INDEX_VIEW_TEXT_SIZE = EXTRA_PREFIX + ".IndexViewTextSize";
        public static final String EXTRA_INDEX_VIEW_TEXT_COLOR = EXTRA_PREFIX + ".IndexViewTextColor";
        public static final String EXTRA_INDEX_VIEW_ICON_DRAWABLE = EXTRA_PREFIX + ".IndexViewIconDrawable";

        public static final String EXTRA_NOT_SELECTED_MESSAGE = EXTRA_PREFIX + ".NotSelectedMessage";

        public static final String EXTRA_LIMITED_COUNT = EXTRA_PREFIX + ".LimitedCount";
        public static final String EXTRA_LIMITED_EXCEED_MESSAGE = EXTRA_PREFIX + ".LimitedExceedMessage";

        public static final String EXTRA_MESSAGE_VIEW_TYPE = EXTRA_PREFIX + ".MessageViewType";

        private Bundle mOptionBundle;

        public Options() {
            mOptionBundle = new Bundle();
        }

        @NonNull
        private Bundle getOptionBundle() {
            return mOptionBundle;
        }

        public void setToolbarColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_TOOLBAR_COLOR, color);
        }

        public void setStatusBarColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_STATUSBAR_COLOR, color);
        }

        public void setToolbarWidgetColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_TOOLBAR_WIDGET_COLOR, color);
        }

        public void setToolbarTitle(@Nullable String text) {
            mOptionBundle.putString(EXTRA_TOOLBAR_TITLE_TEXT, text);
        }

        public void setToolbarBackArrowDrawable(@DrawableRes int drawable) {
            mOptionBundle.putInt(EXTRA_TOOLBAR_BACK_ARROW_DRAWABLE, drawable);
        }

        public void setToolbarDoneDrawable(@DrawableRes int drawable) {
            mOptionBundle.putInt(EXTRA_TOOLBAR_DONE_DRAWABLE, drawable);
        }

        public void setAlbumGridSpanCount(@NonNull int count) {
            mOptionBundle.putInt(EXTRA_GRID_SPAN_COUNT_OF_ALBUM, count);
        }

        public void setMediaGridSpanCount(@NonNull int count) {
            mOptionBundle.putInt(EXTRA_GRID_SPAN_COUNT_OF_MEDIA, count);
        }

        public void setGridSpacing(@DimenRes int dimenRes) {
            mOptionBundle.putInt(EXTRA_GRID_SPACING, dimenRes);
        }

        public void setOverlayViewBackgroundColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_OVERLAY_VIEW_BACKGROUND_COLOR, color);
        }

        public void setOverlayViewStrokeColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_OVERLAY_VIEW_STROKE_COLOR, color);
        }

        public void setOverlayViewStrokeWidth(@DimenRes int dimenRes) {
            mOptionBundle.putInt(EXTRA_OVERLAY_VIEW_STROKE_WIDTH, dimenRes);
        }

        public void setIndexViewType(@NonNull int type) {
            int inType = type;

            if (inType != INDEX_VIEW_TYPE_TEXT && inType != INDEX_VIEW_TYPE_ICON) {
                inType = INDEX_VIEW_TYPE_TEXT;
            }

            mOptionBundle.putInt(EXTRA_INDEX_VIEW_TYPE, inType);
        }

        public void setIndexViewTextSize(@NonNull int size) {
            mOptionBundle.putInt(EXTRA_INDEX_VIEW_TEXT_SIZE, size);
        }

        public void setIndexViewTextColor(@ColorInt int color) {
            mOptionBundle.putInt(EXTRA_INDEX_VIEW_TEXT_COLOR, color);
        }

        /**
         * Use it with method {@link #setIndexViewType(int)}
         */
        public void setIndexViewIconDrawable(@DrawableRes int drawable) {
            // The indexView Type is specified along with the icon to prevent user mistakes.
            setIndexViewType(INDEX_VIEW_TYPE_ICON);

            mOptionBundle.putInt(EXTRA_INDEX_VIEW_ICON_DRAWABLE, drawable);
        }

        public void setNotSelectedMessage(@Nullable String text) {
            mOptionBundle.putString(EXTRA_NOT_SELECTED_MESSAGE, text);
        }

        public void setLimitedCount(@NonNull int count) {
            mOptionBundle.putInt(EXTRA_LIMITED_COUNT, count);
        }

        public void setLimitedExceedMessage(@Nullable String text) {
            mOptionBundle.putString(EXTRA_LIMITED_EXCEED_MESSAGE, text);
        }

        public void setMessageViewType(@NonNull int type) {
            int inType = type;

            if (inType != MESSAGE_VIEW_TYPE_TOAST && inType != MESSAGE_VIEW_TYPE_SNACKBAR) {
                inType = MESSAGE_VIEW_TYPE_TOAST;
            }

            mOptionBundle.putInt(EXTRA_MESSAGE_VIEW_TYPE, inType);
        }
    }
}
