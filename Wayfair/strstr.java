 public static int strStr(String needle, String stack) {

        int len1=needle.length();
        int len2=stack.length();
        if(len1>len2||len1*len2==0||needle==null||stack==null) return -1;
        char[] a=needle.toCharArray();
        char[] b=stack.toCharArray();
        for(int i=0;i<len2-len1+1;i++){

            for(int j=0;j<len1;j++){
                if(b[i+j]!=a[j]) break;
                else{
                    if(j==len1-1) return i;
                }
            }
        }
        return -1;
    }