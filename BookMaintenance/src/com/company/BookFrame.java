package com.company;

/**
 * Created by chiyang on 12/21/16.
 */

import java.awt.*;
import javax.swing.*;

public class BookFrame extends BookDB {
    JFrame f = new JFrame();
    JLabel codeLbl = new JLabel("Code:");
    JLabel titleLbl = new JLabel("Title:");
    JLabel priceLbl = new JLabel("Price:");
    JTextField codeTxt=new JTextField(8);
    JTextField titleTxt=new JTextField(20);
    JTextField priceTxt=new JTextField(5);
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton exitBtn = new JButton("Exit");
    JButton firstBtn = new JButton("First");
    JButton prevBtn = new JButton("Prev");
    JButton nextBtn = new JButton("Next");
    JButton lastBtn= new JButton("Last");

    public BookFrame() {
            frame();
    }

   public  void frame(){
        f.setTitle("Book Maintenance");
        f.setLayout(new FlowLayout());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.add(codeLbl);
        p1.add(codeTxt);

        JPanel p2 = new JPanel();
        p2.add(titleLbl);
        p2.add(titleTxt);

        JPanel p3 = new JPanel();
        p3.add(priceLbl);
        p3.add(priceTxt);

        JPanel p4 = new JPanel();
        p4.add(addBtn);
        p4.add(updateBtn);
        p4.add(deleteBtn);
        p4.add(exitBtn);

        JPanel p5= new JPanel();
        p5.add(firstBtn);
        p5.add(prevBtn);
        p5.add(nextBtn);
        p5.add(lastBtn);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.pack();

        // frame init: show the first result
        try{
            set.next();
            codeTxt.setText(set.getString("code"));
            titleTxt.setText(set.getString("title"));
            priceTxt.setText(set.getString("price"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
