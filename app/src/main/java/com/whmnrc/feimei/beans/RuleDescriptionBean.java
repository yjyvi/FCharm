package com.whmnrc.feimei.beans;

/**
 * @author yjyvi
 * @data 2018/8/20.
 */

public class RuleDescriptionBean {

    private int ruleDescriptionId;
    private int icon;
    private String name;

    public RuleDescriptionBean() {
    }

    public RuleDescriptionBean(int ruleDescriptionId, int icon, String name) {
        this.ruleDescriptionId = ruleDescriptionId;
        this.icon = icon;
        this.name = name;
    }

    public int getRuleDescriptionId() {
        return ruleDescriptionId;
    }

    public void setRuleDescriptionId(int ruleDescriptionId) {
        this.ruleDescriptionId = ruleDescriptionId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
