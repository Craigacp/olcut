package com.sun.labs.util.props;

import java.util.List;

/**
 *
 */
public class ListConfig implements Configurable {

    @Config(genericType=String.class)
    public List<String> stringList;

    @Config(genericType=Double.class)
    public List<Double> doubleList;

    @Config(genericType=StringConfigurable.class)
    public List<StringConfigurable> stringConfigurableList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListConfig that = (ListConfig) o;

        if (stringList != null ? !stringList.equals(that.stringList) : that.stringList != null) return false;
        if (doubleList != null ? !doubleList.equals(that.doubleList) : that.doubleList != null) return false;
        return stringConfigurableList != null ? stringConfigurableList.equals(that.stringConfigurableList) : that.stringConfigurableList == null;
    }

    @Override
    public int hashCode() {
        int result = stringList != null ? stringList.hashCode() : 0;
        result = 31 * result + (doubleList != null ? doubleList.hashCode() : 0);
        result = 31 * result + (stringConfigurableList != null ? stringConfigurableList.hashCode() : 0);
        return result;
    }
}
