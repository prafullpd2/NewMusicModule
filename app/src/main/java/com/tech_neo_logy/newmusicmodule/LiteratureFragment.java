package com.tech_neo_logy.newmusicmodule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tech_neo_logy.newmusicmodule.utils.Technical;


public class LiteratureFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ListView listView;
    String[] listValue = new String[] {"HELLO Your Choice","Technical","Comics","History","Drama","Horror","Poetry","Comics","Romentic"};
    private View view;
    private ArrayAdapter<String> adapter;

    public LiteratureFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up,container,false);

        listView = (ListView)view.findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_2, android.R.id.text1, listValue);

        listView.setAdapter(adapter);
// ListView on item selected listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
// TODO Auto-generated method stub
                if (position == 1) {
                    Intent intent = new Intent(getContext(), Technical.class);
                    startActivity(intent);
                    //  }

                }
                if (position == 2) {
                    Intent p2 = new Intent(getContext(), Comics.class);
                    startActivity(p2);


                }
                if (position == 3) {
                    Intent p3 = new Intent(getContext(), History.class);
                    startActivity(p3);


                }
                if (position == 4) {
                    Intent p4 = new Intent(getContext(), Drama.class);
                    startActivity(p4);

                }
                if (position == 5) {
                    Intent p5 = new Intent(getContext(), Horror.class);
                    startActivity(p5);

                }
                if (position == 6) {
                    Intent p6 = new Intent(getContext(), Poetry.class);
                    startActivity(p6);
                }

            }
        });


        return view;
   }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
