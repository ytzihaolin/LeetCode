第一题不是面经，但很简单。类似逆波兰表达式。输入是一个string ，比如“(＋1 5 6（＊ 3 （－5 1））)” 其对应的算式为： 1+5+6+3*（5-1）＝24，要求return 运算结果，也就是24
做过leetcode逆波兰的应该能秒，这个题不用担心一些invalid input 比如 单独一个括号 ( ) 或者（＋） 。 就考虑基本情况就好。总之秒了，做完这个题还剩半个多小时。




public void solve(){
		String a="(+1586(*3(-51)))";
		a.replace("\\s+","");
		Stack<Character> stack=new Stack<Character>();
		Stack<Integer> s2=new Stack<Integer>();
		int num=0;
		boolean flag=false;
		for(int i=0;i<a.length()-1;i++){
			if(a.charAt(i)=='+'||a.charAt(i)=='-'||a.charAt(i)=='*') stack.push(a.charAt(i));
			else if(a.charAt(i)=='('){
				if(flag) s2.push(num);
				num=0;
				flag=false;
			}else if(Character.isDigit(a.charAt(i))){
				if(!flag) num=a.charAt(i)-'0';
				else num=cal(num,stack.peek(),a.charAt(i)-'0');
				flag=true;
			}else if(a.charAt(i)==')'){
				stack.pop();
				num=cal(num,stack.peek(),s2.pop());
			}
		}
		System.out.println(num);
	}

	private int cal(int a, char b, int c){
		switch(b){
			case '+': return a+c;
			case '-': return a-c;
			case '*': return a*c;
		}
		return 0;
	}

	
}