package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;

public class Conversor {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada
   *                     ou
   *                     gravar os arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");

    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são
   * gerados
   * na pasta de saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página
   *                        web.
   * @param pastaDeSaidas   Pasta em que serão colocados os arquivos gerados no
   *                        formato
   *                        requerido pelo subsistema.
   *
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada
   *                     ou
   *                     gravar os arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    if (!pastaDeSaidas.exists()) {
      pastaDeSaidas.mkdir();
    }

    FileReader leitorArquivo = null;
    BufferedReader bufferedReader = null;
    FileWriter escritorArquivo = null;
    BufferedWriter bufferedWriter = null;

    try {
      for (File file : pastaDeEntradas.listFiles()) {

        leitorArquivo = new FileReader(file);
        bufferedReader = new BufferedReader(leitorArquivo);

        File novoArquivo = new File(pastaDeSaidas, file.getName());

        escritorArquivo = new FileWriter(novoArquivo);
        bufferedWriter = new BufferedWriter(escritorArquivo);

        String linha = bufferedReader.readLine();
        System.out.println(linha);

        int count = 0;
        do {
          if (count == 0) {
            bufferedWriter.write(linha);
            linha = bufferedReader.readLine();
          }
          // faz a formatação
          String[] conteudoLinha = linha.split(",");

          String nome = conteudoLinha[0].toUpperCase();
          String data = conteudoLinha[1];
          String email = conteudoLinha[2];
          String cpf = conteudoLinha[3];

          MaskFormatter mask = new MaskFormatter("###.###.###-##");
          String cpfFormatado = mask.valueToString(cpf);

          DateFormat formatUs = new SimpleDateFormat("yyyy-MM-dd");
          String dataFormatada = formatUs.parse(data).toString();

          String linhaFormatada = nome + "," + data + "," + email + "," + cpf;

          // escreve no novo arquivo
          bufferedWriter.write(linhaFormatada);
          linha = bufferedReader.readLine();
          count += 1;

          bufferedWriter.flush();
        } while (linha != null);
        System.out.println("LINHA 99");
        leitorArquivo.close();
        bufferedReader.close();
        escritorArquivo.close();
        bufferedWriter.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

// -criar diretório - ok
// -ler o arquivos- ok
// -alterar o arquivo
// -salvar o arquivo
// -escrever o novo arquivo