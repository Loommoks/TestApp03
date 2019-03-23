package su.zencode.testapp03;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import su.zencode.testapp03.PrnkRepositories.Picture;

public interface PrnkTestAppView extends MvpView {
    void showTextBlock();
    void hideTextBlock();
    void setTextBlock(String text);

    void showPicture();
    void hidePicture();
    void setPicture(Picture picture);
    void updatePictureDrawable(String id, Drawable drawable);

    void showSelector();
    void hideSelector();
    void setSelector(ArrayList<String> listItems);

}
