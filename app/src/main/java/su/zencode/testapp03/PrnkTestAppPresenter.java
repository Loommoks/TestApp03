package su.zencode.testapp03;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import su.zencode.testapp03.PrnkRepositories.DataRepository;
import su.zencode.testapp03.PrnkRepositories.Picture;

@InjectViewState
public class PrnkTestAppPresenter extends MvpPresenter<PrnkTestAppView> {
    private List<Picture> mPictures;

    public PrnkTestAppPresenter() {
        getViewState().showTextBlock();
        new PrnkFetchr(this).fetchData();
    }

    public void setupTextBlock(String text){
        getViewState().showTextBlock();
        getViewState().setTextBlock(text);
    }

    public void setupPicture(Picture picture) {
        mPictures.add(picture);
        getViewState().showPicture();
        getViewState().setPicture(picture);
    }

    public void updatePictureDrawable(String id, Drawable drawable) {
        DataRepository.getInstance().setPicture(id, drawable);
        getViewState().updatePictureDrawable(id, drawable);
    }

    public void setupSelector() {

    }
}
