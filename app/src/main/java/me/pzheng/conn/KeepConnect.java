package me.pzheng.conn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by k on 3/29/15.
 */

public class KeepConnect extends AsyncTask<Void, Void, Boolean[]> {
    private ProgressDialog progressDialog;

    static Context mContext;

    public KeepConnect(Context mContext) {
        this.mContext = mContext;
        progressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected Boolean[] doInBackground(Void... params) {
        DataConn dataConn = new DataConn(mContext);
        return dataConn.connecting();
    }

    @Override
    protected void onPostExecute(Boolean[] result) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        String notifString;

        if (result[0] == true) {
            notifString = mContext.getString(R.string.airplane_mode_warning);
        } else if (result[2] == true) {
            notifString = mContext.getString(R.string.wifi_connected_warning);
        } else if (result[1] == false) {
            notifString = mContext.getString(R.string.max_retrans_warning);
        } else if (result[1] == true) {
            notifString = mContext.getString(R.string.data_connected_notif);
        } else {
            notifString = mContext.getString(R.string.other_warning);
        }

        showToast(notifString);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Connecting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    private void showToast(CharSequence displayedText) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(mContext, displayedText, duration);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if(v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }
}
