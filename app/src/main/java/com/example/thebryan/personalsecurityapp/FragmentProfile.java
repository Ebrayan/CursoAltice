package com.example.thebryan.personalsecurityapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.EasyImage;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView txtQuantityContact;
    TextView txtCatidadContactWHoIAMSURECONTACT;
    static  ImageView profileImage;
    static  ImageView  btn_go_to_newsNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_profiles, container, false);
        android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout= view.findViewById(R.id.collapsingToolbar);
        txtCatidadContactWHoIAMSURECONTACT= view.findViewById(R.id.txtQuantyContactWHoIAMSURECONTACT);
        txtQuantityContact = view.findViewById(R.id.txtQuantyContact);
        profileImage = view.findViewById(R.id.profileImage);

        if(Aplication.getUserActives().getProfileImageURL()!=null){
            Picasso.get().load(Aplication.getUserActives().getProfileImageURL()).into(profileImage);
        }

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  EasyImage.openChooserWithGallery(getActivity(),"Selcciona una imagen",0);

            }
        });
        view.findViewById(R.id.txtQuantyContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ActivityContact.class));
            }
        });

        view.findViewById(R.id.btn_go_to_newsNews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ActivityNewNews.class));
            }
        });
        keepingUpdateMysecureContact();
        collapsingToolbarLayout.setTitle(Aplication.getUserActives().getUsername());
        txtQuantityContact.setText(String.valueOf(Aplication.mySureContacts));
        txtCatidadContactWHoIAMSURECONTACT.setText(String.valueOf(Aplication.Quantity_peopleWhouserActiveIsSureContac));

        return view;


    }

    public void keepingUpdateMysecureContact() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Contacts");
        Query myQuery = reference.orderByChild("trustedContacOf").equalTo(Aplication.getUserActives().getUserID());
        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int quantyOfMySureCOntact = 0;
                for (DataSnapshot relations : dataSnapshot.getChildren()) {
                    quantyOfMySureCOntact++;
                }
                Aplication.setMySureContacts(quantyOfMySureCOntact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Contacts");
        Query myQuery2 = reference2.orderByChild("userID").equalTo(Aplication.getUserActives().getUserID());
        myQuery2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i= 0;
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    i++;
                }
                Aplication.setQuantity_peopleWhouserActiveIsSureContac(i);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


































    public FragmentProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
