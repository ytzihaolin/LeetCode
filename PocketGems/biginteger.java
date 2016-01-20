class bi{
        String val;
        int len;
        int sign;
        bi(String x){
            if(x.charAt(0)=='-') {
                this.sign=-1;
                x=x.substring(1);
            }
            else this.sign=1;
            this.val=new StringBuilder(x).reverse().toString();
            this.len=x.length();
        }
    

    	public String add(bi other){
            if(other.sign==-1&&this.sign==1){
                bi newother= new bi(other.val.substring(1));
                return this.minus(newother);
            }else if(this.sign==-1&&other.sign==1){
                return other.add(this);
            }
            int carry=0;
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<Math.min(this.len,other.len);i++){
                int temp=(this.val.charAt(i)-'0')+(other.val.charAt(i)-'0')+carry;
                sb.append(temp%10);
                carry=temp/10;
            }  

            for(int i=Math.min(this.len,other.len);i<Math.max(this.len,other.len);i++){
                char t='0';
                if(this.len>other.len)  t=this.val.charAt(i);
                else t=other.val.charAt(i);
                int temp=(t-'0')+carry;
                sb.append(temp%10);
                carry=temp/10;
            }
            if(carry!=0) sb.append(carry);
            if(this.sign==-1&&other.sign==-1) sb.append("-");
            return sb.reverse().toString();
        }

        public String mul(bi other){
            int[] prod=new int[other.len+this.len];
            for(int i=0;i<this.len;i++){
                for(int j=0;j<other.len;j++){
                    prod[i+j]+=(this.val.charAt(i)-'0')*(other.val.charAt(j)-'0');
                }
            }
            int carry=0;
            for(int i=0;i<prod.length;i++){
                int temp=prod[i]+carry;
                prod[i]=temp%10;
                carry=temp/10;
            }

            StringBuilder sb=new StringBuilder();
            for(int t:prod) sb.append(Integer.toString(t));
            while(sb.charAt(sb.length()-1)=='0') sb.deleteCharAt(sb.length()-1);
            if(this.sign*other.sign==-1) sb.append("-");
            return sb.reverse().toString();
        }

        public String minus(bi other){//minus not working well
            if(other.sign==-1) {
                bi newother=new bi(other.val.substring(1));
                return this.add(newother);
            }
            if(this.len<other.len){
                this.sign=-this.sign;
                other.sign=-other.sign;
                String res=other.minus(this);
                other.sign=-other.sign;
                this.sign=-this.sign;
                return res;
            }
            int carry=0;
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<other.len;i++){
                int temp=0;
                if(this.val.charAt(i)+carry<0){
                    temp=(this.val.charAt(i)-'0')-(other.val.charAt(i)-'0')+carry+10;
                    sb.append(temp<0?temp+10:temp);
                    carry=temp<0?-2:-1;
                }else{
                    temp=(this.val.charAt(i)-'0')-(other.val.charAt(i)-'0')+carry;
                    sb.append(temp<0?temp+10:temp);
                    carry=temp<0?-1:0;
                }
            } 
            for(int i=other.len;i<this.len;i++){
                char t=this.val.charAt(i);
                int temp=(t-'0')+carry;
                sb.append(temp<0?temp+10:temp);
                carry=temp<0?-1:0;
            }
            if(carry!=0){
                char k=sb.charAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
                sb.append(Integer.toString(10-(k-'0')));
                sb.append("-");
            }
            while(sb.charAt(sb.length()-1)=='0') sb.deleteCharAt(sb.length()-1);
            if(this.sign==1) return sb.reverse().toString();
            else return "-"+sb.reverse().toString();
        }
    }