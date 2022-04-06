package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
      pastaDeSaidas.createNewFile();
    }

    FileReader leitorArquivo = null;
    BufferedReader bufferedReader = null;
    FileWriter escritorArquivo = null;
    BufferedWriter bufferedWriter = null;

    try {
      for (File file : pastaDeEntradas.listFiles()) {

        leitorArquivo = new FileReader(file);
        bufferedReader = new BufferedReader(leitorArquivo);

        File saidas = new File(pastaDeSaidas, file.getName());

        escritorArquivo = new FileWriter(saidas);
        bufferedWriter = new BufferedWriter(escritorArquivo);

        String linha = bufferedReader.readLine();

        int count = 0;
        do {
          if (count == 0) {
            bufferedWriter.write(linha);
            linha = bufferedReader.readLine();
          }

          // faz a modificações
          // escreve no novo arquivo

          linha = bufferedReader.readLine();
          count += 1;

        } while (linha != null);

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

// 1-ler o aruivo
// 2-alterar o arquivo
// 3-salvar o arquivo
// 4-escrever o novo arquivo