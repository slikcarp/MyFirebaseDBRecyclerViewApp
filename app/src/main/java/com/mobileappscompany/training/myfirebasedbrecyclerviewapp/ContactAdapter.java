package com.mobileappscompany.training.myfirebasedbrecyclerviewapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by User on 2/13/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter <ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.textKey.setText(contact.getKey());
        holder.textName.setText(contact.getName());
        holder.textPhone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView textKey;
        private TextView textName;
        private TextView textPhone;

        public ContactViewHolder(View itemView) {
            super(itemView);
            textKey = (TextView) itemView.findViewById(R.id.textKey);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textPhone = (TextView) itemView.findViewById(R.id.textPhone);
        }
    }
}
