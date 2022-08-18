package com.example.shifaa;

import static com.example.shifaa.MainActivity.imageDrag_cart;
import static com.example.shifaa.MainActivity.no;
import static com.example.shifaa.MainActivity.s1_c;
import static com.example.shifaa.MainActivity.s2_c;
import static com.example.shifaa.MainActivity.s3_c;
import static com.example.shifaa.MainActivity.top;
import static com.example.shifaa.MainActivity.total;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowTeethDrags extends AppCompatActivity {

    private ImageAdapter mAdapter;
    private RecyclerView mRecyleView ;

    private DatabaseReference mDatabaseRef;
    List<User2> mupload ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teeth_drags);

        mRecyleView = (RecyclerView) findViewById(R.id.ReclerView);
        mRecyleView.setLayoutManager(new LinearLayoutManager(this));
        mRecyleView.setHasFixedSize(true);
        mRecyleView.setAdapter(mAdapter);

        mupload = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("upLoads");



        /*mDatabaseRef.child("upLoads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postsnapShot : snapshot.getChildren()) {

                    Toast.makeText(ShowTeethDrags.this, "Writing succed", Toast.LENGTH_SHORT).show();
                    User2 upload = postsnapShot.getValue(User2.class);
                    mupload.add(upload);
                }
                    mAdapter = new ImageAdapter(ShowTeethDrags.this, mupload);
                    mRecyleView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowTeethDrags.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

//        customListView myAdapter = new customListView(mupload);
//        ListView ls = findViewById(R.id.ReclerView);
//        ls.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

       /* mDatabaseRef.child("upLoads").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Toast.makeText(ShowTeethDrags.this, "Writing succed", Toast.LENGTH_SHORT).show();
                User2 upload = snapshot.getValue(User2.class);
                mupload.add(upload);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowTeethDrags.this, "Connect to show Failed", Toast.LENGTH_SHORT).show();
            }
        });*/

        mDatabaseRef.child("upLoads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postsnapShot : snapshot.getChildren()) {

                    Toast.makeText(ShowTeethDrags.this, "Writing succed", Toast.LENGTH_SHORT).show();
                    User2 upload = postsnapShot.getValue(User2.class);
                    mupload.add(upload);
                }
                    mAdapter = new ImageAdapter(ShowTeethDrags.this, mupload);
                    mRecyleView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowTeethDrags.this, "Connect to show Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class customListView extends BaseAdapter {

        List<User2> Items = new ArrayList<>();

        customListView(List<User2> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getTupeOfDrag1();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.list_item2, null);
            TextView txtName = view1.findViewById(R.id.nameDrag_m);
            TextView txtdesc =  view1.findViewById(R.id.salary_m);
            ImageView cha =  view1.findViewById(R.id.imagpha_m);


            txtName.setText(Items.get(i).getTupeOfDrag1());
            txtdesc.setText(Items.get(i).getSaleOfDrag1());
            cha.setImageResource(Integer.parseInt(Items.get(i).getImgUri1()));
            return view1;
        }
    }
}