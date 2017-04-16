package com.tndo.multiaccounts;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Conal on 16/04/2017.
 */

public class AccountArrayAdapter extends ArrayAdapter {

    HashMap<String, Double> mIdMap = new HashMap<String, Double>();

    public AccountArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Account> objects) {
        super(context, resource, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i).name, objects.get(i).balance);
        }
    }

}
