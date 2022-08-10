package com.example.shifaa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddCutomViw extends AppCompatActivity {

    ArrayList<User> l_item = new ArrayList<User>();
    ArrayList<String> key_item = new ArrayList<>();
    EditText nameDrag;
    EditText salryDrag;
    Button AddChange;
    ImageView img1;
    ListView lis;
    Button gallery;
    Uri selectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cutom_viw);

        nameDrag = findViewById(R.id.getDragname);
        salryDrag = findViewById(R.id.getSalryDrag);
        AddChange = findViewById(R.id.AddChange);
        img1 = findViewById(R.id.logoPha);
        gallery = findViewById(R.id.gallrey);
        customListView myAdapter = new customListView(l_item);
        lis = findViewById(R.id.listView8);
        lis.setAdapter(myAdapter);

        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCutomViw.this);
                builder.setTitle("Alert").setMessage("Do you want to delete this item ?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User");

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        AddChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               myRef.push().setValue(new User(nameDrag.getText().toString(), salryDrag.getText().toString(), R.drawable.logo)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddCutomViw.this, "Add succed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCutomViw.this, "Add Failed", Toast.LENGTH_SHORT).show();
                    }
                });

                //l_item.add(new User(nameDrag.getText().toString(), salryDrag.getText().toString(), R.drawable.logo));
                myAdapter.notifyDataSetChanged();
                nameDrag.setText("");
                salryDrag.setText("");
            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                 key_item.add(snapshot.getKey());
                 User user = snapshot.getValue(User.class);

                l_item.add(user);

                Toast.makeText(AddCutomViw.this, "Add list succed", Toast.LENGTH_SHORT).show();
                 myAdapter.notifyDataSetChanged();
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
                Toast.makeText(AddCutomViw.this, "Connect Failed", Toast.LENGTH_SHORT).show();
            }
        });

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
            View view1 = layoutInflater.inflate(R.layout.list_item2, null);
            EditText txtName = view1.findViewById(R.id.nameDrag_m);
            EditText txtdesc = view1.findViewById(R.id.salary_m);
            ImageView imag = view1.findViewById(R.id.imagpha_m);

            txtName.setText(Items.get(i).getTupeOfDrag());
            txtdesc.setText(Items.get(i).getSaleOfDrag());
            imag.setImageResource(Items.get(i).getImgDrag());
            return view1;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectImage = data.getData();
            img1.setImageURI(selectImage);
        }
    }

}

