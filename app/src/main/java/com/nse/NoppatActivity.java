package com.nse;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class NoppatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter2 adapter;
    private List<ListItem2> listItems;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataakta);

        //hist();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        judul = findViewById(R.id.judul);


        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listItems = new ArrayList<>();
                final Calendar c = Calendar.getInstance();

                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                DatePickerDialog dp = new DatePickerDialog(NoppatActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String erg = "";
                                switch (String.valueOf(monthOfYear)) {
                                    case "9":
                                        erg += "" + String.valueOf(monthOfYear + 1);
                                        break;
                                    case "10":
                                        erg += "" + String.valueOf(monthOfYear + 1);
                                        break;
                                    case "11":
                                        erg += "" + String.valueOf(monthOfYear + 1);
                                        break;
                                    default:
                                        erg += "0" + String.valueOf(monthOfYear + 1);
                                        break;
                                }
                                int years = year - 2000;
                                erg += "/" + years;
                                judul.setText(erg);
                                hist();
                            }

                        }, y, m, d);
                dp.setTitle("No PPAT");
                dp.setMessage("Pilih bulan dan tahun PPAT yang dicari");

                dp.show();

            }
        });
    }

    private void hist() {
        class UserLogin2 extends AsyncTask<Void, Void, String> {


            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONArray obj = new JSONArray(s);

                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject count = obj.getJSONObject(i);

                        //creating a new user object
                        ListItem2 item = new ListItem2(
                                count.getString("no"),
                                count.getString("pemberi"),
                                count.getString("penerima"),
                                count.getString("tanggal"),
                                count.getString("sifat"),
                                count.getString("jenis"),
                                count.getString("letak"),
                                count.getString("bulan")

                        );
                        listItems.add(item);
                    }


                    adapter = new MyAdapter2(listItems, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    //starting the profile activity


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("searchQuery", judul.getText().toString());

                //returing the response
                return requestHandler.sendPostRequest("http://notaris.ga/json4.php", params);


            }
        }

        UserLogin2 ul = new UserLogin2();
        ul.execute();
    }
}