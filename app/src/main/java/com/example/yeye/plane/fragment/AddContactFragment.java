package com.example.yeye.plane.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.db.PlaneDB;
import com.example.yeye.plane.entity.Contact;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button submitBtn;
    private EditText nameEdit, phoneEdit, emailEdit;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContactFragment newInstance() {
        AddContactFragment fragment = new AddContactFragment();
        return fragment;
    }

    public AddContactFragment() {
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
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        submitBtn = (Button) v.findViewById(R.id.btn_submit);
        nameEdit = (EditText) v.findViewById(R.id.edit_name);
        phoneEdit = (EditText) v.findViewById(R.id.edit_phone);
        emailEdit = (EditText) v.findViewById(R.id.edit_email);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return v;
    }

    public void submit() {
        if (check()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Contact contact = new Contact(nameEdit.getText().toString(), phoneEdit.getText().toString(), emailEdit.getText().toString());
                    PlaneDB.getInstance(getActivity()).saveContact(contact);
                }
            }).start();
            if (mListener != null) {
                mListener.addFinish();
            }
        }
    }

    private boolean check() {
        boolean flag = true;
        String email = emailEdit.getText().toString();
        String string = ".*@.*";
        if (TextUtils.isEmpty(nameEdit.getText()) || TextUtils.isEmpty(phoneEdit.getText()) || TextUtils.isEmpty(emailEdit.getText())) {
            flag = false;
        }
        if (!email.matches(string)) {
            flag = false;
            Toast.makeText(getActivity(), "邮箱类型有误", Toast.LENGTH_LONG).show();
        }
        return flag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
        public void addFinish();
    }

}
