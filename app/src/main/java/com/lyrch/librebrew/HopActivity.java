package com.lyrch.librebrew;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Spinner;

import com.lyrch.librebrew.model.HopReaderContract;
import com.lyrch.librebrew.model.HopReaderDbHelper;

import java.util.HashMap;
import java.util.Map;


public class HopActivity extends Activity {


    // Create ingredient object that creates callbacks for the view objects for each ingredient
    private HopReaderDbHelper dbHandler;
    private HashMap<String, Long> hops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hop_layout);
        Button saveButton = (Button) findViewById(R.id.hop_save);
        saveButton.setOnClickListener(saveListener);

        Button cancelButton = (Button) findViewById(R.id.hop_cancel);
        cancelButton.setOnClickListener(cancelListener);

        dbHandler = HopReaderDbHelper.getInstance(getApplicationContext());

        hops = new HashMap<String, Long>();
        loadHops();
    }

    private void loadHops(){
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String[] projection = {
                HopReaderContract.HopEntry._ID,
                HopReaderContract.HopEntry.HOP_NAME
        };

        String sortOrder = HopReaderContract.HopEntry.HOP_NAME + " DESC";

        Cursor cursor = db.query(
                HopReaderContract.HopEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(HopReaderContract.HopEntry._ID));
            String hop = cursor.getString(cursor.getColumnIndexOrThrow(HopReaderContract.HopEntry.HOP_NAME));
            hops.put(hop, id);
        }

        loadHopSpinner(hops.keySet().toArray(new String[hops.size()]));
    }

    private void loadHopSpinner(String[] hopNames){
        Spinner hopSpinner = (Spinner) findViewById(R.id.hop_spinner);
        ArrayAdapter<String> hopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hopNames);
        hopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hopSpinner.setAdapter(hopAdapter);
    }

    private OnClickListener saveListener = new OnClickListener() {
        public void onClick(View v) {
            //Return the HopIngredient object and return to the receipe
        }
    };

    private OnClickListener cancelListener = new OnClickListener() {
        public void onClick(View v) {
            //Return to the receipe without an ingredient
        }
    };
}
