package com.example.buby.apptestplavatvornica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.buby.apptestplavatvornica.appHelpers.hotelListHolder;
import com.example.buby.apptestplavatvornica.appHelpers.hotelListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public ArrayList<hotelListHolder> hotelDataHolder = new ArrayList<hotelListHolder>();
    public int [] hoteli={R.drawable.palace,R.drawable.zovko,R.drawable.panorama,R.drawable.antunovic,R.drawable.croatia,
                    R.drawable.jadran,R.drawable.aristos,R.drawable.sheraton,R.drawable.international,R.drawable.central};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get json object from local json
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray hoteliLista = obj.getJSONArray("hoteli");

            for (int i = 0; i < hoteliLista.length(); i++) {
                hotelListHolder holder = new hotelListHolder();
                JSONObject hoteliListaJSONObject = hoteliLista.getJSONObject(i);
                holder.setNaslov(hoteliListaJSONObject.getString("naslov"));                //
                holder.setAdresaA(hoteliListaJSONObject.getString("adresaA"));
                holder.setAdresaB( hoteliListaJSONObject.getString("adresaB"));
                hotelDataHolder.add(holder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //-------------------------------------------------------------
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new hotelListAdapter(this, hotelDataHolder,hoteli));
        //-------------------------------------------------------------

        //list item onClick listener
        listView =(ListView)findViewById(R.id.lista);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Building Intent for next activity
                Intent intent = new Intent(MainActivity.this,HotelFullActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("ListItem_ID", position);
                intent.putExtras(extras);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // get local JSON
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("hoteli.json");
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
}
