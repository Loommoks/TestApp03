package su.zencode.testapp03;

import android.graphics.Bitmap;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.net.MalformedURLException;
import java.util.HashMap;

import su.zencode.testapp03.PrnkRepositories.DataRepository;
import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

@InjectViewState
public class PrnkTestAppPresenter extends MvpPresenter<PrnkTestAppView> {
    private static final String TAG = "PrnkTestAppPresenter";
    private HashMap<String, Picture> mPictures;

    public PrnkTestAppPresenter() {
        mPictures = new HashMap<>();
        new PrnkFetchr(this).fetchData();
    }

    public void setupTextBlock(TextBlock textBlock){
        getViewState().showTextBlock(textBlock);
    }

    public void setupPicture(Picture picture) {
        mPictures.put(picture.getId(), picture);
        getViewState().showPicture(picture);
        if(picture.getBitmap() == null) {
            try {
                new ImageDownloader(this, picture);
            } catch (MalformedURLException e) {
                Log.e(TAG, "Failed to use URL",e);
            }
        } else {
            getViewState().updatePictureDrawable(picture.getId(), picture.getBitmap());
        }
    }

    public void updatePictureDrawable(String id, Bitmap bitmap) {
        DataRepository.getInstance().setPicture(id, bitmap);
        getViewState().updatePictureDrawable(id, bitmap);
    }

    public void setupSelector(Selector selector) {
        getViewState().showSelector(selector);
    }

    public void processTextItemClick(TextBlock textBlock) {
        String type = "TextBlock";
        String id = textBlock.getId();
        //processClick logic
        String message = type + ", " + id + " clicked";
        getViewState().showToast(message);
    }

    public void processPictureClick(Picture picture) {
        String type = "Picture";
        String id = picture.getId();
        //processClick logic
        String message = type + ", " + id + " clicked";
        getViewState().showToast(message);
    }

    public void processSelectorClick(Selector selector, int position) {
        String type = "Selector";
        String id = selector.getId();
        //processClick logic
        String message = type + ", " +id + ", item #"+position +" clicked";
        getViewState().showToast(message);
    }
}
