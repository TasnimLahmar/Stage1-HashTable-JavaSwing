
package tn.usousse.eniso.ia1.stage.presentation.view;



import tn.usousse.eniso.ia1.stage.model.Node;
import tn.usousse.eniso.ia1.stage.model.Table;
import tn.usousse.eniso.ia1.stage.presentation.controller.HashtableController;
import tn.usousse.eniso.ia1.stage.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HashTableDrawComponent extends JComponent {
    private HashtableController controller;

    private Table model = new Table(3);


    public HashTableDrawComponent(HashtableController controller) {

        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int caseSize = 100;
                int startX = (getWidth() - caseSize) / 2;
                int startY = (getHeight() - (caseSize * controller.getModel().getSize())) / 2;
                int nodeIndex = (y - startY) / caseSize;
                Node node = controller.getModel().getNodes()[nodeIndex];
                if (node != null) {
                    int r = startX;
                    while (node != null) {
                        int nodeX = r + caseSize + 50;
                        int nodeY = startY + (caseSize * nodeIndex) + caseSize + 20;
                        if (x >= nodeX && x <= nodeX + caseSize - 20 && y >= nodeY - caseSize - 30 && y <= nodeY - 40) {
                            int dialogResult = JOptionPane.showConfirmDialog(HashTableDrawComponent.this,
                                    "Are you sure you want to delete this node?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                boolean removed = controller.getService().remove(node.getValue());
                                if (removed) {
                                    repaint();
                                } else {
                                    JOptionPane.showMessageDialog(HashTableDrawComponent.this, "Node not found.");
                                }
                            }
                            break;
                        }
                        r += caseSize + 30;
                        node = node.getNext();
                    }
                }
            }
        });
    }


  /*  public Table getModel() {
        return model;
    }*/

    public void setModel(Table model) {
        this.model = model;
        SwingUtilities.invokeLater(this::repaint);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int caseSize = 80;
        Table table = controller.getModel();
        int tableSize = table.getSize();
        int tableHeight = caseSize * tableSize;

        int startX = (getWidth() - caseSize) / 2;
        int startY = (getHeight() - tableHeight) / 2;
        int j = 70;
        Node[] nodes = table.getNodes();
        int y = startY ;
        for (int i = 0; i < nodes.length; i++) {
            y = startY + i * caseSize;
            g2.drawRect(startX, y, caseSize, caseSize);
            Node n = nodes[i];
            int r=startX;
            int z=caseSize;
            while (n != null) {
                drawNode(n, g2, caseSize, r, startY);
                r += caseSize+50;
                if(n.next==null){
                    drawMass(n,g2, caseSize, r, startY);}
                n = n.getNext();
            }

        }


    }

private void addMouseListenerToNodeRect(Node node, Rectangle rect) {
    addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rect.contains(e.getPoint())) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this node?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getService().remove(node.getValue());
                    setModel(controller.getModel());
                    repaint();
                }
            }
        }
    });
}


    private void drawNode(Node node, Graphics2D g2d, int caseSize, int startX, int startY) {
        Service service = new Service(model);
        int i = service.hash(node.getValue());
        int lineY = startY + caseSize * i + 50;
        g2d.drawLine(startX + caseSize, lineY, startX + caseSize + 50, lineY);
        int rectY = lineY + caseSize;
        g2d.drawRect(startX + caseSize + 50, rectY - caseSize - 30, caseSize - 20, caseSize - 40);
        g2d.drawString(node.getValue(), startX + caseSize + 75, lineY);
        g2d.drawLine(startX + caseSize * 2 + 30, lineY, startX + caseSize * 2 + 80, lineY);


    }

    private void drawMass(Node node, Graphics2D g2d, int caseSize, int startX, int startY) {
        int i=controller.getService().hash(node.getValue());
        int lineY=startY+caseSize*i+50;
        int lineX=startX+caseSize*2-50;
        g2d.drawLine(lineX,lineY+20,lineX,lineY-20);
        g2d.drawLine(lineX,lineY+10,lineX+10,lineY+20);
        g2d.drawLine(lineX,lineY-10,lineX+10,lineY);

    }


}

