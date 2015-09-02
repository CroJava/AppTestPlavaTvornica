package com.example.buby.apptestplavatvornica;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.buby.apptestplavatvornica.Fragments.HotelGalleryFragment;
import com.example.buby.apptestplavatvornica.appHelpers.ParallaxScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class HotelFullActivity extends ActionBarActivity {

    private ParallaxScrollView scrollView;
    public ViewPager viewPager;
    public int [] hoteli={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic,R.drawable.croatia,
            R.drawable.jadran,R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};

    //Manual gallery holders
    private static int [] hotelsGallery={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};
    private static int [] hotelsGallery1={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};
    private static int [] hotelsGallery2={R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};
    private static int [] hotelsGallery3={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};
    private static int [] hotelsGallery4={R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};
    private static int [] hotelsGallery5={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};
    private static int [] hotelsGallery6={R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};
    private static int [] hotelsGallery7={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};
    private static int [] hotelsGallery8={R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};
    private static int [] hotelsGallery9={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic};

    public static int photoNumber=4;
    public static int ListItemPagerID;

    GalleryAdapter galleryImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_full);

        //actionBar back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // My Image adapter
        galleryImageAdapter = new GalleryAdapter(getBaseContext(), getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.full_news_pager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setClipToPadding(false);
        //viewPager.setPadding(40, 0, 20, 0);
        //viewPager.setPageMargin(8);

        //parallax design
        scrollView = (ParallaxScrollView) findViewById(R.id.scrollView);

        scrollView.setScrollViewListener(new ParallaxScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ParallaxScrollView scrollView, int x, int y, int oldx, int oldy) {
                View view = scrollView.findViewById(R.id.fullHotelImage);
                if(view!=null){
                    view.setTranslationY(scrollView.getScrollY() / 4);
                }
            }
        });

        //geting intent from prevorius Activity
        Intent intent = getIntent();
        int ListItem_ID = intent.getIntExtra("ListItem_ID",-1);
        ListItemPagerID = ListItem_ID;

        //geting local json
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray hoteliLista = obj.getJSONArray("hoteli");
            JSONObject hoteliListaJSONObject = hoteliLista.getJSONObject(ListItem_ID);


            TextView fullHotelNaslov = (TextView) findViewById(R.id.fullHotelNaziv);
            fullHotelNaslov.setText(hoteliListaJSONObject.getString("naslov"));

            TextView fullHoteladresaA = (TextView) findViewById(R.id.adresaA);
            fullHoteladresaA.setText(hoteliListaJSONObject.getString("adresaA"));

            TextView fullHoteladresaB = (TextView) findViewById(R.id.adresaB);
            fullHoteladresaB.setText(hoteliListaJSONObject.getString("adresaB"));

            TextView fullHotelOpis = (TextView) findViewById(R.id.fullHotelOpis);
            fullHotelOpis.setText(hoteliListaJSONObject.getString("opis"));


            RatingBar fullHotelStars = (RatingBar) findViewById(R.id.starsNumber);
            fullHotelStars.setRating(hoteliListaJSONObject.getInt("stars"));

            ImageView topImage = (ImageView)findViewById(R.id.fullHotelImage);
            topImage.setImageResource(hoteli[ListItem_ID]);

            viewPager.setAdapter(galleryImageAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    // get local JSON
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("hoteli_info.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    //------------------- Gallery ViewPager Adapter -------------------
    public static class GalleryAdapter extends FragmentPagerAdapter {
        Context context;


        public GalleryAdapter(Context context, FragmentManager fm) {
            super(fm);
            context=context;
        }

        @Override
        public int getCount() {
            return photoNumber;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new HotelGalleryFragment();
            Bundle args = new Bundle();

            //if condition for browsing gallery
            if(ListItemPagerID==0){
                args.putInt("galerija_image", hotelsGallery[position]);
            }else if (ListItemPagerID==1){
                args.putInt("galerija_image", hotelsGallery1[position]);
            }else if (ListItemPagerID==2){
                args.putInt("galerija_image", hotelsGallery2[position]);
            }else if (ListItemPagerID==3){
                args.putInt("galerija_image", hotelsGallery3[position]);
            }else if (ListItemPagerID==4){
                args.putInt("galerija_image", hotelsGallery4[position]);
            }else if (ListItemPagerID==5){
                args.putInt("galerija_image", hotelsGallery5[position]);
            }else if (ListItemPagerID==6){
                args.putInt("galerija_image", hotelsGallery6[position]);
            }else if (ListItemPagerID==7){
                args.putInt("galerija_image", hotelsGallery7[position]);
            }else if (ListItemPagerID==8){
                args.putInt("galerija_image", hotelsGallery8[position]);
            }else if (ListItemPagerID==9) {
                args.putInt("galerija_image", hotelsGallery9[position]);
            }
            fragment.setArguments(args);
            return fragment;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hotel_full, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
