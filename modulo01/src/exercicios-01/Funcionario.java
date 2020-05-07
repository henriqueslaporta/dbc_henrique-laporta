
public class Funcionario
{
    private String nome;
    private double salario_fixo;
    private double total_vendas;
    
    //Porcentagem
    private double p_imposto = (10.0/100);
    private double p_comissao = (15.0/100);
    private double p_imposto_comissao = (10.0/100);
    
    public Funcionario(String nome, double salario_fixo, double total_vendas)
    {
        this.nome = nome;
        this.salario_fixo = salario_fixo;
        this.total_vendas = total_vendas;
    }
    
    public double getLucro(){
        double salario_com_imposto = (salario_fixo * (1 - p_imposto) );
        double comissao_com_imposto = (total_vendas * p_comissao) - ((total_vendas * p_comissao) * p_imposto_comissao);
        
        return salario_com_imposto + comissao_com_imposto;
    }
    
}
