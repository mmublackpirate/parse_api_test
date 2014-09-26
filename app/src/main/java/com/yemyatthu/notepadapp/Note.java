package com.yemyatthu.notepadapp;

public class Note {
    private String mId;
    private String mTitle;
    private String mContent;

    public Note(String id,String title,String content){
        mId = id;
        mTitle = title;
        mContent = content;
    }

    public void setId(String id){
        mId = id;
    }
    public String getId(){
        return mId;
    }
    public void setTitle(String title){
        mTitle = title;
    }
    public String getTitle(){
        return mTitle;
    }
    public void setContent(String content){
        mContent = content;
    }
    public String getContent(){
        return mContent;
    }
    public String toString(){
        return this.getTitle();
    }

}
