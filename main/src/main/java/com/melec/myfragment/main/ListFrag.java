
package  com.melec.myfragment.main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import  android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFrag extends ListFragment{
    onListSelectionListener mCallback;

    public interface onListSelectionListener{
        public void onItemSelected(int pos);
    }



    @Override
   public void onCreate (Bundle savedInstanceState){
     super.onCreate(savedInstanceState);

     // We need to use a different list item layout for devices older than Honeycomb
     int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
             android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

     ArrayAdapter mAdapter = new ArrayAdapter<String>(getActivity(),layout,MainActivity.mList);

     setListAdapter(mAdapter);
 }


    @Override
    public void onStart(){
        super.onStart();

        /** When in two-pane layout, set the listview to highlight the selected list item
          (We do this during onStart because at the point the listview is available.)*/

        if(getFragmentManager().findFragmentById(R.id.details_fragment) != null){

            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (onListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onListSelectionListener");
        }
    }


    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        /** Notify the parent activity of selected item*/
        mCallback.onItemSelected(pos);

        /** Set the item as checked to be highlighted when in two-pane layout */
       getListView().setItemChecked(pos,true);
    }
}
