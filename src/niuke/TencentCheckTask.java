package niuke;

import java.util.Arrays;
import java.util.Scanner;

public class TencentCheckTask {
	  public static void check(int ID1,int ID2){
		  if(ID1>1024||ID1<1||ID2>1024||ID2<1)
			  System.out.println(-1);
		  int[] bitmap=new int[32];
		  Arrays.fill(bitmap, 0);
		  ID1--;
		  ID2--;
		  int pos1=ID1>>5;
		  int pos2=ID1&0x1f;
		  bitmap[pos1]|=(1<<(pos2));
		  pos1=ID2>>5;
		  pos2=ID2&0x1f;
		  System.out.println((bitmap[pos1]>>(pos2))&1);
	  }
      public static void main(String[] args){
    	 Scanner in =new Scanner(System.in);
    	  int ID1=in.nextInt();
    	  int ID2=in.nextInt();
    	  check(ID1, ID2);
      }
}
