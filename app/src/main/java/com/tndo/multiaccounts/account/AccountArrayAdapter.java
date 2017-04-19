package com.tndo.multiaccounts.account;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tndo.multiaccounts.R;

import java.util.List;

/**
 * Created by Conal on 16/04/2017.
 */

public class AccountArrayAdapter extends ArrayAdapter
{

    private final Context context;
    private final List<Subaccount> values;

    public AccountArrayAdapter( @NonNull Context context, @LayoutRes int resource, @NonNull List<Subaccount> values )
    {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        final Subaccount current = values.get(position);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.account_list_item, parent, false);
        final TextView nameView = (TextView) rowView.findViewById(R.id.accountName);
        final TextView balanceView = (TextView) rowView.findViewById(R.id.accountBalance);
//        ImageView accountIconView = (ImageView) rowView.findViewById(R.id.accountIcon);

        nameView.setText(current.name);
        balanceView.setText("" + current.balance);

//        Drawable icon = context.getResources().getDrawable(R.drawable.ic_commerce);
//        accountIconView.setImageIcon();
        return rowView;
    }
}
