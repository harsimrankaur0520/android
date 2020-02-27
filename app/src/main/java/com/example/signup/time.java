package com.example.signup;

public class time {
    private int valuepts;

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
