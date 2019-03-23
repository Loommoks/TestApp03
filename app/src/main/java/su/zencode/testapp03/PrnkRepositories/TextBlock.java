package su.zencode.testapp03.PrnkRepositories;

public class TextBlock {
    private String mId;
    private String mText;

    public TextBlock(String id, String text) {
        mId = id;
        mText = text;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
