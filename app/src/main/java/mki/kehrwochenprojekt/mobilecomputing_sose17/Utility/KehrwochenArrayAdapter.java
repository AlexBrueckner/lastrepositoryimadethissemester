package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

import mki.kehrwochenprojekt.mobilecomputing_sose17.R;
import android.view.*;

/**
 * Created by alexb on 03.07.2017.
 */

public final class KehrwochenArrayAdapter extends ArrayAdapter {

    private Context myContext;
    private ArrayList<String> adapterArguments;
    Class targetActivity;

    public KehrwochenArrayAdapter(Context c, ArrayList<String> args, Class target){

        super(c,-1,args);
        myContext = c;
        adapterArguments = args;
        targetActivity = target;


        System.out.println("DEBUG - KAA Constructor\n----");
        System.out.println("adapterArguments: ");
        for(Iterator<String> it = adapterArguments.iterator(); it.hasNext();){
            System.out.println("\n --------- \n " + it.next() + "\n ---------");
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newView = inflater.inflate(R.layout.button_list_entry,parent,false);
        final Button b = (Button) newView.findViewById(R.id.listEntryButton);
        b.setTag(new Integer(position));
        b.setText(adapterArguments.get(position).split("@")[0]);
        b.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                    int index = (int) b.getTag();
                    String s = adapterArguments.get(index);
                    String dataToFetch = adapterArguments.get(index).split("@")[1];
                    Intent i = new Intent(myContext,targetActivity);
                    i.putExtra("data",dataToFetch);
                    i.putExtra("adapterList",adapterArguments);
                    myContext.startActivity(i);
                }
        });

        return newView;
    }

    public static String toArgumentString(String buttonName, String argumentName){
        return buttonName + "@" + argumentName;
    }

    public ArrayList<String> getArgs(){
        return adapterArguments;
    }

}
