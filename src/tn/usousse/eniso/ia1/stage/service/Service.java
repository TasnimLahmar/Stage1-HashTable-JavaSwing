
package tn.usousse.eniso.ia1.stage.service;

import tn.usousse.eniso.ia1.stage.model.Node;
import tn.usousse.eniso.ia1.stage.model.Table;

import static java.lang.Math.abs;


public class Service {
    private Table table;

    public Service(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public boolean add(String v) {
        int index = hash(v);
        Node n = new Node(v, null);
        Node list = table.getNodes()[index];
        Node nc;
        nc = list;

        if (!(find(v))) {
            if (list == null) {
                list = n;
            } else {
                while (nc.next != null) {
                    nc = nc.next;
                }
                nc.next = n;
            }
            table.getNodes()[index] = list;

            return true;
        } else {
            return false;
        }

    }
    public int hash(String value) {
        int s = 0;
        int x = value.length() - 1;
        for (char i : value.toCharArray()) {

            s = (int) (((int) i) * Math.pow(31, x) + s);
            x = x - 1;

        }
        return abs(s % (table.getSize()));
    }

    public boolean remove(String v) {
        boolean state = false;
        int index = hash(v);
        Node list = table.getNodes()[index];
        Node previous = null;
        if (find(v)) {
            while (list != null) {
                if (list.value.equals(v)) {
                    if (previous == null) {
                        table.getNodes()[index] = list.next;

                    } else {
                        previous.next = list.next;
                    }
                }
                previous = list;
                list = list.next;


            }
            return true;
        } else {
            return false;
        }

    }


    public boolean find(String v) {
        boolean state = false;
        int index = hash(v);
        Node list = table.getNodes()[index];
        while (list != null) {
            if (list.value.equals(v)) {
                state = true;
                break;
            } else {
                list = list.next;
            }
        }
        return state;
    }

    public void list() {
        for (int i = 0; i < table.getNodes().length; i++) {
            System.out.print(i + "--");
            Node n = table.getNodes()[i];
            while (n != null) {
                System.out.print(n.value + "--");
                n = n.next;
            }
            System.out.println("null");
        }
        //the wrong code
        /*
        while(n!=null){
        System.out.println(i+"--"+n.value);
        n=n.next;
        }
        */
        //what went wrong
        
        /*
         the method list implementation you provided will only print the value 
        of the first node in each bucket and then set the table.nodes[i] to its 
        next node. This will modify the original table and remove all the remaining 
        nodes in each bucket, which is not the desired behavior.
        */
    }

}


