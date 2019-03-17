package su.zencode.testapp03.PrnkApiClient;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoxyAppApiClient {
    private static final String TAG = "MoxyAppApiClient";

    public static String getUrlString(String urlSpec) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlSpec)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String resultString = response.body().string();
            return resultString;
        } catch (IOException e) {
            Log.e(TAG, "Failed to make a OkHttp Call", e);
        }

        return null;
    }
}
