package com.example.thebryan.personalsecurityapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_menu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_menu extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
     private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_menu() {
        // Required empty public constructor
    }

    public static Fragment_menu newInstance(String param1, String param2) {
        Fragment_menu fragment = new Fragment_menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        getContext().getTheme().applyStyle(R.style.Theme_AppCompat_NoActionBar,true);
        View view =  inflater.inflate(R.layout.fragment_mainmenu, container, false);
        ImageButton btngo_to_contact =view.findViewById(R.id.contactbutton_dashmnenu);
        btngo_to_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivityContact.class));
            }
        });
        ImageButton btngo_to_map =view.findViewById(R.id.btn_go_to_map);
        btngo_to_map .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivityMaps.class));
            }
        });

        ImageButton btn_go_to_news=view.findViewById(R.id.btn_go_to_news);
        btn_go_to_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivityViewNews.class));
            }
        });

        ImageButton btn_go_toWritePost=view.findViewById(R.id.btn_go_toWritePost);
        btn_go_toWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivityNewNews.class));
            }
        });
        ImageButton btn_go_tosetting=view.findViewById(R.id.btn_go_to_setting);
        btn_go_toWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivityNewNews.class));
            }
        });

        ImageView btnEmergecy = view.findViewById(R.id.btnEmergecy);
        btnEmergecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ActivitySomeEmergency.class));
            }
        });
    return  view;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go_to_map :
                startActivity(new Intent(getContext(),ActivityMaps.class));
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
