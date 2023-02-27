public class Department{
    public String name;  //name of department
    public double[] sale_per_month = new double[12];
    public double[] sale_per_quarter = new double[4]; 
	
	// class constructor
    Department(double[] sale_per_month, String name)
    {
        this.sale_per_month = sale_per_month;
        this.name = name;
        for(int quart =0; quart < 4 ; quart++) {
        	for( int m =0; m<3; m++) {
        		this.sale_per_quarter[quart] += sale_per_month[quart*3+m];
        	}
        }
    }
 
	
    public double[] get_sale_per_quarter(){
    
	    double[] quarter_sale= new double[4];
	    for( int i =1; i< 5; i++){
	       double quart= 0;
	       for( int m =1; m<4; m++){
	            quart = quart + sale_per_month[(i-1)*3+m-1];    
	           } 
	         quarter_sale[i-1]=quart;
	    }
        return quarter_sale;
    }
    
    // show sales per quarter
    public void show_sale_per_quarter(){
        double[] sale = get_sale_per_quarter();
        for( int i = 0; i< sale.length; i++){
          System.out.println(name+" Quarter "+
                             (i+1)+" sale is "+sale[i]);
        }
        
    }
    // calculate the worst performance of the month per quarter 
    public  double[] get_worst_performance_per_quarter(){
        double[] min = new double[4];
        for(int i=1;i<5;i++){
      
            min[i-1]=sale_per_month[(i-1)*3];
            for (int x=(i-1)*3;x<i*3;x++){
                
                if (sale_per_month[x]<min[i-1]){
                    min[i-1]=sale_per_month[x];
                }
            }
           
           
        }
        return min;
    }
    
     public double[]  get_best_performance_per_quarter(){
        double[] max = new double[4];
        for(int i=1;i<5;i++){
   
            max[i-1]=sale_per_month[(i-1)*3];
            for (int x=(i-1)*3;x<i*3;x++){
                if (sale_per_month[x]>max[i-1]){
                    max[i-1]=sale_per_month[x];
                }
                
            }
          
           
        }
        return max;
    }
    
    
    public void show_performance_per_quarter(){
        double[] max  = get_best_performance_per_quarter();
        double[] min = get_worst_performance_per_quarter();
        System.out.println(name+" BEST and WORST performance:");
        for( int i =0 ; i < max.length; i++){
            System.out.println("Best_perfomance："+ max[i]);
            System.out.println("Worst_performance:" + min[i]);
        }
        
    }

    public double[] get_tax_per_quarter(){
       double[] tax_per_quarter= get_sale_per_quarter();
       for( int i= 0; i< tax_per_quarter.length; i++){
          tax_per_quarter[i] = tax_per_quarter[i]*0.17;   
         // System.out.println("tax for quarter "+ (i+1) +" is "+ tax_per_quarter[i]);   
          
       }
        return tax_per_quarter; 
    }
    
    
    
    public double get_salestarget(){
        double last_quarter =0 ;
        double[] sale_quarter=get_sale_per_quarter();
        for(int i=3;i>=0;i--){ 
           if (sale_quarter[i]!=0){
             last_quarter=sale_quarter[i];
             break;  
            }
       }
        double sale = 1.2*last_quarter/3.0;
       // System.out.println("Sale targe for " + name+ " : "+sale);
        return sale;
    }
        
        


} 




