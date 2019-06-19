package com.example.a1894220.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView Txt1,Txt2;
    ArrayList<Github> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList=new ArrayList<>();

        FirebaseApp.initializeApp(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue(String.class);



                /*DataSnapshot snap=dataSnapshot.child("0");
                int watch= Integer.parseInt(snap.child("watchers").getValue().toString());
                String name = snap.child("full_name").getValue().toString();
                String name1=snap.child("full_name").getValue(String.class);


                Txt1.setText(name);
               Txt2.setText(watch); */

                Iterable<DataSnapshot> childs=dataSnapshot.getChildren();

                    for(DataSnapshot snaps : childs){

                        Github gt=snaps.getValue(Github.class);
                        System.out.println(gt.getId()+" "+gt.getName()+""+gt.getWatchers());
                        arrayList.add(gt);

                    }



                      System.out.print("size of array"+arrayList.size());
                    for(int i=0;i<arrayList.size();i++){
                        System.out.print(arrayList.get(i).getId()+""+arrayList.get(i).getName()+""+arrayList.get(i).getWatchers());
                    }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        
        

    }
}
