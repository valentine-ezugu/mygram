package com.valentine.messenger;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@SuppressWarnings("serial")
public class UserGroupPk implements Serializable {

    private Integer userId;

    private Integer groupId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupPk)) return false;
        UserGroupPk that = (UserGroupPk) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getGroupId(), that.getGroupId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getGroupId());
    }

}
