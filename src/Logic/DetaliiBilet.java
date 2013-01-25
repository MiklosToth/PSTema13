/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Remus
 */
public class DetaliiBilet {
    
    public String id_spectacol;
    public String titlu_spectacol;
    public double incasari_spectacol;
    public int balcon_total;
    public int loja_total;
    public int parter_total;
    public int balcon_vandut;
    public int loja_vandut;
    public int parter_vandut;
    
    public DetaliiBilet(){}
    
    public DetaliiBilet(String id,String titlu,double incasari,int bt,int lt, int pt, int bv, int lv , int pv ){
        id_spectacol=id;
        titlu_spectacol=titlu;
        incasari_spectacol=incasari;
        balcon_total=bt;
        loja_total=lt;
        parter_total=pt;
        balcon_vandut=bv;
        loja_vandut=lv;
        parter_vandut=pv;
    }
}
