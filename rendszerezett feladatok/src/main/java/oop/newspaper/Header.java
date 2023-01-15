package oop.newspaper;

import java.util.Objects;

public class Header {

    private String content;
    private int level;

    public Header(String content, int level) {
        this.content = content;
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Header header = (Header) o;
        return level == header.level && content.equals(header.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, level);
    }
}
