/*
 *    Medicine Manager - An assistant for personal medication management.
 *    Copyright (C) 2016 CITIUS - USC
 *
 *    Medicine Manager is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package test.thearch.app.main.medicinemanager;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import test.thearch.app.main.medicinemanager.util.ScreenUtils;


/**
 * Created by joseangel.pineiro on 10/30/15.
 */
public class CalendulaActivity extends AppCompatActivity{

    protected Toolbar toolbar;
    int count=0;
    //View.OnClickListener mOnClickListener;
   // private boolean doubleBackToExitPressedOnce;
    //private Handler mHandler = new Handler();

  /*  private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };*/


    protected CalendulaActivity setupToolbar(String title, int color, int iconColor){
        // set up the toolbar
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(color);
        toolbar.setNavigationIcon(getNavigationIcon(iconColor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (title == null) {
            //set the back arrow in the toolbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(title);
        }
        return this;
    }


    protected CalendulaActivity setupToolbar(String title, int color){
       return setupToolbar(title, color, Color.WHITE);
    }

    protected CalendulaActivity setupStatusBar(int color){
        ScreenUtils.setStatusBarColor(this, color);
        return this;
    }

    protected CalendulaActivity subscribeToEvents() {
        CalendulaApp.eventBus().register(this);
        return this;
    }

    protected CalendulaActivity unsubscribeFromEvents() {
        CalendulaApp.eventBus().unregister(this);
        return this;
    }

    protected Drawable getNavigationIcon(int iconColor){
        return new IconicsDrawable(this, GoogleMaterial.Icon.gmd_arrow_back)
                .sizeDp(24)
                .paddingDp(2)
                .color(iconColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        if(count == 1)
        {
            count=0;
            finish();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Press Back again to exit.", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Press Back again to Exit");

            final Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 600);
            count++;
        }

        return;
    }





}

    /*@Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    public void onBackPressed()
    {
        /*if(count == 1)
        {
            count=0;
            finish();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Press Back again to exit.", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Press Back again to Exit");

            final Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 800);
            count++;
        }





        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }



        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press Back again to Exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);

        return;
    }





}*/
