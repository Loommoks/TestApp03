package su.zencode.testapp03;

import android.os.AsyncTask;
import android.widget.TextView;

import su.zencode.testapp03.PrnkApiClient.MoxyAppApiClient;

public class PrnkFetchr {

    public String fetchData(final TextView view) {
        AsyncTask<Void,Void,String> asyncCall = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String url = "https://prnk.blob.core.windows.net/tmp/JSONSample.json";
                return MoxyAppApiClient.getUrlString(url);
            }

            @Override
            protected void onPostExecute(String s) {
                view.setText(s);
            }
        };
        //String result = asyncCall.execute();
        //String url = "https://prnk.blob.core.windows.net/tmp/JSONSample.json";
        //return MoxyAppApiClient.getUrlString(url);
        return null;
    }
}
