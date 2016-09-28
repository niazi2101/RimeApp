package com.example.rafi715.rimeapplication.Calling;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rafi715.rimeapplication.R;

/**
 * Created by Rafi715 on 9/24/2016.
 */
public class FragmentContact extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button btnPlaceCall;
    View view;
    FragmentContact context = this;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
        //btnPlaceCall = (Button) findViewById(R.id.buttonPlace
        /*view = inflater.inflate(R.layout.fragment_contact, container, false);
        btnPlaceCall = (Button) view.findViewById(R.id.buttonPlaceCall);
        btnPlaceCall.setOnClickListener(this);
        return view;*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.buttonPlaceCall:
                Intent i = new Intent(  context,PlaceCallActivity.class  );
                startActivity(i);


                break;*/
         }
    }
}
