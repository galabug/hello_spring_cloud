package com.gala.bug.rocketmq;

public class Solution {
    public static void main(String[] args) {
         System.out.println(convert("PAYPALISHIRING",3));
    }



    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        for (int row = 0; row < numRows; row++) {
            int rowIndex = 0;
            for (int columnIndex = 0; rowIndex < s.length(); columnIndex++) {
                int spaceNum = 0;
                if (columnIndex == 0) {
                    rowIndex = row;
                } else if (row == 0||row==numRows-1) {
                    rowIndex += (numRows-1)*2   ;
                    spaceNum = numRows-1;
                }else if (columnIndex%2==1) {
                    rowIndex += (numRows-row-1)*2   ;
                    spaceNum = numRows-row-1;
                } else if (columnIndex%2==0) {
                    rowIndex += row*2   ;
                    spaceNum = row-1;
                }

                if (rowIndex < s.length()) {
//                    if (spaceNum >0) {
//                        sb.append(String.format("%" + spaceNum + "s",""));
//                    }
                    sb.append(s.charAt(rowIndex));
                }
            }
//            sb.append("\n");
        }
        return sb.toString();
    }
}
