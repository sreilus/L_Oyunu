/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOyunu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;


/**
 *
 * @author Muhammet
 */
public class GuiDenemesi extends JFrame {

    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    JLabel lb = new JLabel();
    JLabel carpan;
    int sayac = 0;
    int gridCarpani;
    int gidisSayisi = 0;
    int sonIndis=0;
    public LButton buttons[];
    boolean basladiMi = false;
    JButton ayarlar = new JButton("Ayarlar");
    JButton tekrarOyna = new JButton("Tekrar Oyna");
    JButton hakkinda = new JButton("Hakkında");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GuiDenemesi(7);
    }

    public GuiDenemesi(int gridCarpaniA) {
        super("L Oyunu");
        gridCarpani = gridCarpaniA;
        setSize(gridCarpani * 80, gridCarpani * 80);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttons = new LButton[(gridCarpani * gridCarpani)];
        p.setSize(gridCarpani * 50, gridCarpani * 50);
        p2.setSize(150, 150);
        lb.setText("Tahminler");
        p2.add(lb);
        carpan = new JLabel("   " + gridCarpani + "x" + gridCarpani);
        ayarlar.setSize(100, 100);
        ayarlar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AyarlarJFrame((GuiDenemesi.this)).setVisible(true);
            }
        });
        tekrarOyna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yeniOyun(gridCarpani);
            }
        });
        ayarlar.setBorderPainted(true);
        p2.add(ayarlar);
        p2.add(tekrarOyna);
        hakkinda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hakkinda().setVisible(true);
            }
        });
        p2.add(hakkinda);
        p2.add(carpan);
        p.setLayout(new GridLayout(gridCarpani, gridCarpani));
        for (int i = 0; i < (gridCarpani * gridCarpani); i++) {
            buttons[i] = new LButton(i);
            p.add(buttons[i]);
        }
        add(p, BorderLayout.CENTER);
        add(p2, BorderLayout.NORTH);
        setVisible(true);
        addAction();
        renkSifirla();
    }

    public ActionListener createAction(LButton button) {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sayac = 0;
                if (basladiMi == false) {
                    basladiMi = true;
                    sonIndis=button.indis;                    
                    kontroller(button);
                    button.setForeground(Color.blue);
                } else {
                    if (button.gidilebilirMi == true) {
                        buttons[sonIndis].setForeground(Color.red);
                        kontroller(button);
                        button.setForeground(Color.blue);
                        sonIndis=button.indis;
                        if (sayac > 0) {
                            lb.setText(String.valueOf(sayac) + " tane gidebilceğin yer var");
                        } else {
                            lb.setText("Oyunu Kaybettin!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Buraya gidemezsin!");
                    }
                    if (gidisSayisi == (gridCarpani * gridCarpani)) {
                        lb.setText("Tebrikler Oyunu Kazandın!");
                    }
                }
            }

            private void kontroller(LButton button) {
                if (button.durum == 0) {
                    renkSifirla();
                    gidebilmeSifirla();
                    button.durum = 1;
                    gidisSayisi++;
                    button.setText(String.valueOf(gidisSayisi));
                    button.setForeground(java.awt.Color.red);
                    if (button.indis >= 0 && button.indis < (gridCarpani - 2) || (button.indis % gridCarpani) < (gridCarpani - 2) && button.indis < ((gridCarpani * gridCarpani) - gridCarpani)) {
                        if ((button.indis + (gridCarpani + 2)) < (gridCarpani * gridCarpani) && (button.indis + (gridCarpani + 2)) >= 0) {
                            if (buttons[button.indis + (gridCarpani + 2)].durum == 0) {
                                butonIslemleri(buttons[button.indis + (gridCarpani + 2)]);
                            }
                        }
                    }
                    if ((button.indis % gridCarpani) < (gridCarpani - 1) && button.indis < ((gridCarpani * gridCarpani) - (gridCarpani * 2))) {
                        if ((button.indis + (1 + gridCarpani)) < (gridCarpani * gridCarpani) && (button.indis + (1 + gridCarpani)) >= 0) {
                            if (buttons[button.indis + (1 + (gridCarpani * 2))].durum == 0) {
                                butonIslemleri(buttons[button.indis + (1 + (gridCarpani * 2))]);
                            }
                        }
                    }
                    if ((button.indis >= 2 && button.indis < gridCarpani) || (button.indis % gridCarpani) > 1 && button.indis < ((gridCarpani * gridCarpani) - gridCarpani)) {
                        if ((button.indis + (gridCarpani - 2)) < (gridCarpani * gridCarpani) && (button.indis + (gridCarpani - 2)) >= 0) {
                            if (buttons[button.indis + (gridCarpani - 2)].durum == 0) {
                                butonIslemleri(buttons[button.indis + (gridCarpani - 2)]);
                            }
                        }
                    }
                    if ((button.indis >= 1 && button.indis < gridCarpani) || (button.indis % gridCarpani) > 0 && button.indis < ((gridCarpani * gridCarpani) - (gridCarpani * 2))) {
                        if ((button.indis + ((gridCarpani * 2) - 1)) < (gridCarpani * gridCarpani) && (button.indis + ((gridCarpani * 2) - 1)) >= 0) {
                            if (buttons[button.indis + ((gridCarpani * 2) - 1)].durum == 0) {
                                butonIslemleri(buttons[button.indis + ((gridCarpani * 2) - 1)]);
                            }
                        }
                    }
                    if ((button.indis % gridCarpani) > 1 && button.indis >= gridCarpani) {
                        if ((button.indis - (gridCarpani + 2)) < (gridCarpani * gridCarpani) && (button.indis - (gridCarpani + 2)) >= 0) {
                            if (buttons[button.indis - (gridCarpani + 2)].durum == 0) {
                                butonIslemleri(buttons[button.indis - (gridCarpani + 2)]);
                            }
                        }
                    }
                    if ((button.indis % gridCarpani) != 0 && button.indis > (gridCarpani * 2)) {
                        if ((button.indis - ((gridCarpani * 2) + 1)) < (gridCarpani * gridCarpani) && (button.indis - ((gridCarpani * 2) + 1)) >= 0) {
                            if (buttons[button.indis - ((gridCarpani * 2) + 1)].durum == 0) {
                                butonIslemleri(buttons[button.indis - ((gridCarpani * 2) + 1)]);
                            }
                        }
                    }
                    if ((button.indis % gridCarpani) < (gridCarpani - 2) && button.indis >= gridCarpani) {
                        if ((button.indis - (gridCarpani - 2)) < (gridCarpani * gridCarpani) && (button.indis - (gridCarpani - 2)) >= 0) {
                            if (buttons[button.indis - (gridCarpani - 2)].durum == 0) {
                                butonIslemleri(buttons[button.indis - (gridCarpani - 2)]);
                            }
                        }
                    }
                    if ((button.indis % gridCarpani) < (gridCarpani - 1) && button.indis >= (gridCarpani * 2)) {
                        if ((button.indis - ((gridCarpani * 2) - 1)) < (gridCarpani * gridCarpani) && (button.indis - ((gridCarpani * 2) - 1)) >= 0) {
                            if (buttons[button.indis - ((gridCarpani * 2) - 1)].durum == 0) {
                                butonIslemleri(buttons[button.indis - ((gridCarpani * 2) - 1)]);
                            }
                        }
                    }
                    if (sayac > 0) {
                        lb.setText(String.valueOf(sayac) + " tane gidebilceğin yer var");
                    } else {
                        lb.setText("Oyunu Kaybettin");
                    }
                }
            }

            private void butonIslemleri(LButton button) {
                button.gidilebilirMi = true;
                button.setBackground(java.awt.Color.red);
                sayac++;
            }
        };

        return al;
    }

    public void addAction() {
        Component[] comps = p.getComponents();
        for (Component comp : comps) {
            if (comp instanceof LButton) {
                LButton button = (LButton) comp;
                button.addActionListener(createAction(button));
            }
        }
    }

    public void renkSifirla() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(java.awt.Color.LIGHT_GRAY);
        }
    }

    public void gidebilmeSifirla() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].gidilebilirMi = false;
        }
    }

    public void yeniOyun(int gridSayisi) {
        new GuiDenemesi(gridSayisi);
        this.dispose();
    }
}