package com.purplecorn.icebreaknow;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purplecorn.icebreaknow.api.IceBreakAPIConstant;
import com.purplecorn.icebreaknow.ui.ColorSquareImageView;
import com.purplecorn.icebreaknow.util.IceBreakNowUtil;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendPreviewFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendPreviewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendPreviewFrag extends Fragment {
    private static final String DATA_JSON = "data_json";

    private JSONObject friendJSON;
    private ColorSquareImageView friendImg;
    private TextView name;
    private TextView age;

    private OnFragmentInteractionListener mListener;

    public FriendPreviewFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param jsonStr friend data json string.
     * @return A new instance of fragment FriendPreviewFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendPreviewFrag newInstance(String jsonStr) {
        FriendPreviewFrag fragment = new FriendPreviewFrag();
        Bundle args = new Bundle();
        args.putString(DATA_JSON, jsonStr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String jsonStr = getArguments().getString(DATA_JSON);
            try {
                friendJSON = new JSONObject(jsonStr);
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.friend_preview_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        friendImg = (ColorSquareImageView) view.findViewById(R.id.friend_img);
        name = (TextView) view.findViewById(R.id.friend_name_text);
        age = (TextView) view.findViewById(R.id.friend_age_text);

        name.setText(friendJSON.optString(IceBreakAPIConstant.RESP_KEY_NAME));
        age.setText(String.valueOf(IceBreakNowUtil.calculateAgeFromDOB(friendJSON.optString(IceBreakAPIConstant.RESP_KEY_DOB))));
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
