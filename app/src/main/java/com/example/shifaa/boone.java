package com.example.shifaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class boone extends AppCompatActivity {

    int[] imageDrag = {R.drawable.boon1, R.drawable.boon2, R.drawable.boon3, R.drawable.boon4, R.drawable.boon5, R.drawable.boon6, R.drawable.boon7, R.drawable.boon8, R.drawable.boon9, R.drawable.boon10};
    String[] s1, s2;
    ArrayList<User> l_item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boone);

        s1 = getResources().getStringArray(R.array.NameDrag2);
        s2 = getResources().getStringArray(R.array.SalryDrag2);

        for (int i = 0; i < imageDrag.length; i++) {
            l_item.add(new User(s1[i], s2[i], imageDrag[i]));
        }

        customListView myAdapter = new customListView(l_item);
        ListView ls = findViewById(R.id.list_view);
        ls.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    class customListView extends BaseAdapter {

        ArrayList<User> Items = new ArrayList<User>();

        customListView(ArrayList<User> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getTupeOfDrag();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.list_item, null);
            TextView txtName = view1.findViewById(R.id.nameDrag);
            TextView txtdesc = (TextView) view1.findViewById(R.id.SalryOfDrag);
            TextView cha = (TextView) view1.findViewById(R.id.numOfDrag);
            ImageView imag = (ImageView) view1.findViewById(R.id.imagpha);
            Button btnPlu = (Button) view1.findViewById(R.id.pluseAction);
            Button btnmin = (Button) view1.findViewById(R.id.minesAction);
            btnPlu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    present_value_int++;
                    cha.setText(String.valueOf(present_value_int));
                }
            });

            btnmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    if (present_value_int > 0) {
                        present_value_int--;
                    }
                    cha.setText(String.valueOf(present_value_int));
                }
            });

            txtName.setText(Items.get(i).getTupeOfDrag());
            txtdesc.setText(Items.get(i).getSaleOfDrag());
            imag.setImageResource(Items.get(i).getImgDrag());
            return view1;
        }
    }
}