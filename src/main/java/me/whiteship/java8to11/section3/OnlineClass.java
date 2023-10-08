package me.whiteship.java8to11.section3;

import me.whiteship.java8to11.section4.Progress;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;

    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * 자바 8부터 optional은 리턴타입에 쓰는거
     * @return
     */
    public Optional<Progress> getProgress() {
        //로직에 에러 던지기? 좋은 코드는 아니다
//        if(this.progress == null) throw new IllegalStateException();
        return Optional.ofNullable(progress); //null 참조..
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
