package su.zencode.testapp03;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public interface PrnkTestAppView extends MvpView {
    void showTextBlock(TextBlock textBlock);

    void showPicture(Picture picture);
    void updatePictureDrawable(String id, Bitmap bitmap);

    void showSelector(Selector selector);

    void showToast(String message);
}
