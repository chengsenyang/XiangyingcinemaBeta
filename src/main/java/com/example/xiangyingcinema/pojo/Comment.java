package com.example.xiangyingcinema.pojo;

import java.util.List;

public class Comment {
    private String flagid;
    private String cmid;
    private String comment;
    private String flag;
    private String cmemail;
    private String cmname;
    private String btitle;
    private String cmtime;
    private String cmflag;
    private String superiorid;
    private String superiorname;
    private String cmcontext;
    private String comments;

    public String getComments() {
        return comments;
    }


    public void setComments(List<Comment> sonComment) {
    }

    public String getCmcontext() {
        return cmcontext;
    }

    public void setCmcontext(String cmcontext) {
        this.cmcontext = cmcontext;
    }

    public String getCmemail() {
        return cmemail;
    }

    public void setCmemail(String cmemail) {
        this.cmemail = cmemail;
    }

    public String getCmname() {
        return cmname;
    }

    public void setCmname(String cmname) {
        this.cmname = cmname;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getCmtime() {
        return cmtime;
    }

    public void setCmtime(String cmtime) {
        this.cmtime = cmtime;
    }

    public String getCmflag() {
        return cmflag;
    }

    public void setCmflag(String cmflag) {
        this.cmflag = cmflag;
    }

    public String getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid;
    }

    public String getSuperiorname() {
        return superiorname;
    }

    public void setSuperiorname(String superiorname) {
        this.superiorname = superiorname;
    }

    public String getFlagid() {
        return flagid;
    }

    public void setFlagid(String flagid) {
        this.flagid = flagid;
    }

    public String getCmid() {
        return cmid;
    }

    public void setCmid(String cmid) {
        this.cmid = cmid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
