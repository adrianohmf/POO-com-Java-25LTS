// Arquivo: Json.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utilitário mínimo de JSON, só para fins didáticos.
// Em projetos reais use uma biblioteca como Jackson ou Gson.
class Json {

    // Coloca um texto entre aspas, escapando aspas e barras
    static String texto(String valor) {
        String escapado = valor
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
        return "\"" + escapado + "\"";
    }

    // Monta um array JSON a partir de uma lista de livros: [ {...}, {...} ]
    static String lista(List<Livro> livros) {
        var partes = new ArrayList<String>();
        for (Livro livro : livros) {
            partes.add(livro.paraJson());
        }
        return "[" + String.join(",", partes) + "]";
    }

    // Monta um objeto JSON de erro: {"erro":"mensagem"}
    static String erro(String mensagem) {
        return "{\"erro\":" + texto(mensagem) + "}";
    }

    // Lê um objeto JSON simples (sem objetos aninhados) e devolve campo -> valor
    static Map<String, String> lerCampos(String json) {
        var campos = new HashMap<String, String>();
        Matcher m = Pattern
                .compile("\"(\\w+)\"\\s*:\\s*(\"((?:[^\"\\\\]|\\\\.)*)\"|-?\\d+)")
                .matcher(json);
        while (m.find()) {
            String valor = m.group(3) != null
                    ? m.group(3).replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n")
                    : m.group(2);
            campos.put(m.group(1), valor);
        }
        return campos;
    }
}
