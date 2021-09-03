package principal;

import produtos.Produtos;
import java.util.Scanner;

public class Principal{

    private Produtos produtosList[] = new Produtos[5];
    private int tam = 0;

    public static void main(String[] args) {

        Principal principal = new Principal();
        principal.tituloMenu();
        principal.telaPrincipal();
    }
    private void telaPrincipal(){
        int opcao = 0;

        do{
            opcao = opcaoMenuPrincipal();
            switch (opcao){
                case 1:
                    menuCadastroProduto();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    reajusteDePreco();
                    break;
                case 4:
                    relatorioProduto();
                    break;
                case 0:
                    System.out.println("Saindo do Sistema!...");
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        }while (opcao != 0);
    }

    private void relatorioProduto(){
        this.tituloMenu();
        System.out.println("-------------------------------");
        System.out.println("==== RELATÓRIO PRODUTOS ====");
        System.out.println("-------------------------------");
        for(int i = 0; i < tam; i++){
            //System.out.println("Produtos: \n" + "Código: " + i + "\n"+produtosList[i]);
            System.out.println("-------------------------------");
            System.out.println("|\tNome: " + produtosList[i].getNome());
            System.out.println("|\tPreço Unitario R$: " + produtosList[i].getPrecoUnitario());
            System.out.println("|\tUnidade de medida: " + produtosList[i].getUnidade());
            System.out.println("|\tQuantidade em estoque: " + produtosList[i].getQtdEstoque());
            System.out.println("-------------------------------");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aperte uma tecla +  enter para continuar");
        scanner.next();
    }

    private void menuMovimentacao(){
        Scanner scanner = new Scanner(System.in);
       this.tituloMenu();
        System.out.println("-------------------------------");
        System.out.println("==== MOVIMENTAÇÃO ====");
        System.out.println("-------------------------------");
        System.out.println("|\t1 - ENTRADA               |");
        System.out.println("|\t2 - SAIDA                 |");
        System.out.println("|\t0 - RETORNA               |");
        System.out.println("|\tOPÇÃO:                    |");
        System.out.println("-------------------------------");

        int opcaoMovimentacao = scanner.nextInt();

        switch (opcaoMovimentacao){
            case 1:
                compraProduto();
                break;
            case 2:
                saidaProduto();
                break;
            case 0:
                System.out.println("Retornar para o menu");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }
    private void compraProduto(){
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------------------");
            System.out.println("==== COMPRA DE PRODUTOS ====");
            System.out.println("-------------------------------");
            System.out.println("\tNome do Produto");
            String nomeProduto = scanner.nextLine();
            Produtos produtosMovimentacao;
            boolean controle = true;

            for(int i = 0; i < tam; i++){
                if(nomeProduto.equalsIgnoreCase(produtosList[i].getNome())){
                    controle = false;
                    produtosMovimentacao = produtosList[i];
                    System.out.println("-------------------------------");

                    System.out.println("|\tNome: " + produtosMovimentacao.getNome());
                    System.out.println("|\tPreço R$: " + produtosMovimentacao.getPrecoUnitario());
                    System.out.println("|\tUnidade: " + produtosMovimentacao.getUnidade());
                    System.out.println("|\tQuantidade Atual: "+ produtosMovimentacao.getQtdEstoque());
                    System.out.println("-------------------------------");
                    System.out.println("|\tQuantidade de Entrada: ");
                    int qtdEntrada = scanner.nextInt();
                    System.out.println("-------------------------------");

                    qtdEntrada = produtosMovimentacao.getQtdEstoque() + qtdEntrada;

                    System.out.println("|\tQuantidade final: "+ qtdEntrada);
                    System.out.println("-------------------------------");

                    escolha = confirmaOperacao();
                    System.out.println("-------------------------------");
                    if(escolha.equalsIgnoreCase("S")){
                        produtosMovimentacao.setQtdEstoque(qtdEntrada);
                        produtosList[i] = produtosMovimentacao;

                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }while ( escolha.equalsIgnoreCase("S"));
    }

    private void saidaProduto(){
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------------------");
            System.out.println("==== CONSUMO DE PRODUTOS ====");
            System.out.println("-------------------------------");
            System.out.println("|\tNome do Produto");
            String nomeProduto = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < tam; i++){
                if(nomeProduto.equalsIgnoreCase(produtosList[i].getNome())){
                    controle=false;
                    Produtos produtosMovimentacao = produtosList[i];
                    System.out.println("-------------------------------");
                    System.out.println("|\tNome: " + produtosMovimentacao.getNome());
                    System.out.println("|\tPreço R$: " + produtosMovimentacao.getPrecoUnitario());
                    System.out.println("|\tUnidade: " + produtosMovimentacao.getUnidade());
                    System.out.println("|\tQuantidade Atual: "+produtosMovimentacao.getQtdEstoque());
                    System.out.println("-------------------------------");
                    System.out.println("|\tQuantidade Saída: ");
                    int qtdSaida = scanner.nextInt();
                    System.out.println("-------------------------------");

                    qtdSaida = produtosMovimentacao.getQtdEstoque() - qtdSaida;
                    System.out.println("-------------------------------");
                    System.out.println("|\tQuantidade Final: " + qtdSaida);
                    System.out.println("-------------------------------");

                    if(qtdSaida > produtosMovimentacao.getQtdEstoque() ){
                        System.out.println("Quantidade maior do que quantidade em estoque ");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if(escolha.equalsIgnoreCase("S")){
                        produtosMovimentacao.setQtdEstoque(qtdSaida);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        } while (escolha.equalsIgnoreCase("S"));
    }


    private void opcaoInvalida(){
        System.out.println("Opção Invalida");
    }

    private int opcaoMenuPrincipal(){
        int opcao;
        System.out.println("-------------------------------");
        System.out.println("\t==== MENU PRINCIPAL ====");
        System.out.println("-------------------------------");
        System.out.println("| 1 - CADASTRO DE PRODUTOS    |");
        System.out.println("| 2 - MOVIMENTAÇÃO            |");
        System.out.println("| 3 - REAJUSTE DE PREÇOS      |");
        System.out.println("| 4 - RELATÓRIOS              |");
        System.out.println("| 0 - FINALIZAR               |");
        System.out.println("| OPÇÃO:_                     | ");
        System.out.println("|-----------------------------|");

        opcao = getMenuPrincipal();
        return opcao;
    }

    private void menuCadastroProduto(){
        int opcao;
        System.out.println("-------------------------------");
        System.out.println("==== CADASTRO DE PRODUTOS ====");
        System.out.println("-------------------------------");
        System.out.println("| 1 - INCLUSÃO             |");
        System.out.println("| 2 - ALTERAÇÃO            |");
        System.out.println("| 3 - CONSULTA             |");
        System.out.println("| 4 - EXCLUSÃO             |");
        System.out.println("| 0 - RETORNAR             |");
        System.out.println("|--------------------------|");

        opcao = getMenuPrincipal();

        switch (opcao){
            case 1:
                cadastraProduto();

                break;
            case 2:
                setAlteraProduto();
                break;
            case 3:
                consultaProduto();
                break;
            case 4:
                excluirProduto();
                break;
            default:
                opcaoInvalida();
                break;
        }

    }

    private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("--------------------------------");
            System.out.println("==== PRODUTO NÃO ENCONTRADO ====");
            System.out.println("--------------------------------");
        }
    }

    private String confirmaOperacao(){
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("-------------------------------");
        System.out.println("Confirmar Operação (S/N) ?");
        System.out.println("-------------------------------");
        escolha = scanner.nextLine();
        return escolha;
    }

    private int getMenuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }

    private void tituloMenu(){
        System.out.println("----------------------------------------");
        System.out.println("==== SISTEMA DE CONTROLE DE ESTOQUE ====");
        System.out.println("----------------------------------------");
    }

    private void cadastraProduto(){
        String escolha;
        do{
            this.tituloMenu();
            System.out.println("-------------------------------");
            System.out.println("==== CADASTRO DE PRODUTOS ====");
            System.out.println("-------------------------------");
            Produtos produtos = setDadosProdutos();
            escolha = confirmaOperacao();
            if(escolha.equalsIgnoreCase("S")){
                    produtosList[tam] = produtos;
                    tam++;
            }
            escolha = getRepetirOperacao();
        }while (escolha.equalsIgnoreCase("S"));
    }

    public Produtos setDadosProdutos() {
        Scanner scanner = new Scanner(System.in);
        boolean encontrado = false;
        Produtos produtos = new Produtos();
        System.out.print("|\tDigite o nome do Produto: ");
        String nome = scanner.nextLine();

        for (int i = 0; i < tam; i++){
            if (nome.equalsIgnoreCase(produtosList[i].getNome())){
                encontrado = true;
                break;
            }break;
        }
        if (encontrado){
            System.out.println("-------------------------------");
            System.out.println("Produto ja cadastrado");
            System.out.println("-------------------------------");

        }else {
            System.out.print("|\tDigite o valor: ");
            double valor = scanner.nextDouble();
            System.out.print("|\tDigite a unidade de medida: ");
            String unidade = scanner.next();
            System.out.print("|\tDigite a quantidade: ");
            int quantidade = scanner.nextInt();

            //Setando as informações
            produtos.setNome(nome);
            produtos.setPrecoUnitario(valor);
            produtos.setUnidade(unidade);
            produtos.setQtdEstoque(quantidade);
        }

        return produtos;
    }

    private Produtos setAlteraProduto() {
        Produtos produtos = new Produtos();

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("==== ALTERAÇÃO DE PRODUTO ====");
        System.out.println("-------------------------------");
        String escolha;
      do {

          System.out.println("|\tInsira o nome do Produto ");
          String nome = scanner.next();
          boolean encontra = true;
          for (int i = 0; i < tam; i++){

              if (nome.equalsIgnoreCase(produtosList[i].getNome())){
                  System.out.println("-------------------------------");
                  System.out.println("==== PRODUTO ENCONTRADO ====");
                  System.out.println("-------------------------------");
                  System.out.println("Produto: " + produtosList[i].getNome());
                  System.out.println("Preço R$: " + produtosList[i].getPrecoUnitario());
                  System.out.println("Unidade: " + produtosList[i].getUnidade());
                  System.out.println("Quantidade em estoque: " + produtosList[i].getQtdEstoque());
                  System.out.println("-------------------------------");
                  encontra = false;
              }break;
          }
          if (!encontra){
              System.out.println("-------------------------------");
              System.out.println("==== INSIRA OS NOVOS DADOS ====");
              System.out.println("-------------------------------");
              System.out.print("|\tDigite o novo valor: ");
              double valor = scanner.nextDouble();
              System.out.print("|\tDigite a nova unidade de medida: ");
              String unidade = scanner.next();
              System.out.print("|\tDigite a nova quantidade: ");
              int quantidade = scanner.nextInt();
              System.out.println("-------------------------------");

              escolha = confirmaOperacao();

              //Inserção dos dados
              for (int i = 0; i < tam; i++){

                  produtosList[i].setPrecoUnitario(valor);
                  produtos.getNome();
                  produtosList[i].setUnidade(unidade);
                  produtosList[i].setQtdEstoque(quantidade);

                  System.out.println("-------------------------------");
                  System.out.println("==== PRODUTOS ATUALIZADOS ====");
                  System.out.println("-------------------------------");
                  System.out.println("Produto: " + produtosList[i].getNome());
                  System.out.println("Preço R$: " + produtosList[i].getPrecoUnitario());
                  System.out.println("Unidade: " + produtosList[i].getUnidade());
                  System.out.println("Quantidade em estoque: " + produtosList[i].getQtdEstoque());
                  System.out.println("-------------------------------");

                  scanner.nextLine();

              }

          }else {
              System.out.println("Produto não encontrado");
          }

          escolha = getRepetirOperacao();
      }while (escolha.equalsIgnoreCase("S"));
        return produtos;

    }


    private void consultaProduto(){
        String escolha;
        do{
            Scanner scan = new Scanner(System.in);

            this.tituloMenu();
            System.out.println("-------------------------------");
            System.out.println("==== CONSULTA DE PRODUTOS ====");
            System.out.println("-------------------------------");
            System.out.println("Informe o nome do produto a ser pesquisado");
            String nomePes = scan.nextLine();
            boolean controle = false;

            for(int i = 0; i < tam; i++){
                if(nomePes.equalsIgnoreCase(produtosList[i].getNome())){
                    controle = false;
                    System.out.println(produtosList[i].toString());

                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }while (escolha.equalsIgnoreCase("S"));
    }

    private void excluirProduto(){
        String escolha;
        do {
            Scanner scan = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("-------------------------------");
            System.out.println("==== EXCLUIR PRODUTO ====");
            System.out.println("-------------------------------");
            System.out.println("Informe o nome do produto a ser excluido");
            String nomePes = scan.nextLine();
            boolean controle = true;

            for (int i = 0; i < tam; i++){
                if (nomePes.equalsIgnoreCase(produtosList[i].getNome())){
                    controle = false;
                    System.out.println(produtosList[i].toString());
                    System.out.println("Confirma a exclusão do produto "+produtosList[i].getNome() + " (S/N) ?");
                    escolha = scan.next();

                    if(escolha.equalsIgnoreCase("S")){
                        for (int j = i; j < tam; j++){
                            produtosList[j] = produtosList[j + 1];
                            tam--;
                        }
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }while (escolha.equalsIgnoreCase("S"));
    }

    private void reajusteDePreco(){
        Scanner scanner = new Scanner(System.in);
        String escolha;
        do {
            double taxa = 0;
            System.out.println("-------------------------------");
            System.out.println("==== REAJUSTE DE PREÇO ====");
            System.out.println("-------------------------------");
            System.out.println("Insira o nome do produto");
            String nome = scanner.next();
            System.out.println("-------------------------------");
            for(int i = 0; i < tam; i++){
                if(nome.equalsIgnoreCase(produtosList[i].getNome())){
                    System.out.println("-----------------------------------------------");
                    System.out.println("|\tProduto: " + produtosList[i].getNome());
                    System.out.println("|\tValor Atual: " +produtosList[i].getPrecoUnitario());
                    System.out.println("|\tInsira o reajuste de preço em percentagem (%)");
                    System.out.println("------------------------------------------------");
                    double reajuste = scanner.nextDouble();


                    double percentual = reajuste / 100;

                    taxa = produtosList[i].getPrecoUnitario() + (percentual * produtosList[i].getPrecoUnitario());
                    produtosList[i].setPrecoUnitario(taxa);
                    System.out.println("-------------------------------");
                    System.out.println("|\tValor reajustado : " + produtosList[i].getPrecoUnitario());
                    System.out.println("-------------------------------");

                }
            }
            escolha = getRepetirOperacao();

        }while (escolha.equalsIgnoreCase("S"));
        }
}