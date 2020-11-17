package com.github.binarywang.demo.wx.cp.model;

public class WxTableauMapping {
    private Integer id;

    private String userId;

    private String tableauId;

    private String tableauUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTableauId() {
        return tableauId;
    }

    public void setTableauId(String tableauId) {
        this.tableauId = tableauId == null ? null : tableauId.trim();
    }

    public String getTableauUrl() {
        return tableauUrl;
    }

    public void setTableauUrl(String tableauUrl) {
        this.tableauUrl = tableauUrl == null ? null : tableauUrl.trim();
    }
}