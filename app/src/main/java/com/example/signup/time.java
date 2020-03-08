package com.example.signup;

public class time {
    private int valuepts;
    String currentpoints;

    public String getCurrentpoints() {
        return currentpoints;
    }

    public void setCurrentpoints(String currentpoints) {
        this.currentpoints = currentpoints;
    }

    public time() {

    }

    public time(int valuepts) {

//        this.valuepts = valuepts;
        setValuepts(valuepts);
    }


    public int getValuepts() {
        return valuepts;
    }

    public void setValuepts(int valuepts) {

        if(valuepts>=10){
            this.valuepts=valuepts;
        }
    }
}
