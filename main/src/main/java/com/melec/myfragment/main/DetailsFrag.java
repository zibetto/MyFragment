package com.melec.myfragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFrag extends Fragment {

    final static String ARG_POS = "pos";
    int mCurrentPos = -1;
    ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        /**  If activity is recreated (such as from screen rotate), restore
             the previous article selection set by onSaveInstanceState().
             This is primarily necessary when in the two-pane layout. */
        if (savedInstanceState != null){
            mCurrentPos = savedInstanceState.getInt(ARG_POS);
        }

        /** Inflate the layout for this fragment */

        return inflater.inflate(R.layout.details_view,container,false);

    }


    @Override
    public void onStart(){
        super.onStart();

        /**During startup, check if there are arguments passed to the fragment.
           onStart is a good place to do this because the layout has already been
           applied to the fragment at this point, so we can safely call the method
           below that sets the article text. */

        Bundle args = getArguments();

        if (args != null){

            /** Set Details based on argument passed in */
        updateDetailsView(args.getInt(ARG_POS));
        }
        else if (mCurrentPos != -1) {
            /** Set details based on saved instance state defined during onCreateView */
            updateDetailsView(mCurrentPos);

        }


    }

    public void updateDetailsView(int pos){

        TextView detailsview = (TextView) getActivity().findViewById(R.id.detailsView);
        detailsview.setText(MainActivity.mDetails[pos]);

        image = (ImageView) getActivity().findViewById(R.id.ImageViewLarge);
        image.setImageResource(Images.mImage[pos]);

        mCurrentPos = pos;

    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);

        /** Save the current details selection in case we need to recreate the fragment*/
        outState.putInt(ARG_POS,mCurrentPos);
    }


}