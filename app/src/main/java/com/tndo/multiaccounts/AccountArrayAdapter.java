package com.tndo.multiaccounts;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Conal on 16/04/2017.
 */

public class AccountArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final List<Account> values;

    public AccountArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Account> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Account current = values.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.account_list_item, parent, false);
        TextView nameView = (TextView) rowView.findViewById(R.id.accountName);
        TextView balanceView = (TextView) rowView.findViewById(R.id.accountBalance);

        nameView.setText(current.name);
        balanceView.setText("" + current.balance);
        return rowView;
    }
}
