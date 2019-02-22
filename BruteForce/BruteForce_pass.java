import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BruteForce_pass {
    private static int[] foundedDigits = new int[10];
    private static int foundedDigitsCounter=0;
    private final static int[] _TIMES = {20,16,12,8,4,0};
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        firstSuggests();
        Vector<Integer[]> states = makeAllStates();
//        printVector(states); for use, uncomment its method
        int lastTime;
        Integer[] bestExperience;
        int firstSug = new Random().nextInt(states.size());
        bestExperience=states.get(firstSug);
        System.out.println("1 - "+Arrays.toString(bestExperience));
        lastTime = sc.nextInt();
        if(lastTime == 0)
            finish();
        states.remove(firstSug);
        int counter = 1;
        do {
            // 22156
            // 45452
            // 70000
            // 36152
            // 98175
            // 54231

            Integer[] tmp = getASuggest(states,bestExperience,lastTime);
//            System.out.println("bestEXP: "+Arrays.toString(bestExperience)+" "+lastTime);
            System.out.println(++counter+" - "+Arrays.toString(tmp) /*+ " "+calcTime(bestExperience,tmp)*/);
            int time = sc.nextInt();
            if(time<lastTime){
                bestExperience = tmp;
                lastTime = time;
            }
        }while (lastTime!=0);
        finish();

    }

    private static Integer[] getASuggest(Vector<Integer[]>  states,
                                         Integer[]          lastExperience,
                                         int                lastTime) {
        int forceBound = lastTime-1;
        int badBound = lastTime;
        int counterLoop = 0;
        int boundedLoop = states.size()/2;
        Random random = new Random();
        while (true){
            int i = random.nextInt(states.size());
            if(counterLoop<=boundedLoop &&
                    calcTime(lastExperience,states.get(i)) <= forceBound)
                return states.remove(i);
            else if(counterLoop>boundedLoop
                    && calcTime(lastExperience,states.get(i))<=badBound)
                return states.remove(i);
            counterLoop++;
        }
    }
    private static int calcTime(Integer[] currentPass, Integer[] suggestPas){
        int c = 0;
        for (int i = 0; i < currentPass.length; i++) {
            if(currentPass[i].intValue()!=suggestPas[i].intValue())
                c++;
        }
        return c;
    }

//    private static void printVector(Vector<Integer[]> states) {
//        int c=0;
//        for (Integer[] state : states) {
//            System.out.println(++c + "-" + Arrays.toString(state));
//        }
//    }

    private static Vector<Integer[]> makeAllStates() {
        Vector<Integer[]> states = new Vector<>();
        int[] numberOfPass = new int[5];
        int k=0;
        for (int i = 0; i < foundedDigits.length; i++) {
            for (int j = 0; j < foundedDigits[i]; j++) {
                numberOfPass[k++] = i;
            }
        }
        for (int i = 0; i < numberOfPass.length; i++)
            for (int j = 0; j < numberOfPass.length; j++)
                for (int l = 0; l < numberOfPass.length; l++)
                    for (int m = 0; m < numberOfPass.length; m++)
                        for (int n = 0; n < numberOfPass.length; n++)
                            if(i!=j && i!=l && i!=m && i!=n
                                    && j!=l && j!=m && j!=n
                                    && l!=m && l!=n
                                    && m!=n) {
                                Integer[] tmp = new Integer[]{  numberOfPass[i],
                                                                numberOfPass[j],
                                                                numberOfPass[l],
                                                                numberOfPass[m],
                                                                numberOfPass[n]};
                                states.add(tmp);
                            }
        removeRepeats(states);
        return states;
    }

    private static void removeRepeats(Vector<Integer[]> states) {
        for (int i = 0; i < states.size(); i++) {
            Integer[] tmp1 = states.get(i);
            for (int j = i+1; j < states.size(); j++) {
                Integer[] tmp2 = states.get(j);
                int flag = 0;
                for (int l = 0; l < tmp1.length; l++) {
                    if(tmp1[l].intValue()!=tmp2[l].intValue()) {
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    // 1,2,3,4,5 remove index=1 value=2 and next copare is index=2 value = 3
                    // after remove: 1,3,4,5 but next compare index = 2 value = 4 and value = 3 :( pooof
                    states.remove(j);
                    j--; // j-- fix our problem
                }
            }
        }
    }

    private static void firstSuggests() {
        for (int i = 0; i < foundedDigits.length; i++) {
            System.out.println(i+""+i+""+i+""+i+""+i+"\ntime:");
            int time = sc.nextInt();
            for (int j = 0; j < _TIMES.length; j++) {
                if(time == _TIMES[j]){
                    foundedDigits[i] = j;
                    foundedDigitsCounter+=j;
                    break;
                }
            }
            if(time == 0){
                finish();
            }
            if(foundedDigitsCounter == 5)
                break;
        }
    }

    private static void finish() {
        System.out.println("FOUNDED");
        System.exit(1);
    }
}
