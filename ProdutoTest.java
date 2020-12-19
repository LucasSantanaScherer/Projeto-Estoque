package cadastro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProdutoTest {

//Execução do programa no método main
	public static void main(String[] args) {
		Teclado t = new Teclado();
		int opcao = 0;
		List<Produto> slist = new SinglyLinkedList<Produto>();
		do {
//Menu de opções
			System.out.println("1 – Adicionar produto\r\n");
			System.out.println("2 – Buscar produto\r\n");
			System.out.println("3 – Excluir produto\r\n");
			System.out.println("4 – Listar produtos\r\n");
			System.out.println("5 – Salvar produtos\r\n");
			System.out.println("6 – Abrir produtos\r\n");
			System.out.println("9 – Sair\r\n");
			opcao = t.leInt();
			switch (opcao) {
			
//Cadastra um produto
			case 1: {
				System.out.println("Digite os dados do produto: \r\n");
				Produto p = new Produto(t.leInt("Digite o ID do produto"), t.leString("Digite o nome do produto"),
						t.leDouble("Digite o preço do produto"));
				if (slist.search(p) == -1)
					slist.insert(p, slist.numElements());
				break;
			}
			
//Pergunta por qual parâmetro deseja buscar o produto (Nome ou ID). Se encontrado printa o produto e seus respectivos dados, caso contrário printa mensagem
			case 2: {
				if (slist.isEmpty())
					System.out.println("Não há produtos cadastrados");

				String option = ((t.leString("Deseja buscar por nome ou ID?")));
				Produto pro = new Produto(0, "", 0);

				if (option.equalsIgnoreCase("ID")) {
					pro.setId(t.leInt("Digite o ID do produto"));
				}

				else if (option.equalsIgnoreCase("Nome")) {
					pro.setNome((t.leString("Digite o nome do produto")));

				} else
					System.out.println("Digite uma opção válida");

				int pos = ((SinglyLinkedList<Produto>) slist).search(pro);

				if (pos == -1)
					System.out.println("Produto não encontrado");
				else {
					pro = ((SinglyLinkedList<Produto>) slist).get(pos);
					System.out.println("Código|Nome|Preço");
					System.out.println(pro.toString());
				}
				break;
			}
			
//Remove um produto pelo ID, se o ID não estiver cadastrado, printa mensagem.
			case 3: {
				Produto p = new Produto(t.leInt("Digite o ID do produto"), "", 0);
				int pos = ((SinglyLinkedList<Produto>) slist).search(p);
				if (pos != -1) {
					((SinglyLinkedList<Produto>) slist).remove(pos);
					System.out.println("Produto removido");
				} else
					System.out.println("Produto não encontrado");

				break;
			}
			
//Verifica se a lista está vazia, caso false printa os produtos cadastrados.
			case 4: {
				if (slist.isEmpty())
					System.out.println("Não há produtos cadastrados");
				else
					System.out.println("Código|Nome|Preço");
				for (int i = 0; i < slist.numElements(); i++)
					System.out.println(slist.get(i));

				break;
			}

//Salva o arquivo em .txt
			case 5: {

				if (slist.isEmpty()) {
					System.out.println("Para salvar insira um produto");
				} // fim do if
				else {
					File file = new File(
							"C:\\Users\\lucas\\Desktop\\" + t.leString("Digite o nome do arquivo") + ".txt");
					try (BufferedWriter bw = new BufferedWriter(new FileWriter((file)))) {
						Produto aux = new Produto(0, "", 0.0);
						bw.write("Código|Nome|Preço");
						for (int i = 0; i < slist.numElements(); i++) {
							aux = slist.get(i);
							bw.newLine();
							bw.write(aux.toString());
						} // fim do for
					} catch (IOException e) {
						System.out.println(e.getMessage());
					} // fim do catch
				}
				break;
			} // fim do case

//Carrega os dados do arquivo gerado para o programa em execução.

			case 6: {

				while (!slist.isEmpty())
					((SinglyLinkedList<Produto>) slist).removeLast();
				while (true) {
					String line = "";
					String file = t.leString("Digite o path do arquivo que deseja ler");
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						br.readLine();
						while ((line = br.readLine()) != null) {
							String[] args1 = line.split(";");
							Produto produto = new Produto(Integer.valueOf(args1[0]), args1[1],
									Double.valueOf(args1[2]));
							((SinglyLinkedList<Produto>) slist).insertLast(produto);
						}
					} catch (IOException e) {
						e.getMessage();
						e.printStackTrace();
					}

					break;
				}
			}
			}
		} while (opcao != 9);
	}
}