import java.util.*;

public class AlgoD_1 {
    private static final String BLACK = "B", RED = "R",YELLOW ="Y",GREEN = "G";
    private static final int ANS_LENGTH = 4;
    private static final String INRIGHTPLACE="In Right Place >> ";
    private static final String RIGHTCOLORS="Right Colors >> ";
    public static void main(String[] args) {
        ArrayList<EXP> exps = new ArrayList<>();
        String [] COLORS =  {BLACK,RED,YELLOW,GREEN};
        Vector<String[]> states = makeNewState(COLORS);

        int counter = 1;

        counter = mBstWYToSolve(exps);

        int sugIndex = new Random().nextInt(states.size());
        save(exps,states.get(sugIndex));
        while (exps.get(exps.size()-1).inRightPlcae !=4){
            EXP exp = bestEXP(exps);
            for (int i = 0; i < states.size(); i++) {
                if(checkWithBestEXP(exp.expAr,exp.inRightPlcae,exp.rightColors,states.get(i),false)){
                    System.out.println(counter++);
                    save(exps,states.get(i));
                    states.remove(i);
                    break;
                }
            }
            System.out.println("=======================");
        }
    }
    /**
     * this method need to count the currentness of his suggests => number of current color counter varibale do that
     * */
    private static int numberOfRightColorCounter = 0;
    private static int mBstWYToSolve(ArrayList<EXP> exps) {
        int counter;
        save(exps,new String[]{"BLACK","BLACK","BLACK","BLACK"});
        save(exps,new String[]{"GREEN","GREEN","GREEN","GREEN"});
        save(exps,new String[]{"BLUE","BLUE","BLUE","BLUE"});
        save(exps,new String[]{"RED","RED","RED","RED"});
        counter = 4;
        if (numberOfRightColorCounter != ANS_LENGTH) {
            counter++;
            save(exps,new String[]{"WHITE","WHITE","WHITE","WHITE"});
        }
        if(numberOfRightColorCounter != ANS_LENGTH) {
            counter++;
            save(exps,new String[]{"YELLOW","YELLOW","YELLOW","YELLOW"});
        }
        return counter;
    }
    /**
     * save method suggest an arry and get your input. so need an scanner obj
     * */
    private static Scanner sc = new Scanner(System.in);
    private static void save(ArrayList<EXP> expHashMap, String[] ar){
        print("",ar);
        System.out.print(INRIGHTPLACE);
        int inRightPlace = sc.nextInt();
        System.out.print(RIGHTCOLORS);
        int rightColor = sc.nextInt();
        expHashMap.add(new EXP(ar,rightColor,inRightPlace));
        if(numberOfRightColorCounter <= ANS_LENGTH && inRightPlace == 1)
            numberOfRightColorCounter++;
    }
    /**
     * run once. to make all of state of 6 color
     * */
    private static Vector<String[]> makeNewState(String[] COLORS){
        Vector<String[]> nState = new Vector<>();
        for (String COLOR : COLORS)
            for (String COLOR1 : COLORS)
                for (String COLOR2 : COLORS)
                    for (String COLOR3 : COLORS)
                        nState.add(new String[]{COLOR,COLOR1,COLOR2,COLOR3,/*COLOR4*/});
        return nState;
    }
    private static boolean firsExCalc = true;
    private static EXP bestEXP(ArrayList<EXP> exps) {
        EXP[] eXps = new EXP[exps.size()];
        int c = 0;
        for (EXP i : exps) eXps[c++] = i;
        if (!firsExCalc) {
            insertionOnInRightPlace(eXps);
            insertionOnRightColor(eXps);
            for (EXP eXp : eXps) {
                System.out.println(eXp.toString());
            }
            return eXps[eXps.length - 1];
        }
        firsExCalc = false;
        String fec = "";
        for (EXP eXp : eXps)
            if (eXp.inRightPlcae == 1)
                fec = (fec.concat(eXp.expAr[0].concat(" ")));
        return new EXP(fec.split(" "), ANS_LENGTH, 0);
    }
    private static boolean checkWithBestEXP(String[] oldExp, int cuInP, int cuColor, String[] sug, boolean ff){
        if(ff)
        System.out.println("============================");
        int tmpCu=0 , tmpCuInP=0;
        String tmpE[]= new String[ANS_LENGTH], tmpS[]=new String[ANS_LENGTH];
        System.arraycopy(oldExp,0,tmpE,0,oldExp.length);
        System.arraycopy(sug,   0,tmpS,0,sug.length);
        if(ff) {
            print("tmpE current in place = " +cuInP+" current Color = "+cuColor , tmpE);
            print("tmpS", tmpS);
        }
        for (int i = 0; i < tmpE.length; i++) {
            if(tmpE[i].equals(tmpS[i])){
                tmpCuInP++;
                tmpCu++;
                if(ff)
                    System.out.println(tmpS[i]+"="+tmpE[i]+" , tmpCuInP++ , tmpCu++");
            }else{
                if(!isContain(tmpS, i,tmpS[i]) && isContain(tmpE, tmpE.length,tmpS[i],true)){
                    tmpCu++;
                    if(ff)
                        System.out.println(tmpS[i]+"!="+tmpE[i]+" tmpCu++");
                }
            }
        }
        if(ff)
            System.out.println("tmpCuInP = "+tmpCuInP+" tmpCu = "+tmpCu);
        if(ff)
            System.out.println("============================");
        return tmpCuInP >= cuInP-1 && tmpCuInP <= cuInP && tmpCu == cuColor;
    }

    private static boolean isContain(String[] ar, int end, String str, boolean onOter){
        for (int i = 0; i < end; i++) {
            if(ar[i].equals(str)) {
                if(onOter){
                    ar[i] = "";
                }
                return true;
            }
        }
        return false;
    }
    private static boolean isContain(String[] ar, int end, String str){
        return isContain(ar, end,str,false);
    }

    private static void insertionOnRightColor(EXP[] exps){
        for (int i = 1; i < exps.length; i++) {
            int j = i;
            while (j >= 1 && exps[j].rightColors < exps[j-1].rightColors){
                EXP tmp = exps[j];
                exps[j]=exps[j-1];
                exps[j-1] = tmp;
                j--;
            }
        }
    }
    private static void insertionOnInRightPlace(EXP[] exps){
        for (int i = 1; i < exps.length; i++) {
            int j = i;
            while (j >= 1 && exps[j].inRightPlcae < exps[j-1].inRightPlcae){
                EXP tmp = exps[j];
                exps[j]=exps[j-1];
                exps[j-1] = tmp;
                j--;
            }
        }
    }

    private static void print(String str, String[] s){
        if(!str.equals(""))
            System.out.println(str);
        for (String value : s)
            System.out.print(value + ", ");
        System.out.println();
    }

    private static class EXP {
        String[] expAr;
        int rightColors;
        int inRightPlcae;
        EXP(String[] expAr, int rightColors, int inRightPlcae) {
            this.expAr = expAr;
            this.rightColors = rightColors;
            this.inRightPlcae = inRightPlcae;
//        jam = rightColors + inRightPlcae;
        }
        @Override
        public String toString() {
            return String.format("cuCo=%d,inRightPlcae=%d", rightColors, inRightPlcae);
        }
    }
}
