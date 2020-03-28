package hw3;
import java.awt.*;
import java.awt.event.*;

public class s20140310hw3 extends Frame implements ActionListener{
	
	private Frame f;
	private TextArea tf;
	private Panel p, p2;
	private String operation="";
	private int i;
	private int n;
	private double num[] = new double [5];
	private boolean resultFlag=false, minusFlag=false;
	
		public s20140310hw3() {
			f=new Frame("Calculator");
			f.setBackground(Color.gray);
			//f.setSize(300, 200);
			p = new Panel();
			tf=new TextArea(2, 30);
			tf.setEditable(false);
			p.add(tf, BorderLayout.CENTER);
			f.add(p,  BorderLayout.NORTH);
			p2 = new Panel();
			p2.setLayout(new GridLayout(4, 5, 5, 5));
			Button b7 = new Button("7");
			Button b8 = new Button("8");
			Button b9 = new Button("9");
			Button bDiv = new Button("/");
			Button bRef = new Button("C");
			Button b4 = new Button("4");
			Button b5 = new Button("5");
			Button b6 = new Button("6");
			Button bMul = new Button("*");
			Button bArr = new Button("<-");
			Button b1 = new Button("1");
			Button b2 = new Button("2");
			Button b3 = new Button("3");
			Button bSub = new Button("-");
			Button bLParen = new Button("(");
			Button b0 = new Button("0");
			Button bDot = new Button(".");
			Button bEqu = new Button("=");
			Button bPlus = new Button("+");
			Button bRParen = new Button(")");
			b7.addActionListener(this);
			b8.addActionListener(this);
			b9.addActionListener(this);
			bDiv.addActionListener(this);
			bRef.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			b6.addActionListener(this);
			bMul.addActionListener(this);
			bArr.addActionListener(this);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bSub.addActionListener(this);
			bLParen.addActionListener(this);
			b0.addActionListener(this);
			bDot.addActionListener(this);
			bEqu.addActionListener(this);
			bPlus.addActionListener(this);
			bRParen.addActionListener(this);
			p2.add(b7);
			p2.add(b8);
			p2.add(b9);
			p2.add(bDiv);
			p2.add(bRef);
			p2.add(b4);
			p2.add(b5);
			p2.add(b6);
			p2.add(bMul);
			p2.add(bArr);
			p2.add(b1);
			p2.add(b2);
			p2.add(b3);
			p2.add(bSub);
			p2.add(bLParen);
			p2.add(b0);
			p2.add(bDot);
			p2.add(bEqu);
			p2.add(bPlus);
			p2.add(bRParen);
			f.add(p2,  BorderLayout.CENTER);
			f.pack();
			WindowDestroyer listener=new WindowDestroyer();
			f.addWindowListener(listener);
			f.setVisible(true);
			
		}
		public void eval() {
			int token, numBegin;
			String number;
			int operator;

			token=getToken(n, operation);
			if(token==3) 
			{
				minusFlag=true;
				num[i]=0;
				i++;
				n++;
				token=getToken(n, operation);
			}
			if(token==7)
			{
				numBegin=n;
				do {n++;
				token=getToken(n, operation);
				}while(token==7);
				number=operation.substring(numBegin, n);
				num[i]=Double.parseDouble(number);
				if(minusFlag)
				{
					num[i-1]=num[i-1]-num[i];
					num[i]=0;
					i--;
					minusFlag=false;
				}
				i++;
			}
			while(n<operation.length()) {
				
					operator=getToken(n, operation);
					if(operator==1 || operator==6)
						{
							n++;							
							return;
						}
					else if(operator==0);
					else n++;
					token=getToken(n, operation);
					if(token==0) {
						n++;			
						this.eval();
					}
					else if(token==7) {
						numBegin=n;
						do {n++;
						token=getToken(n, operation);
						}while(token==7);
						number=operation.substring(numBegin, n);
						num[i]=Double.parseDouble(number);
						i++;				
					}
					switch(operator){
					case 2: {num[i-2]=num[i-2]+num[i-1]; num[i-1]=0; i--; break;}
					case 3: {num[i-2]=num[i-2]-num[i-1]; num[i-1]=0; i--; break;}
					case 4: {num[i-2]=num[i-2]*num[i-1]; num[i-1]=0; i--; break;}
					case 5: {num[i-2]=num[i-2]/num[i-1]; num[i-1]=0; i--; break;}
					}
			}		
		}
		
		public int getToken(int i, String str) {
			if(str.length()<=0) return 6;
			char symbol=str.charAt(i);
			switch(symbol)
			{	case '(': return 0;
				case ')': return 1;
				case '+': return 2;
				case '-': return 3;
				case '*': return 4;
				case '/': return 5;
				case '=': return 6;
				default: return 7;				
			}
		}
		public void actionPerformed(ActionEvent e) {
			String c=e.getActionCommand();
			if(resultFlag)
			{
				operation=""; tf.setText(operation);
				resultFlag=false;
			}
			switch(c) {
			case "<-": {if(operation.length()>0) {
						operation=operation.substring(0, operation.length()-1); tf.setText(operation); return;	}
						else return;}
			case ".": 
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9": 
			case "/":
			case "*":
			case "-":
			case "+": 
			case "(": 
			case ")": {operation = operation + c; tf.setText(operation); return;}
			case "C": {operation=""; tf.setText(operation); return;}
			case "=": {operation = operation + c;
						n=0;
						i=0;
						this.eval(); 
						resultFlag=true; 
						operation=operation+"\n";
						operation=operation+num[0];
						tf.setText(operation);
						return;}
			// TODO Auto-generated method stub
		}
	}

	public static void main(String args[]) {
		s20140310hw3 cal = new s20140310hw3();
	}


}
