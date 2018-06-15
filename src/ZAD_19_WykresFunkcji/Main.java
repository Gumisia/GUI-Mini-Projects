package ZAD_19_WykresFunkcji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame{

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        SwingUtilities.invokeLater(() -> createGUI());
    }

    private int xmin, xmax, ymin, ymax;
    private String wzorText;
    private List<Point> pointList = new ArrayList<>();
    private boolean first = true;
//
//
//    MyPanel myPanel;

    private boolean checkNegative(String text){
        boolean test=false;
        if(text.charAt(0)=='-'){
            test=true;
        }
        return test;
    }

    private void createGUI(){


//        pointList.add(new Point(-30, 30));
//        pointList.add(new Point(-20, 20));
//        pointList.add(new Point(-11, 10));
//        pointList.add(new Point(0, 0));
//        pointList.add(new Point(11, 10));
//        pointList.add(new Point(20, 20));
//        pointList.add(new Point(30, 30));

        MyPanel myPanel = new MyPanel(pointList);

//        myPanel = new MyPanel(pointList);
        Container container = new Container();
        JPanel jPanelEnd = new JPanel();
        Container containerAll = getContentPane();


        JTextField xminTextField = new JTextField();
        JTextField xmaxTextField = new JTextField();
        JTextField yminTextField = new JTextField();
        JTextField ymaxTextField = new JTextField();

        JTextField wzor = new JTextField();

        this.setTitle("Wykres");
        this.setBounds(-700, 200, 450, 550);
        this.setVisible(true);

        container.add(xminTextField);
        container.add(xmaxTextField);
        container.add(yminTextField);
        container.add(ymaxTextField);
        container.setLayout(new GridLayout(1,4));

        jPanelEnd.add(container);
        jPanelEnd.add(wzor);
        jPanelEnd.setLayout(new GridLayout(2,1));


        containerAll.setLayout(new BorderLayout());
        containerAll.add(myPanel, BorderLayout.CENTER);
        containerAll.add(jPanelEnd, BorderLayout.SOUTH);

        xminTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = xminTextField.getText();


                try {

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setValueXmin(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)){
                        setValueXmin(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    emptyJFieldEx("xmintf focus...");
                }


            }

        });

        xmaxTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = xmaxTextField.getText();


                try {

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setValueXmax(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)){
                        setValueXmax(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    emptyJFieldEx("xmaxtf focus...");
                }


            }

        });

        yminTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = yminTextField.getText();


                try {

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setValueYmin(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)){
                        setValueYmin(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    emptyJFieldEx("ymintf focus...");
                }


            }

        });

        ymaxTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = ymaxTextField.getText();


                try {

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setValueYmax(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)){
                        setValueYmax(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    emptyJFieldEx("ymaxtf focus...");
                }


            }



        });

        wzor.setText("5x^3+2x^2+1+5"); // bez tego nie odswiezaja sie wartosci

        wzor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {


                String text = wzor.getText(); // = wzor.getText();

                try {
                    setWzorText(text);
                    System.out.println("wzor.focus try WT="+getWzorText());
                    operation(getWzorText()); // bez tego wartosci w liscie sie nie odswiezaja

                } catch (StringIndexOutOfBoundsException ex){
                    emptyJFieldEx("wzor focus...");
                }


            }

        });

        System.out.println("after focus WT="+getWzorText());

        operation(wzorText); // bez tego nie rysuje, bo lista nie jest przekazana do mypanel

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private boolean numberTest(String text){
        char tab[] = text.toCharArray();
        boolean test = true;
        for(int i=0; (i<tab.length)&&(test); i++){
            if(tab[i]<'0'||tab[i]>'9') test = false;
        }
        if(!test){
            System.out.println("Number format error!");
        }

        return test;
    }

    private void setWzorText(String textCollect) {
        this.wzorText = textCollect;
    }
    public String getWzorText(){
        return wzorText;
    }
    private void setValueXmin(int x){
        this.xmin = x;
    }
    private void setValueXmax(int x){
        this.xmax = x;
    }
    private void setValueYmin(int x){
        this.ymin = x;
    }
    private void setValueYmax(int x){
        this.ymax = x;
    }


    private void emptyJFieldEx(String method){
        System.out.println("Empty String "+method);
//        System.out.println("Empty String - clear all values");
//        setValueXmin(0); setValueXmax(0); setValueYmin(0); setValueYmax(0);
    }

    private void operation(String textCollect){

        System.out.println("operation pobrany wzor =" +textCollect);


        if(first) {
            textCollect = "5x^3+2x^2+1+5"; // bez tego nie pobiera wzoru
            first = false;
            System.out.println("operation podmieniony wzor =" +textCollect);
        }


        try {


            char[] charText = textCollect.toCharArray();
            List<String> smallOper = new ArrayList<String>();
            boolean space = false;

//        int[] tab = new int[10];

            try {


                for (int i = 0; i < charText.length || (textCollect.isEmpty()); i++) {

                    if (i > 0 && (charText[i] == '-' || charText[i] == '+')) {
                        space = true;
                    }


                    if (space) {
                        //                System.out.println();
                        smallOper.add(textCollect.substring(0, i));
                        textCollect = textCollect.substring(i);
                        space = false;
                        i = 1;
                        charText = textCollect.toCharArray();
                    }
                    //            System.out.print(charText[i]);
                }

                System.out.println("Lista");
                for (String s : smallOper) {
                    System.out.println(s);
                }


                for (int i = -25; i < 25; i++) {
                    pointList.add(new Point(i, sumOfAll(smallOper, i)));
                }

                for (Point point : pointList) {
                    System.out.print("f(" + point.getX() + ")= ");
                    System.out.println(point.getY());
                }

//            double y = sumOfAll(smallOper, 1);
//            System.out.println(y);

//            int x=1;
//            int result = getSmallOperationValue(x, smallOper.get(4));
//            int result = getSmallOperationValue(x, "-122");
//            System.out.println("result"+result);


            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Empty list");
            }
        }catch (NullPointerException ex){
            System.out.println("ex operation");
        }


    }

//    private int getSmallOperationValue(int x, String text){
//        boolean findX = text.contains("x");
//        boolean findXSqrt = text.contains("x^");
//        int result = 0;
//        char firstChar = text.charAt(0);
//
//        if(!findX){
//            if(firstChar>='0'&&firstChar<='9')
//                return Integer.valueOf(text);
//            else
//                result = Integer.valueOf(text.substring(1));
//
//        } else if(!findXSqrt){
////            x
//
//        } else {
////            x^
//
//        }
//
//        if(firstChar=='-') result = result*(-1);
//        return result;
//    }

    private static double doMath(int x, String tekst){
        if(tekst.contains("x")){
            if(tekst.contains("x^"))
                return multiply(some(tekst, true), x);
            else
                return multiply(some(tekst,false), x);
        }
        else return noX(tekst);
    }

    private  static numberPower some(String t, boolean power){
        boolean minus = false;
        boolean second = false;
        if((t.charAt(0)=='-')){
            t=t.substring(1);
            minus = true;
        }else if(t.charAt(0)=='+'){
            t=t.substring(1);
        }
        char[] tab = t.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i < tab.length; i++ ){
            if(Character.isDigit(tab[i])){
                if(second){
                    sb2.append(tab[i]);
                }else{
                    sb.append(tab[i]);
                }

            }
            else second= true;
        }
        if(minus){
            sb.insert(0,'-');
        }
//        System.out.println(sb);
//        System.out.println(sb2);
        if(!power){
            return new numberPower( Integer.valueOf( sb.toString() ), 1) ;
        }
        return new numberPower(Integer.valueOf(sb.toString()), Integer.valueOf(sb2.toString()));

    }

    private static double multiply (numberPower p, int x){
        return p.getNumber()* Math.pow(x,p.getPower());

    }

    private static int noX(String a){
        if(a.charAt(0)=='-'){
            return -1*(Integer.valueOf(a.substring(1)));
        }
        else
            return Integer.valueOf(a);
    }

    private static  double sumOfAll(List<String> list, int x){
        double sum = 0;
        for(String s : list){
            sum+= doMath(x,s);
        }
        return sum;
    }


}
