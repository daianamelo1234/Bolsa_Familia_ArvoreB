import arvore.BTree;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeBolsaFamilia {

    private static String obterValor(String linha[]) {
        return linha[7];
    }

    private static BTree<String, String> popularArvore(String arquivo) throws Exception {
        BTree<String, String> st = new BTree<String, String>();
        FileInputStream fin = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(fin, "cp1252");
        BufferedReader br = new BufferedReader(isr);
        String linha = br.readLine();
        linha = br.readLine();
        int contador = 0;
        while (linha != null && contador < 100000) {
            String valores[] = linha.split("\t");
            String nis = obterValor(valores);
            st.put(nis, nis);
            contador++;
            linha = br.readLine();
        }
        br.close();
        isr.close();
        fin.close();
        return st;
    }

    public static void main(String[] args) throws Exception {
        BTree<String, String> st = LeBolsaFamilia.popularArvore("C:\\201708_BolsaFamiliaFolhaPagamento.csv");
        FileInputStream fin = new FileInputStream("C:\\201707_BolsaFamiliaFolhaPagamento.csv");
        InputStreamReader isr = new InputStreamReader(fin, "cp1252");
        BufferedReader br = new BufferedReader(isr);
        String linha = br.readLine();
        linha = br.readLine();
        int naoEncontrado = 0;
        int contador = 0;
        while (linha != null && contador < 100000) {
            String valores[] = linha.split("\t");
            linha = br.readLine();
            contador++;
            String tmp = st.get(obterValor(valores));
            if (tmp == null) {
                naoEncontrado++;
                continue;
            }
        }
        br.close();
        isr.close();
        fin.close();

        System.out.println("NIS apenas em uma das listas: " + naoEncontrado);
    }
}


