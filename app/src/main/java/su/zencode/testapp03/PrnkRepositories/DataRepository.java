package su.zencode.testapp03.PrnkRepositories;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private static DataRepository sDataRepository;

    private List<TextBlock> mTextBlocks;
    private List<Picture> mPictures;
    private List<Selector> mSelectors;

    public static DataRepository getInstance() {
        if(sDataRepository == null) {
            sDataRepository = new DataRepository();
        }
        return sDataRepository;
    }

    private DataRepository() {
        mTextBlocks = new ArrayList<>();
        mPictures = new ArrayList<>();
        mSelectors = new ArrayList<>();
    }

    public void addTextBlock(TextBlock textBlock) {
        mTextBlocks.add(textBlock);
    }

    public void addPicture(Picture picture) {
        mPictures.add(picture);
    }

    public void addSelector(Selector selector) {
        mSelectors.add(selector);
    }

    public TextBlock getTextBlock(String id) {
        for (TextBlock block: mTextBlocks) {
            if(block.getId().equals(id)) {
                return block;
            }
        }
        return null;
    }

    public Picture getPicture(String id) {
        for (Picture picture :
                mPictures) {
            if(picture.getId().equals(id)){
                return picture;
            }
        }
        return null;
    }

    public Selector getSelector(String id) {
        for (Selector selector :
                mSelectors) {
            if (selector.getId().equals(id)) {
                return selector;
            }
        }
        return null;
    }
}
