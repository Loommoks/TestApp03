package su.zencode.testapp03.PrnkRepositories;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Picture {
    private String mId;
    private String mUrl;
    private String mDescription;
    private Bitmap mBitmap;

    public Picture(String id, String url, String description) {
        mId = id;
        mUrl = url;
        mDescription = description;
        mBitmap = null;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
