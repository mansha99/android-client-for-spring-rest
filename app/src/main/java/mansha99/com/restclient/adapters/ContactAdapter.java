package mansha99.com.restclient.adapters;

import android.view.LayoutInflater;

        import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Color;
        import android.support.v4.content.ContextCompat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

import java.util.List;

import mansha99.com.androidrestclient.R;
import mansha99.com.restclient.models.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    List<Contact> m_Contacts;
    Context ctx;
    public ContactAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        ctx=context;
    }

    public ContactAdapter(Context context, int resource, List<Contact> Contacts) {
        super(context, resource, Contacts);
        ctx=context;
        this.m_Contacts = Contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_contact, null);
        }

        Contact p = m_Contacts.get(position);
        if (p != null) {
            TextView row_contact_name = (TextView) v.findViewById(R.id.row_contact_name);
            TextView row_contact_email = (TextView) v.findViewById(R.id.row_contact_email);
            if (row_contact_name != null) {
                row_contact_name.setText(p.getName());
            }
            if (row_contact_email != null) {
                row_contact_email.setText(p.getEmail());
            }

        }
        //----------------------------------------------------
        if(position % 2 == 0){
            v.setBackgroundColor(ContextCompat.getColor(ctx,R.color.tango_white_shine));
        }
        else {
            v.setBackgroundColor(ContextCompat.getColor(ctx,R.color.tango_white_mid));
        }
        //----------------------------------------------------
        return v;
    }
}