package com.realswordteam.asdust.util;

public class ConvertUtil {
    public ConvertUtil convertUtil = new ConvertUtil();
    public static double convertPxToDouble(float number)
    {
        if (number > 0 && number <= 16)
        {
            return (double) number / 16;
        }   else
        {
            return 1;
        }
    }

}
