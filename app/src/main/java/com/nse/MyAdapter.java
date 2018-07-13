package com.nse;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by POPO on 2/25/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textpihak1.setText(listItem.getNama());
        holder.textsifat.setText(listItem.getSifat());
        holder.texttanggal.setText(listItem.getTanggal());
        holder.textbulanan.setText(listItem.getBulanan());


        holder.btncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

                alertDialog.setTitle("Detail");
                alertDialog.setMessage("Pihak 1 : " + listItem.getNama() + "\nPihak 2 : " + listItem.getPihak2() + "\nSifat Akta : " +
                        listItem.getSifat() + "\nTanggal : " + listItem.getTanggal() + "\nNo Bulanan : " + listItem.getBulanan());


                alertDialog.setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textpihak1;
        public TextView textsifat;
        public TextView texttanggal;
        public TextView textbulanan;
        public CardView btncard;

        public ViewHolder(View itemView) {
            super(itemView);

            textpihak1 = itemView.findViewById(R.id.textView12);
            textsifat = itemView.findViewById(R.id.textsifat);
            texttanggal = itemView.findViewById(R.id.texttanggal);
            textbulanan = itemView.findViewById(R.id.textbulanan);
            btncard = itemView.findViewById(R.id.btncard);
        }
    }
}

