package su.zencode.testapp03;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import su.zencode.testapp03.PrnkApiClient.MoxyAppApiClient;
import su.zencode.testapp03.PrnkRepositories.DataRepository;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public class PrnkFetchr {
    private static final String TAG = "PrnkFetchr";
    private PrnkTestAppPresenter mPresenter;
    private DataRepository mDataRepository;

    public PrnkFetchr(final PrnkTestAppPresenter presenter) {
        mPresenter = presenter;
        mDataRepository = DataRepository.getInstance();
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
                    Selector selector = mDataRepository.getSelector(itemToShow);
                    if(selector == null) {
                        selector = parseSelector(itemToShow, dataJsonArray);
                        mDataRepository.addSelector(selector);
                    }
                    mPresenter.setupSelector(selector);
                    break;
                default:
                    TextBlock textBlock = mDataRepository.getTextBlock(itemToShow);
                    if(textBlock == null) {
                        textBlock = parseTextBlock(itemToShow, dataJsonArray);
                        mDataRepository.addTextBlock(textBlock);
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
                JSONObject jsonBlockData = jsonArrayItem.getJSONObject("data");
                String blockText = jsonBlockData.getString("text");
                return new TextBlock(blockId, blockText);
            }
        }
        return null;
    }

    private Selector parseSelector(String itemName, JSONArray dataJsonArray) throws JSONException {
        for(int i = 0; i < dataJsonArray.length(); i++) {
            JSONObject jsonArrayItem = dataJsonArray.getJSONObject(i);
            if(jsonArrayItem.getString("name").equals(itemName)){
                JSONObject blockData = jsonArrayItem.getJSONObject("data");
                int selectedId = blockData.getInt("selectedId");
                JSONArray variantsJsonArray = blockData.getJSONArray("variants");
                ArrayList<String> variants = new ArrayList<>();
                for(int j = 0; j < variantsJsonArray.length(); j++) {
                    JSONObject variant = variantsJsonArray.getJSONObject(i);
                    int index = variant.getInt("id");
                    String text = variant.getString("text");
                    variants.add(index, text);
                }
                return new Selector(itemName, selectedId,variants);
            }
        }
        return null;
    }
}
