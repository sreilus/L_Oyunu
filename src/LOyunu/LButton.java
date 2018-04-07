/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOyunu;

import javax.swing.JButton;

/**
 *
 * @author Muhammet
 */
public class LButton extends JButton {

    public int indis;
    public byte durum;
    public boolean gidilebilirMi;

    public LButton(int indis) {
        this.durum = 0;
        this.indis = indis;
        this.gidilebilirMi = false;
        setSize(5, 5);
    }
}
