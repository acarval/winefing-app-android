package fr.dawin.winefing.winefing.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.activities.SignupActivity;
import fr.dawin.winefing.winefing.activities.UserDashboardActivity;

/**
 * Created by vmorreel on 25/01/2017.
 */
public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (SignupActivity)getActivity(), year, month, day);
    }
}
