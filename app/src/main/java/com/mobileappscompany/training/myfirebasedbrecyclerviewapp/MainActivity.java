package com.mobileappscompany.training.myfirebasedbrecyclerviewapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACTS_NODE = "Contacts";

    private TextView textKey;
    private EditText editName;
    private EditText editPhone;
    private Button buttonCreateContact;

    private DatabaseReference dr;

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textKey = (TextView) findViewById(R.id.textKey);
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        buttonCreateContact = (Button) findViewById(R.id.buttonCrateContact);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        contacts = new ArrayList<>();

        dr = FirebaseDatabase.getInstance().getReference(CONTACTS_NODE);

        addValueEventListener();

        initRecyclerView();

        buttonCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createContact();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.hasFixedSize();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        contactAdapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(contactAdapter);
    }

    private void createContact() {
        Contact contact = getContact();

        dr.child(contact.getKey()).setValue(contact);
    }

    @NonNull
    private Contact getContact() {
        Contact contact = new Contact();
        String key = textKey.getText().toString();
        if(key.isEmpty()) {
            key = dr.push().getKey();
        }
        contact.setKey(key);
        contact.setName(editName.getText().toString());
        contact.setPhone(editPhone.getText().toString());
        return contact;
    }

    private void addValueEventListener() {
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contacts.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    contacts.add((Contact) ds.getValue(Contact.class));
                }

                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dr.addValueEventListener(vel);
    }

    public void displayContact(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        textKey.setText(((TextView)linearLayout.getChildAt(0)).getText().toString());
        editName.setText(((TextView)linearLayout.getChildAt(1)).getText().toString());
        editPhone.setText(((TextView)linearLayout.getChildAt(2)).getText().toString());
    }
}
