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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.Query;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


public class AddCutomViw extends AppCompatActivity {

    List<User2> l_item ;

    EditText nameDrag;
    EditText salryDrag;
    Button AddChange;
    Button gallery;
    ImageView img1;
    Button showlist;


    private static final int Pick_Image_Request = 1;
    private Uri mImageUri;

    //Storage Firebase.
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private ImageAdapter mAdapter;
    private RecyclerView mRecyleView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cutom_viw);


        nameDrag = (EditText) findViewById(R.id.getDragname);
        salryDrag = (EditText) findViewById(R.id.getSalryDrag);
        AddChange = (Button) findViewById(R.id.AddChange);
        gallery = (Button) findViewById(R.id.gallrey);
        img1 = (ImageView) findViewById(R.id.logoPha);
        showlist = (Button) findViewById(R.id.showList);


//        mRecyleView = (RecyclerView) findViewById(R.id.ReclerView);
//        mRecyleView.setLayoutManager(new LinearLayoutManager(AddCutomViw.this));
//        mRecyleView.setHasFixedSize(true);
//        mRecyleView.setAdapter(mAdapter);

        l_item = new ArrayList<>();
        // Delete custom list view.
       /* lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        });*/

        //Storage Firebase(Upload data).
        mStorageRef = FirebaseStorage.getInstance().getReference("upLoads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("upLoads");

        //Download data.

       String uploadId = mDatabaseRef.push().getKey();

       /*mDatabaseRef.child(uploadId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postsnapShot : snapshot.getChildren()) {
//                    User2 use = new User2();
                    Toast.makeText(AddCutomViw.this, "Writing succed", Toast.LENGTH_SHORT).show();
//                    use.setImgUri1(postsnapShot.child("imgUri1").getValue().toString());
//                    use.setSaleOfDrag1(postsnapShot.child("imgUri1").getValue().toString());
//                    use.setTupeOfDrag1(postsnapShot.child("imgUri1").getValue().toString());
                    User2 upload = postsnapShot.getValue(User2.class);
                    l_item.add(upload);

                    mAdapter = new ImageAdapter(AddCutomViw.this, l_item);
                    mRecyleView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddCutomViw.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/


        //Button to show data of teeth.
        showlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCutomViw.this , ShowTeethDrags.class);
                startActivity(intent);
            }
        });

        //To take photo from the gallery.
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        AddChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddCutomViw.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Pick_Image_Request);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Pick_Image_Request && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(img1);
        }
    }

    private String getFileExetention(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExetention(mImageUri)
            );

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddCutomViw.this, "Upload succed", Toast.LENGTH_SHORT).show();
                    User2 user = new User2( nameDrag.getText().toString(),
                            salryDrag.getText().toString(),
                            taskSnapshot.getUploadSessionUri().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(user);
                    nameDrag.setText("");
                    salryDrag.setText("");
                    img1.setImageResource(R.drawable.logo);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddCutomViw.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    //mProgressBar
                }
            });
        } else {
            Toast.makeText(AddCutomViw.this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

}

