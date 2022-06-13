package lb.hack.mshack.utils;

public class KeyWordSearchService {

    private int countKeyWords(String text, String[] keyWords) {
        int num = 0;
        String[] parts;
        if (keyWords.length == 1) {
            parts = text.split(keyWords[0]);
            num = parts.length - 1;
        }

        if (keyWords.length == 2) {
            parts = text.split(keyWords[0]);
            String[] subParts;
            for (int i = 0; i < parts.length; i++) {
                subParts = parts[i].split(keyWords[1]);
                if (subParts.length > 0) {
                    for (int j = 0; j < subParts.length; j++) {
                        if (subParts[j].equals("") || subParts[j].equals(" ") || subParts[j].equals("  ")) {
                            num = num + 1;
                        }
                    }
                }
            }

        }
        if (keyWords.length == 3) {
            parts = text.split(keyWords[0]);
            String[] subParts;
            String[] subSubParts;
            for (int i = 0; i < parts.length; i++) {
                subParts = parts[i].split(keyWords[1]);
                if (subParts.length > 0) {
                    for (int j = 0; j < subParts.length; j++) {
                        subSubParts = subParts[j].split(keyWords[1]);
                        if (subSubParts.length > 0) {
                            for (int k = 0; k < subSubParts.length; k++) {
                                if (subSubParts[k].equals("") || subSubParts[k].equals(" ") || subSubParts[k].equals("  ")) {
                                    num = num + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return num;
    }
}
