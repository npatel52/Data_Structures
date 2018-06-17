import java.util.HashMap;
import java.util.Map;

public class Node {

    private char val;
    private Map<Character, Node> childrens;
    private boolean isEndChar;

    public Node(Character ch) {
        val = ch;
        childrens = new HashMap<>();
    }

    public char getVal(){
        return val;
    }

    public Map<Character, Node> getChildrens(){
        return childrens;
    }

    public boolean isEndCharOfAWord(){
        return isEndChar;
    }

    public void addChild(char ch){
        childrens.put(ch, new Node(ch));
    }

    public void setIsEndCharOfWord(boolean isWord){
        isEndChar = isWord;
    }

    public void removeChild(char val){
        childrens.remove(val);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(childrens.isEmpty()){
            sb.append("+");
        }else{
            for(Map.Entry<Character, Node> entry : childrens.entrySet()) {
                sb.append(entry.getKey());
                sb.append("->");
                sb.append(entry.getValue().toString());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
