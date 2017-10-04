
package hamburgueria;

import java.util.List;


public class Pedido {
    private String dataFechamento;
    private int numPedido = 0;
    private double valorFinal;
    private boolean flagFechamento = true;

    public boolean isFlagFechamento() {
        return flagFechamento;
    }

    public void setFlagFechamento(boolean flagFechamento) {
        this.flagFechamento = flagFechamento;
    }
   

    public Pedido() {
    }
    public Pedido(int numPedido) {
        this.numPedido = numPedido;
    }

    @Override
    public String toString() {
        if(flagFechamento==true){
           return "Pedido de número "+ numPedido + " STATUS ABERTO";
        }else
        {
         return "Pedido de número "+ numPedido + " STATUS FECHADO";
        }
        
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

 

    public double getValorFinal() {
        return valorFinal;
    }

    private void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
    
    void acrescentaFinal(Double preco, Pedido p1){
        p1.setValorFinal(p1.getValorFinal() + preco);
}
    
}
