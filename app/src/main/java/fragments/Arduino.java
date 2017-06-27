package fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.devqt.idea.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adruino_adapter.ArdAdap;
import adruino_adapter.ArdL;


public class Arduino extends Fragment {




    private ListView nameList;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    ArrayList<ArdL> ardL=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arduino, container, false);





        nameList = (ListView) getView().findViewById(R.id.listViewArd);
        return getView();


        progressDialog=new ProgressDialog(getActivity());
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://idea-projects-380e3.firebaseio.com/arduino");
        progressDialog.setMessage("Please Wait, Loading Data From Server");
        progressDialog.show();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ArdL ardL=snapshot.getValue(ArdL.class);
                    ardL.add(ardL);
                }
                ArdAdap adapter=new ArdAdap(getActivity(),ardL);
                nameList.setAdapter(adapter);
            }
//ArdAdap adapter=new ArdAdap(getApplicationContext(),ardL);
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
