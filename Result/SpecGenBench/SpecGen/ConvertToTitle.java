class ConvertToTitle {
    //@ assignable \nothing;
    //@ ensures (\old(columnNumber) > 0) <==> (\result.length() >= 1);
    //@ ensures (\forall int i; 0 <= i && i < \result.length(); 'A' <= \result.charAt(i) && \result.charAt(i) <= 'Z');
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        //@ maintaining columnNumber >= 0;
        //@ maintaining sb != null;
        //@ maintaining (\forall int i; 0 <= i && i < sb.length(); 'A' <= sb.charAt(i) && sb.charAt(i) <= 'Z');
        //@ decreases columnNumber;
        while (columnNumber > 0) {
            int a0 = (columnNumber - 1) % 26 + 1;
            sb.append((char)(a0 - 1 + 'A'));
            columnNumber = (columnNumber - a0) / 26;
        }
        return sb.reverse().toString();
    }
}