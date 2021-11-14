import org.junit.Assert;

public class Chuck
{
    public static final int NUM_DICE = 3;
    public GVdie[] dice;
    public int credits;
    private String message ;
    int Num;
    boolean bet[] = new boolean[]{false,false,false,false,false,false,false,false,false};
//    Arrays.fill(bet,false);
//    public static final int ONES = 1;
//    public static final int TWOS = 2;
//    public static final int THREES = 3;
//    public static final int FOURS = 4;
//    public static final int FIVES = 5;
//    public static final int SIXES = 6;
//    public static final int SMALL = 7;
//    public static final int FIELD = 8;
//    public static final int LARGE = 9;

    public Chuck() {

        credits = 10;

        dice = new GVdie[NUM_DICE];
        for(int i = 0; i < NUM_DICE; i++){
            dice[i] = new GVdie();
            dice[i].setDelay(25);
            dice[i].setBlank();
            dice[i].roll();
        }

        message = "Chuck-A-Luck!";

    }
    public String getMessage(){
        return message;
    }
    public int getCredits(){
        return credits;
    }
	public GVdie[] getDice() {
		// TODO Auto-generated method stub
		return dice;
	}
    private int getSumDiceValues(){
    	int sum=0;
        for(int i = 0; i < NUM_DICE; i++){
           sum+=dice[i].getValue();
        }
        return sum;
    }
    public boolean isDoubles (int num){
    	int count=0;
    	for(int i=0;i<NUM_DICE;i++){
    		if(dice[i].getValue()==num){
    			count+=1;
    		}
    	}
    	if(count==2)
    		return true;
    	else
    		return false;
    }
    private boolean isTriplets(){
    	int diceValue = dice[0].getValue();
    	for(int i=1;i<NUM_DICE;i++){
    		if(dice[i].getValue()!=diceValue){
    			return false;
    		}
    	}
    	return true;
    }
    private void checkLarge(){
    	int sumDiceValue = getSumDiceValues();
    	if(sumDiceValue>10 && !isTriplets()){
    		credits+=2;
    		message="Player Won";
    	}
    }
    private void checkSmall(){
    	int sumDiceValue = getSumDiceValues();
    	if(sumDiceValue<11 && !isTriplets()){
    		credits+=2;
    		message="Player Won";
    	}
    }
    private void checkField(){
    	if(getSumDiceValues()<8 || getSumDiceValues()>12){
    		credits+=2;
    		message="Player Won";
    	}
    }

    private boolean checkValue(int num){
    	int count=0;
    	for(int i=0;i<NUM_DICE;i++){
    		if(dice[i].getValue()==num){
    			count+=1;
    		}
    	}
    	if(count==1)
    		return true;
    	else
    		return false;
    }
    private void checkNumber(int num){
    	if(isTriplets()){
    		credits+=11;
    	}
    	if(isDoubles(num)){
    		credits+=3;
    		System.out.println("hello");
    	}
    	if(checkValue(num)){
    			credits+=2;
    			System.out.println("test");
    		}
    }
    private void checkAllBets(){
    	message= "Player Lost";
    	for(int i=0;i<9;i++){
    		if(bet[i]){
    			credits-=1;
    			if(i>=0 && i<=5){
    				System.out.println(i);
    				checkNumber(i+1);
    			}
    			else if(i==6){
    				checkField();
    			}
    			else if(i==7){
    				checkSmall();
    			}
    			else{
    				checkLarge();
    			}
    		}
    	}
    }
    
    public void addCredits(int amount){
    	if(amount>0){
    		credits+=amount;
    	}
    }
    public void placeBet(int betIndex){
    	if(betIndex>=1 && betIndex<=9)
    		bet[betIndex-1] = true;
    }
    public void cancelBet(int betIndex){
    	if(betIndex>=1 && betIndex<=9){
    		if(bet[betIndex-1]){
    			bet[betIndex-1] = false;
    		}
    	}
    }
    public void clearAllBets(){
    	for(int i=0;i<9;i++){
    		bet[i] = false;
    	}
    }
    public boolean enoughCredits(){
    	int countBets=0;
    	for(int i=0;i<9;i++){
    		if(bet[i]){
    			countBets+=1;
    		}
    	}
    	if(credits>=countBets)
    		return true;
    	else 
    		return false;
    }
    
    public void roll(){
    	if(enoughCredits()){
        	for(int i=0;i<NUM_DICE;i++){
        		dice[i].roll();
        	}
         	checkAllBets();
//         	System.out.println(message);
         	clearAllBets();	
    	}
    	else{
    		message = "Not enough credits";
    	}
    }
    
    public void reset(){
    credits = 10;
    message = "Chuck-A-Luck!";
    	for(int i=0;i<NUM_DICE;i++){
    		dice[i].setBlank();
    	}
    }
    private boolean checkValid(int v1,int v2,int v3){
    	if(v1>=1 && v1<=6){
    		if(v2>=1 && v2<=6){
    			if(v3>=1 && v3<=6){
    				return true;
    			}
    			else
    				return false;
    		}
    		else
    			return false;
    	}
    	else
    		return false;
    }
    public void testRoll(int v1,int v2,int v3){
    	if(checkValid(v1,v2,v3) && enoughCredits()){
        	GVdie d1 = new GVdie();
        	while(d1.getValue()!=v1){
        		d1.roll();
        	}
        	dice[0] = d1;
        	
        	GVdie d2 = new GVdie();
        	while(d2.getValue()!=v2){
        		d2.roll();
        	}
        	dice[1] = d2;
        	
        	GVdie d3 = new GVdie();
        	while(d3.getValue()!=v3){
        		d3.roll();
        	}
        	dice[2] = d3;
        	
//        	System.out.println(checkNumber(5));
    	}
    	checkAllBets();
    	clearAllBets();
    }   
}