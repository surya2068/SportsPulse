package sportspulse.data;
public final class ModuleSearch {
    private ModuleSearch(){}
    public static int kmp(String text,String pat) {
        if(pat.length()==0)return 0;
        int[] lps=new int[pat.length()];
        for(int i=1,len=0;i<pat.length();) {
            if(pat.charAt(i)==pat.charAt(len))lps[i++]=++len;
            else if(len>0)len=lps[len-1];else lps[i++]=0;
        }
        for(int i=0,j=0;i<text.length();) {
            if(text.charAt(i)==pat.charAt(j)){i++;j++;if(j==pat.length())return i-j;}
            else if(j>0)j=lps[j-1];else i++;
        }
        return -1;
    }
}
