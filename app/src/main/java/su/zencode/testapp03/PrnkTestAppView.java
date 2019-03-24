package su.zencode.testapp03;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public interface PrnkTestAppView extends MvpView {
    void showTextBlock(TextBlock textBlock);
    void hideTextBlock();

    void showPicture(Picture picture);
    void hidePicture();
    void updatePictureDrawable(String id, Drawable drawable);

    void showSelector(Selector selector);
    void hideSelector();

}
