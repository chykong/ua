package com.critc.sys.model;

/**
 * 功能模块vo
 *
 * @author chykong
 */
public class SysModule {


    private int id;
    private String name;//模块名称
    private String code;//代码
    private Integer parent_id;//上级节点
    private String parent_name;//上级节点名称
    private String url;//链接
    private String iconImg;//
    private String target;//链接目标
    private int display_order;//排序
    private int cnt;//子节点数量

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysModule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parent_id=" + parent_id +
                ", parent_name='" + parent_name + '\'' +
                ", url='" + url + '\'' +
                ", iconImg='" + iconImg + '\'' +
                ", target='" + target + '\'' +
                ", display_order=" + display_order +
                ", cnt=" + cnt +
                '}';
    }
}
