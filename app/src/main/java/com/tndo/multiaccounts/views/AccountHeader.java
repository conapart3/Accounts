package com.tndo.multiaccounts.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tndo.multiaccounts.R;

/**
 * Created by Conal on 15/04/2017.
 */

public class AccountHeader extends View
{
    public AccountHeader( Context context )
    {
        super(context);
        initView();
    }

    public AccountHeader( Context context, @Nullable AttributeSet attrs )
    {
        super(context, attrs);
        initView();
    }

    public AccountHeader( Context context, @Nullable AttributeSet attrs, int defStyleAttr )
    {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView()
    {
        View view = inflate(getContext(), R.layout.account_header, null);
    }

}
