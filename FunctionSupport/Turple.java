/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FunctionSupport;

/**
 *
 * @author Lê Minh
 */
public class Turple<A,B> {
    private final A first;
    private final B second;
    
    public Turple(){
        this.first = null;
        this.second = null;
    }
    
    public Turple(A first , B second){
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
    
    

