package oop.newspaper;

import java.util.List;

public class YellowPressArticle extends Article{

    public YellowPressArticle(String author, Header header, List<String> paragraphs) {
        super(author, header, paragraphs);
        if(header.getLevel()>5){
            throw new IllegalArgumentException("Header size cannot be greater than 5!");
        }
    }

    @Override
    int getImportance() {
        return 1;
    }
}
