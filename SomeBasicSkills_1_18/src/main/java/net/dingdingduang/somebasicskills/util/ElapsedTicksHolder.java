package net.dingdingduang.somebasicskills.util;

public class ElapsedTicksHolder {
    private int IntCounter;

    public ElapsedTicksHolder(int counter) {
        this.IntCounter = counter;
    }

    public int getIntCounter() { return this.IntCounter; }
    public void setIntCounter(int intCounter) { this.IntCounter = intCounter; }

    @Override
    public int hashCode() {
        return this.IntCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        ElapsedTicksHolder that = (ElapsedTicksHolder) o;
        return this.IntCounter == that.IntCounter;
    }
}
