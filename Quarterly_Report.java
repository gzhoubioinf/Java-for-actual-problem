import java.util.Currency;
import java.util.Locale;

public class Quarterly_Report{
    public Department[] depart  ;
    public static String currency = Currency.getInstance("GBP").getSymbol(Locale.UK) ;
    public static String[] quarterstr = new String[] {"1st","2nd","3rd","4th"};
    /*
    public boolean [] get_valid_quarter() {
    	double[][] sale = get_total_sales_depart_quarter();
    	boolean [] validquarter = new boolean[4];
       
    	for( int quart =0; quart<4 ; quart++) { 
    		double tmp = 0.0;
    		for(int i = 0 ; i< depart.length; i++) {
    			tmp = tmp + sale[i][quart];
    		}
    		if(tmp == 0  ) {
    			validquarter[quart] = false;
    		}
    		else {
    			validquarter[quart] = true;
    		}
    			
    		}
    	return validquarter;
    	}
    */
    public boolean [] get_valid_quarter() {
    	//double[][] sale = get_total_sales_depart_quarter();
    	boolean [] validquarter = new boolean[4];
       
    	for( int quart =0; quart<4 ; quart++) { 
    		double tmp = 0.0;
    		for(int i = 0 ; i< depart.length; i++) {
    			tmp +=  depart[i].sale_per_quarter[quart];
    		}
    		if(tmp == 0  ) {
    			validquarter[quart] = false;
    		}
    		else {
    			validquarter[quart] = true;
    		}
    			
    		}
    	return validquarter;
    	}
   

    
    // Q1
    

    public void show_total_sales_depart_quarter() {
    	
        boolean[] validquarter = get_valid_quarter();
    	for( int quart =0; quart<4 ; quart++) { 

    		if(validquarter[quart]) {
	    		System.out.println(quarterstr[quart]+" Quarter totals:");
	    		for(int dept =0; dept< depart.length; dept++) {
	    			System.out.println("\t"+depart[dept].name+" "
	    		    +currency +(1000*depart[dept].sale_per_quarter[quart]));
	    		}
    		}
    	}
    }
    
    // Q2
    public void show_performance() {
    	boolean[] validquarter = get_valid_quarter();
    	for( int quart =0; quart<4 ; quart++) { // quarter
    		if(!validquarter[quart]) {
    			continue;
    		}
    	
    		 int maxdept = 0;
    	     int mindept = 0;
    	     double maxv = depart[0].sale_per_quarter[quart] ; 
    	     double minv = depart[0].sale_per_quarter[quart];
    	     for( int dept=1;dept <depart.length; dept++) {
    	    	 if(depart[dept].sale_per_quarter[quart] > maxv ) {
    	    		 maxv = depart[dept].sale_per_quarter[quart] ; 
    	    		 maxdept = dept;
    	    	 }
    	    	 if(depart[dept].sale_per_quarter[quart] < minv ) {
    	    		 minv = depart[dept].sale_per_quarter[quart] ; 
    	    		 mindept = dept;
    	    	 }
    	     }
    	     System.out.println( quarterstr[quart]+" Quarter best: "+ depart[maxdept].name + " "
    	    		 +currency + (1000*depart[maxdept].sale_per_month[quart*3]) +"," 
    	    	     +currency + (1000*depart[maxdept].sale_per_month[quart*3+1]) +"," 
    	    	     +currency + (1000*depart[maxdept].sale_per_month[quart*3+2]) 
    	    		 );
    	     System.out.println( quarterstr[quart]+" Quarter worst: "+ depart[mindept].name + " "
    	    		 +currency + (1000*depart[mindept].sale_per_month[quart*3]) +"," 
    	    	     +currency + (1000*depart[mindept].sale_per_month[quart*3+1]) +"," 
    	    	    +  currency + (1000*depart[mindept].sale_per_month[quart*3+2]) 
    	    		 );
    		
    	}

    	
    }
    
    
    //Q3 
    public void show_mosteffectivemonth() {
    	String[] strmon = new String[] {"January", "Febrary","March", "April",
    			"May","June","July","Auguest","September","Octorber","November",
    			"December"};
    	
       boolean[] validquarter = get_valid_quarter();
       for(int quart = 0; quart < 4; quart++) {
    	   if(validquarter[quart]) {
    		   System.out.println("Effective month for "+ quarterstr[quart]+" Quarter:"); 
    		   for(int dept =0; dept < depart.length; dept++) {
    			  double maxm = depart[dept].sale_per_month[quart*3];
    			  int monind  = quart*3;
    			  for(int m =1; m<3; m++) {
    				  if(maxm < depart[dept].sale_per_month[quart*3+m]) {
    				      maxm  = depart[dept].sale_per_month[quart*3+m];
    				      monind = quart*3+m;
    				  }
    				  }
    			  System.out.println("\t"+ depart[dept].name+", "+ strmon[monind] 
    					  +","+currency+(1000*maxm)) ;
    		   }
    	   }  
       }
    	
    }
    
    //Q4 
    
    public void show_total_tax_quarter() {
  
    	double[] taxquart =  new double[4];
    	for(int dept= 0; dept < depart.length; dept++) {
    		double[] tax = depart[dept].get_tax_per_quarter();
    		for(int t=0; t < tax.length; t++) {
    			taxquart[t] +=  tax[t];
    		}
    	}
    	
    	boolean[] validquarter = get_valid_quarter();
    	for(int quart=0; quart<4; quart ++ ) {
    		if(validquarter[quart]) {
    		System.out.println("Total tax for "+ quarterstr[quart] 
    		     +" quarter:" + currency +(1000*taxquart[quart]));
    		}
    	}
    			
    }
    
    //Q5 
    public void show_newsalestarget() {
    	
    	System.out.println("New sales target:") ;
    	for(int dept =0 ;dept < depart.length; dept ++) {
    	   System.out.println("\t"+ depart[dept].name + " " 
    	           + currency+(1000*depart[dept].get_salestarget())
    			   );
    	}
    }
    

public static void main(String args[]){
	Quarterly_Report store = new Quarterly_Report();
	store.depart = new Department[5];
	
	store.depart[0] = new Department(new double[] {0,0,0,67,63,78,78,104,104,0,0,0},"Electrical");
	store.depart[1] = new Department(new double[] {0,0,0,65,67,56,45,56,72,0,0,0},"Kitchen");
	store.depart[2] = new Department(new double[] {0,0,0,63,63,65,71,73,69,0,0,0},"Bathroom");
	store.depart[3] = new Department(new double[]  {0,0,0,18,24,22,19,17,16,0,0,0},"SoftFurnishing");
	store.depart[4] = new Department(new double[]  {0,0,0,16,23,21,19,20,19,0,0,0},"Accessories");
	
    System.out.println("Question -1 ");
	store.show_total_sales_depart_quarter();
	System.out.println("Question -2 ");
	store.show_performance();
	System.out.println("Question -3 ");
	store.show_mosteffectivemonth();
	System.out.println("Question -4 ");
	store.show_total_tax_quarter();
	System.out.println("Question -5 ");
	store.show_newsalestarget();
}


}