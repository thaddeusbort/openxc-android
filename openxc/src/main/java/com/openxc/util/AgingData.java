package com.openxc.util;

import com.google.common.base.Objects;

import com.openxc.measurements.NoValueException;

import com.openxc.units.Unit;

/**
 * AgingData is a container for a data value that keeps track of its age.
 *
 * This class keeps track of the birth time of a bit of data, i.e. time time
 * the AgingData object is instantiated.
 */
public class AgingData<TheUnit extends Unit> {
    TheUnit mValue;
    private double mBornTime;

    /**
     * Construct an instance of AgingData with the value of unit.
     *
     * @param value The data value for this bit of AgingData.
     */
    public AgingData(TheUnit value) {
        mValue = value;
        mBornTime = System.nanoTime();
    }

    /**
     * Construct an instance of AgingData with no value.
     *
     * @see NoneData
     */
    public AgingData() {
        super();
        mBornTime = System.nanoTime();
    }

    /**
     * Return the value this instance wraps.
     *
     * @return The wrapped value (an instance of TheUnit)
     */
    public TheUnit getValue() {
        return mValue;
    }

    /**
     * Retreive the age of this piece of data.
     *
     * @return the age of the data in seconds.
     */
    public double getAge() throws NoValueException {
        if(isNone()) {
            throw new NoValueException();
        }
        return (System.nanoTime() - mBornTime) / 1000000000.0;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("value", mValue)
            .add("born", mBornTime)
            .toString();
    }
}
