package su.zencode.testapp03;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import su.zencode.testapp03.PrnkRepositories.Picture;

public class ImageDownloader {
    private PrnkTestAppPresenter mPresenter;

    public ImageDownloader(PrnkTestAppPresenter presenter, final Picture picture) throws MalformedURLException {
        mPresenter = presenter;

        URL currentUrl = getReHostedUrl(picture);

        final Request request = new Request.Builder()
                .url(currentUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //todo solve error handle
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    final Bitmap bitmap = BitmapFactory.decodeStream(
                            response.body().byteStream());
                    //todo set Bitmap in Main Thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.updatePictureDrawable(picture.getId(),bitmap);
                        }
                    });
                } else {
                    //todo handle error
                }
            }
        });
    }

    @NonNull
    private URL getReHostedUrl(Picture picture) throws MalformedURLException {
        String urlPath = new URL(picture.getUrl()).getPath();
        String currectHost = "prnk.blob.core.windows.net";
        return new URL("https",currectHost,urlPath);
    }
}

