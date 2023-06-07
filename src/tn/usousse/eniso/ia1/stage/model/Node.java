 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.usousse.eniso.ia1.stage.model;

/**
 *
 * @author ASUS
 */
public class Node {
    
   public String value;
   public Node next;
    
    public Node(String v, Node next) {
        this.value = v;
        this.next = null;
    } 

    public String getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
}

