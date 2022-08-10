package com.example.shifaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class heart extends AppCompatActivity {

    int[] imageDrag = {R.drawable.medicine1, R.drawable.medicine2, R.drawable.medicine3, R.drawable.medicine4, R.drawable.medicine5, R.drawable.medicine6, R.drawable.medicine7, R.drawable.medicine8, R.drawable.medicine9, R.drawable.medicine10};
    String[] s1, s2;
    ArrayList<User> l_item = new ArrayList<>();

    Button order;
    TextView salry;
    EditText NumbeOfDrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);

        order = findViewById(R.id.btnOrder);
        salry = findViewById(R.id.SalryOfDrag);
        NumbeOfDrag = findViewById(R.id.numOfDrag);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String present_value1_string = salry.getText().toString();
                int present_value1_int = Integer.parseInt(present_value1_string);
                String present_value2_string = NumbeOfDrag.getText().toString();
                int present_value2_int = Integer.parseInt(present_value2_string);
                int total = present_value1_int * present_value2_int;
                Toast.makeText(heart.this, total , Toast.LENGTH_LONG).show();
            }
        });

        s1 = getResources().getStringArray(R.array.NameDrag1);
        s2 = getResources().getStringArray(R.array.SalryDrag1);

        for (int i = 0; i < imageDrag.length; i++) {
            l_item.add(new User(s1[i], s2[i], imageDrag[i]));
        }

        customListView myAdapter = new customListView(l_item);
        ListView ls = findViewById(R.id.list_view2);
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