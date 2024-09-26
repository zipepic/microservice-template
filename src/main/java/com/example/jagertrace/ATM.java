package com.example.jagertrace;

import java.util.*;

// 50, 100, 500, 1000, 5000
public class ATM {
    private int sumMoneys = 0;
    private int numMoneys = 0;
    private PriorityQueue<Money> moneysIn= new PriorityQueue<>();

    public List<Money> getMoneysIn() {
        return Collections.unmodifiableList(moneysIn.stream().toList());
    }

    synchronized void incasate(List<Money> moneys){
        moneys.forEach(m->
        {sumMoneys+=m.getValue();
            numMoneys++;
        moneysIn.add(m);
        });
        System.out.println(moneysIn);
    }
    synchronized List<Money> giveMoney(int nominal) throws IllegalStateException,IllegalArgumentException{
        if(nominal<=0){
            throw  new IllegalArgumentException("");
        }
        if(nominal>sumMoneys){
            throw  new IllegalStateException("");
        }
        int left = nominal;
        List<Money> toRemove = new ArrayList<>();
        List<Money> toSave = new ArrayList<>();
        int size = moneysIn.size();
        for(int i =0;i<size;i++){
            Money m = moneysIn.poll();
            if(left>=m.getValue()){
                left-=m.getValue();
                toRemove.add(m);
                if (left==0){
                    break;
                }
            }else {
                toSave.add(m);
            }
        }
        if (left==0){
            for(Money m:toRemove){
                moneysIn.remove(m);
                sumMoneys-=m.getValue();
                numMoneys--;
            }
        }else{
            moneysIn.addAll(toSave);
            throw new IllegalStateException();
        }
        moneysIn.addAll(toSave);
        return  toRemove;

    }

    public static void main(String[] args) {
        ATM bankomate = new ATM();
        bankomate.incasate(List.of(new TenRoubles(),
                new FiveTenRoubles(),
                new HoundredRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new TenRoubles(),
                new FiveThousandRoubles()
        ));
        System.out.println(bankomate.giveMoney(500));
        System.out.println(bankomate.getMoneysIn());
    }




}
abstract class Money implements Comparable<Money>{
    @Override
    public String toString(){
        return  String.valueOf(this.getValue());
    }
    abstract int getValue();
    @Override
    public int compareTo(Money o) {
        if(o.getValue()>this.getValue()){
            return 1;
        }
        if(o.getValue()<this.getValue()){
            return -1;
        }
        return 0 ;
    }
}

class TenRoubles extends Money{

    @Override
    int getValue() {
        return 10;
    }


}

class FiveTenRoubles extends Money{

    @Override
    int getValue() {
        return 50;
    }
}

class HoundredRoubles extends Money{

    @Override
    int getValue() {
        return 100;
    }
}

class ThousandRoubles extends Money{

    @Override
    int getValue() {
        return 500;
    }
}

class FiveThousandRoubles extends Money{

    @Override
    int getValue() {
        return 5000;
    }
}



