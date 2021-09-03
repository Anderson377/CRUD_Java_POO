package produtos;

public class Produtos{
    private String nome;
    private double precoUnitario;
    private String unidade;
    private int qtdEstoque;


    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

            this.nome = nome;

    }

    public double getPrecoUnitario() {

        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {

        if(precoUnitario > 0){
            this.precoUnitario = precoUnitario;
        }else {
            System.out.println("Valor do preço invalido");

            return;

        }
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        if (qtdEstoque < 0){
            System.out.println("Valor superior a quantidade em estoque");
        }else{
            this.qtdEstoque = qtdEstoque;
        }

    }

    public Produtos() {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.qtdEstoque = qtdEstoque;
    }
    public Produtos(double precoUnitario, String unidade, int qtdEstoque) {
        if((precoUnitario > 0 ) && (qtdEstoque >= 0)){
            this.precoUnitario = precoUnitario;
            this.unidade = unidade;
            this.qtdEstoque = qtdEstoque;
        }else {
            System.out.println("Valores invalido, repita a operação");
        }

    }


    @Override
    public boolean equals (Object obj){
        return ((Produtos)obj).nome != this.nome;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", unidade=" + unidade +
                ", qtdEstoque=" + qtdEstoque +
                '}';
    }

    public void setAdicionarQuantidade(int qtdEntrada) {


    }

    public void setDiminuirQuantidade(int qtdSaida) {
    }
}