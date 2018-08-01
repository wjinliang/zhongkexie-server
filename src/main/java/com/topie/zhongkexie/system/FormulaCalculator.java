package com.topie.zhongkexie.system;
 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
 
/**
 * @author zhangwenchao
 * @since 2016-08-26
 * 公式计算的工具类
 */
public class FormulaCalculator {
	
	private static boolean isRightFormat = true;
 
	public static double getResult(String formula){  
		double returnValue = 0;  
		try{  
			returnValue = doAnalysis(formula);  
		}catch(NumberFormatException nfe){   
			System.out.println("公式格式有误，请检查:" + formula);  
		}catch(Exception e){   
			e.printStackTrace();  
		} 
		if(!isRightFormat){  
			System.out.println("公式格式有误，请检查:" + formula);  
		} 
		return returnValue;	
	}
 
	private static double doAnalysis(String formula){
		double returnValue = 0;  
		LinkedList<Integer> stack = new LinkedList<Integer>();  
		int curPos = 0;  
		String beforePart = ""; 
		String afterPart = "";  
		String calculator = ""; 
		isRightFormat = true; 
		while(isRightFormat&&(formula.indexOf('(') >= 0||formula.indexOf(')') >= 0)){			
			curPos = 0;  
			for(char s : formula.toCharArray()){   
				if(s == '('){     
					stack.add(curPos);   
				}else if(s == ')'){     
					if(stack.size() > 0){     
						beforePart = formula.substring(0, stack.getLast());     
						afterPart = formula.substring(curPos + 1);     
						calculator = formula.substring(stack.getLast() + 1, curPos);      
						formula = beforePart + doCalculation(calculator) + afterPart;     
						stack.clear();     
						break;     
					}else{      
						System.out.println("有未关闭的右括号！");     
						isRightFormat = false;     
					}   
				}    
				curPos++;   
			}   
			if(stack.size() > 0){    
				System.out.println("有未关闭的左括号！");   
				break;   
			} 
		}  
		if(isRightFormat){  
			returnValue = doCalculation(formula); 
		}  
		return returnValue; 
	}
 	
	private static double doCalculation(String formula) {  
		ArrayList<Double> values = new ArrayList<Double>();  
		ArrayList<String> operators = new ArrayList<String>(); 
		int curPos = 0; 
		int prePos = 0;
		int minus = 0;		
		for (char s : formula.toCharArray()) { 
			 if ((s == '+' || s == '-' || s == '*' || s == '/') && minus !=0 && minus !=2) {  								 			 
				 values.add(Double.parseDouble(formula.substring(prePos, curPos).trim()));   				
				 operators.add("" + s);   				
				 prePos = curPos + 1;				 
				 minus = minus +1;
			 }else{				 
				  minus =1;				 
			 }
			 curPos++; 		
		}  
		values.add(Double.parseDouble(formula.substring(prePos).trim())); 
		char op;  
		for (curPos = 0; curPos <= operators.size() - 1; curPos++) {							
			op = operators.get(curPos).charAt(0);   
			switch (op) {  
			case '*':    
				values.add(curPos, values.get(curPos) * values.get(curPos + 1));   
				values.remove(curPos + 1);    
				values.remove(curPos + 1);   
				operators.remove(curPos);  
				curPos = -1;
				break;   
			case '/':   
				values.add(curPos, values.get(curPos) / values.get(curPos + 1));    
				values.remove(curPos + 1);    
				values.remove(curPos + 1);    
				operators.remove(curPos);  
				curPos = -1;
				break;   
			}  
		}  
		for (curPos = 0; curPos <= operators.size() - 1; curPos++) {   
			op = operators.get(curPos).charAt(0);  
			switch (op) {  
			case '+':    
				values.add(curPos, values.get(curPos) + values.get(curPos + 1));    
				values.remove(curPos + 1);    
				values.remove(curPos + 1);   
				operators.remove(curPos);  
				curPos = -1;
				break; 
			case '-':    
				values.add(curPos, values.get(curPos) - values.get(curPos + 1));   
				values.remove(curPos + 1); 
				values.remove(curPos + 1);   
				operators.remove(curPos);  
				curPos = -1;
				break;  
			} 
		}  
		return values.get(0).doubleValue();
	}
 
	public static String setNumberPrecision(double x,int Number){		   
		String p="#########0";		   
		if(Number==0){			 
			p="#########0";		  
		}else{		       
			p="#########0.";		      
			for(int i=0;i<Number;i++){//for的巧妙运用		         
				p=p+"0";  		     
			}		  
		}		      
		DecimalFormat f = new DecimalFormat(p);  		       
		String s = f.format(x).toString();  		      
		return s;		   
	}

	public static void main(String[] args) {	 
		System.out.println(setNumberPrecision(FormulaCalculator.getResult("3-(4*5)+5"),0));	
		System.out.println(FormulaCalculator.getResult("7/2-(-4)"));			
		System.out.println(FormulaCalculator.getResult("1287763200000-1276272000000")/(3600*24*1000));
	}
	
}