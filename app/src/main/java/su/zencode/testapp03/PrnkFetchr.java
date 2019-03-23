package su.zencode.testapp03;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import su.zencode.testapp03.PrnkApiClient.MoxyAppApiClient;
import su.zencode.testapp03.PrnkRepositories.DataRepository;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public class PrnkFetchr {
    private static final String TAG = "PrnkFetchr";
    private PrnkTestAppPresenter mPresenter;

    public PrnkFetchr(final PrnkTestAppPresenter presenter) {
        mPresenter = presenter;
    }

    public String fetchData() {

        AsyncTask<Void,Void,String> asyncCall = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String url = "https://prnk.blob.core.windows.net/tmp/JSONSample.json";
                sleepSecond(1);
                return MoxyAppApiClient.getUrlString(url);
            }

            @Override
            protected void onPostExecute(String s) {
                parseJson(s);
                //presenter.setupTextBlock(s);
            }

            private void sleepSecond(long sec) {
                try {
                    TimeUnit.SECONDS.sleep(sec);
                } catch (InterruptedException e) {
                    Log.e(TAG, "TimeUnit sleep error", e);
                }
            }
        };
        asyncCall.execute();
        return null;
    }

    private void parseJson(String jsonString) {
        try {
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(jsonBody);

        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON", e);
        }
    }

    private void parseItems(JSONObject jsonBody) throws JSONException {
        JSONArray dataJsonArray = jsonBody.getJSONArray("data");
        JSONArray viewJsonArray = jsonBody.getJSONArray("view");
        viewItems(viewJsonArray, dataJsonArray);
    }

    private void viewItems(JSONArray viewJsonArray, JSONArray dataJsonArray) throws JSONException {
        for (int i = 0; i < viewJsonArray.length(); i++) {
            String itemToShow = viewJsonArray.getString(i);
            switch (itemToShow) {
                case "picture":
                    break;
                case "selector":
                    break;
                default:
                    TextBlock textBlock = DataRepository.getInstance().getTextBlock(itemToShow);
                    if(textBlock == null) {
                        textBlock = parseTextBlock(itemToShow, dataJsonArray);
                        DataRepository.getInstance().addTextBlock(textBlock);
                    }
                    mPresenter.setupTextBlock(textBlock);
                    break;
            }
        }
    }

    private TextBlock parseTextBlock(String itemName, JSONArray dataJsonArray)
            throws JSONException {
        for(int i = 0; i < dataJsonArray.length(); i++) {
            JSONObject jsonArrayItem = dataJsonArray.getJSONObject(i);
            if(jsonArrayItem.getString("name").equals(itemName)){
                String blockId = jsonArrayItem.getString("name");
                String blockText = jsonArrayItem.getString("text");
                return new TextBlock(blockId, blockText);
            }
        }
        return null;
    }
}
