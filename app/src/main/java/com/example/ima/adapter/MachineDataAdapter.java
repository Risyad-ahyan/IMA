package com.example.ima.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.example.ima.Model.MachineData;
import com.example.ima.database.QueryDatabase;
import com.example.ima.ima.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class MachineDataAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private MachineDataAdapter.ValueFilter valueFilter;
    private ArrayList<MachineData> arrayData;
    int selector;

    public MachineDataAdapter(Context context, ArrayList<MachineData> arrayData) {
        this.context=context;
        this.arrayData = arrayData;

    }

    public MachineDataAdapter(Context context, ArrayList<MachineData> arrayData, int selector) {
        this.context=context;
        this.arrayData = arrayData;
        this.selector=selector;
    }



    @Override
    public int getCount() {
        return arrayData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayData.get(position).getMachine_ID();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_layout,null);
        }

        TextView txt1 =(TextView) convertView.findViewById(R.id.listNama);
        TextView txt2 =(TextView) convertView.findViewById(R.id.listType);
        TextView txt3 =(TextView) convertView.findViewById(R.id.textId);

        MachineData temp;
        temp=arrayData.get(position);
        System.out.println("isi ID : "+temp.getMachine_ID());

        txt1.setText(temp.getMachine_name());
        txt2.setText(temp.getMachine_type());
        txt3.setText(""+temp.getMachine_ID());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null) {
            valueFilter=new MachineDataAdapter.ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint!=null && constraint.length()>0){
                ArrayList<MachineData> arrayList = new ArrayList<MachineData>();
                for(int i=0;i<arrayData.size();i++) {
                    if(arrayData.get(i).getMachine_name().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) {
                        MachineData data = new MachineData();
                        data.setMachine_ID(arrayData.get(i).getMachine_ID());
                        data.setMachine_name(arrayData.get(i).getMachine_name());
                        data.setMachine_type(arrayData.get(i).getMachine_type());
                        arrayList.add(data);
                    }
                }
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            } else {
                filterResults.count = arrayData.size();
                filterResults.values = arrayData;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence chars,
                                      FilterResults results) {
            System.out.println(results.count);
            if(chars!=null && chars.length()>0 ) {
                arrayData = (ArrayList<MachineData>) results.values;
            } else {
                arrayData = QueryDatabase.GetAllMachineData(context.getApplicationContext());
            }
            notifyDataSetChanged();
        }
    }


}

