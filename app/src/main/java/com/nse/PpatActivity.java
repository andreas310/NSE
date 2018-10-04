package com.nse;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PpatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter2 adapter;

    private List<ListItem2> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataakta);

        //hist();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listItems = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(PpatActivity.this);
                builder.setTitle("Masukan nama Pihak 2");
                builder.setMessage("Jika ingin menampilkan semua, input di kosongi saja");

                // Set up the input
                final EditText input = new EditText(PpatActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String inputs = input.getText().toString();
                        class UserLogin2 extends AsyncTask<Void, Void, String> {


                            ProgressBar progressBar;

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                TextView textjudul = findViewById(R.id.judul);
                                textjudul.setText(inputs);
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
                                params.put("searchQuery", inputs);

                                //returing the response
                                return requestHandler.sendPostRequest("http://notarissilvia.tech/json/ppat", params);


                            }
                        }

                        UserLogin2 ul = new UserLogin2();
                        ul.execute();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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
                        ListItem item = new ListItem(
                                count.getString("nama"),
                                count.getString("nama2"),
                                count.getString("tanggal"),
                                count.getString("sifat"),
                                count.getString("bulanan"),
                                count.getString("bulan")

                        );
                        //listItems.add(item);
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
                params.put("searchQuery", "");

                //returing the response
                return requestHandler.sendPostRequest("http://notaris.ga/json2.php", params);


            }
        }

        UserLogin2 ul = new UserLogin2();
        ul.execute();
    }
}
