package su.zencode.testapp03;

import android.os.HandlerThread;

public class ImageDownloader extends HandlerThread {
    public ImageDownloader(String name) {
        super(name);
    }
}
