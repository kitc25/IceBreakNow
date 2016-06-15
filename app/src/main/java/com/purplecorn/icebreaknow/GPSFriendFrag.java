package com.purplecorn.icebreaknow;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purplecorn.icebreaknow.adapter.FriendPageAdapter;
import com.purplecorn.icebreaknow.api.GetGPSFriendAsyncTask;
import com.purplecorn.icebreaknow.api.IceBreakAPIConstant;
import com.purplecorn.icebreaknow.util.AsyncTaskCallbackListener;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GPSFriendFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GPSFriendFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GPSFriendFrag extends Fragment implements AsyncTaskCallbackListener{
    private ViewPager friendPager;

    private OnFragmentInteractionListener mListener;

    public GPSFriendFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GPSFriendFrag.
     */
    public static GPSFriendFrag newInstance() {
        GPSFriendFrag fragment = new GPSFriendFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gps_friend_frag, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        friendPager = (ViewPager) view.findViewById(R.id.gps_friend_pager);
        new GetGPSFriendAsyncTask(this).execute();
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

    @Override
    public void onFinish(JSONObject result) {
        JSONObject data = result;//.optJSONObject(IceBreakAPIConstant.RESP_KEY_IB_RESPONSE);
        if("YES".equals(data.optString(IceBreakAPIConstant.RESP_KEY_RESPONSE_RESULT))){
            friendPager.setAdapter(new FriendPageAdapter(getFragmentManager(), data.optJSONArray(IceBreakAPIConstant.RESP_KEY_RESPONSE_CONTENT)));
        }

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
