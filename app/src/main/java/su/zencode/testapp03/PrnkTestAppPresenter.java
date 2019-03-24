package su.zencode.testapp03;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import su.zencode.testapp03.PrnkRepositories.DataRepository;
import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

@InjectViewState
public class PrnkTestAppPresenter extends MvpPresenter<PrnkTestAppView> {
    private List<Picture> mPictures;

    public PrnkTestAppPresenter() {
        /** getViewState().showTextBlock(new TextBlock("placeholderText",
                "Presenter in action")); */
        mPictures = new ArrayList<>();
        new PrnkFetchr(this).fetchData();
    }

    public void setupTextBlock(TextBlock textBlock){
        getViewState().showTextBlock(textBlock);
    }

    public void setupPicture(Picture picture) {
        mPictures.add(picture);
        getViewState().showPicture(picture);
    }

    public void updatePictureDrawable(String id, Drawable drawable) {
        DataRepository.getInstance().setPicture(id, drawable);
        getViewState().updatePictureDrawable(id, drawable);
    }

    public void setupSelector(Selector selector) {
        getViewState().showSelector(selector);
    }
}
