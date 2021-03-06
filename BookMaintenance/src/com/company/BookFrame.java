package com.company;

/**
 * Created by chiyang on 12/21/16.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
        BtnAction();
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
       // update will be enabled after pressing Add button
       updateBtn.setEnabled(false);
        p4.add(deleteBtn);
        p4.add(exitBtn);

        JPanel p5= new JPanel();
        p5.add(firstBtn);
        p5.add(prevBtn);
       // always show the first one when init, so previous button disabled
       prevBtn.setEnabled(false);
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

    public void BtnAction(){
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if (set.next()){
                    prevBtn.setEnabled(true);
                    codeTxt.setText(set.getString("code"));
                    titleTxt.setText(set.getString("title"));
                    priceTxt.setText(set.getString("price"));
                }
                else{
                    set.previous();
                    nextBtn.setEnabled(false);
                }
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        prevBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (set.previous()){
                        nextBtn.setEnabled(true);
                        codeTxt.setText(set.getString("code"));
                        titleTxt.setText(set.getString("title"));
                        priceTxt.setText(set.getString("price"));
                    }
                    else{
                        set.next();
                        prevBtn.setEnabled(false);
                    }
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        lastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    set.last();
                    codeTxt.setText(set.getString("code"));
                    titleTxt.setText(set.getString("title"));
                    priceTxt.setText(set.getString("price"));
                    // // TODO: 12/21/16 when there's only one; both shall be disabled
                    prevBtn.setEnabled(true);
                    nextBtn.setEnabled(false);
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        firstBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    set.first();
                    codeTxt.setText(set.getString("code"));
                    titleTxt.setText(set.getString("title"));
                    priceTxt.setText(set.getString("price"));
                    // // TODO: 12/21/16 when there's only one; both shall be disabled
                    nextBtn.setEnabled(true);
                    prevBtn.setEnabled(false);
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    set.deleteRow();
                    statement.close();
                    set.close();
                    statement = client.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    set = statement.executeQuery("SELECT * FROM BOOK");
                    if (set.next()){
                        prevBtn.setEnabled(false);
                        nextBtn.setEnabled(true);
                        codeTxt.setText(set.getString("code"));
                        titleTxt.setText(set.getString("title"));
                        priceTxt.setText(set.getString("price"));
                    }
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // clear all textfields
                updateBtn.setEnabled(true);
                addBtn.setEnabled(false);
                codeTxt.setText("");
                titleTxt.setText("");
                priceTxt.setText("");
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fcode = codeTxt.getText();
                String ftitle = titleTxt.getText();
                String fprice =priceTxt.getText();

                try{
                    set.moveToInsertRow();
                    set.updateString("code",fcode);
                    set.updateString("title",ftitle);
                    set.updateString("price",fprice);
                    set.insertRow();

                    statement.close();
                    set.close();
                    statement = client.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    set = statement.executeQuery("SELECT * FROM BOOK");

                    if (set.next()){
                        prevBtn.setEnabled(false);
                        nextBtn.setEnabled(true);
                        updateBtn.setEnabled(false);
                        addBtn.setEnabled(true);
                        codeTxt.setText(set.getString("code"));
                        titleTxt.setText(set.getString("title"));
                        priceTxt.setText(set.getString("price"));
                        JOptionPane.showMessageDialog(null,"Record Added");
                    }

                }catch (Exception exc){
                    exc.printStackTrace();
                }

            }
        });


    }
}
