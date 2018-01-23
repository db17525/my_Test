package test.judgeArea;

import java.util.ArrayList;

public class AreaInfo {
	 public double lon;
	 public double lat;
	 
	 public AreaInfo()
	 {
	  lon = 0.0f;
	  lat = 0.0f;
	 }
	 
	 private boolean IsInCustomArea(double fLon, double fLat, ArrayList<AreaInfo> area)
	 {
		 boolean bRet = false;
		 
		 int nCount = area.size();
		 double x = fLon;
		 double y = fLat;
		 
		 int   i,j=nCount-1 ;
		 boolean  oddNodes=false;
		 
		 for (i=0;i<nCount; i++) 
		 {
			 if((area.get(i).lat< y && area.get(j).lat>=y
					 ||   area.get(j).lat<y && area.get(i).lat>=y)
					 && (area.get(i).lon<=x || area.get(j).lon<=x)) 
			 {
				 oddNodes^=(area.get(i).lon+(y-area.get(i).lat)/(area.get(j).lat-area.get(i).lat)*(area.get(j).lon-area.get(i).lon)<x);
				 
			 }
			 
			 j=i;
		 }
		 
		 if (oddNodes)
		 {
			 bRet = true;
		 }
		 return bRet;
	 }
	}

