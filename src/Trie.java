import java.util.Map;
import java.util.Stack;

public class Trie {

    // TO DO : Insert, Search, Remove

    private Node root;

    public Trie(){
        root = new Node('*');
    }

    /**
     * Insert a word into trie
     * @param word - a word consisting of ascii characters only
     */
    public void insert(String word){

        if(word != null && !word.isEmpty()){

            Node parent = root;
            Map<Character, Node> childrens = null;
            char ch = '0';

            // follow the three path as long as it contains the characters of the string
            for(int index = 0; index < word.length(); ++index){
                ch = word.charAt(index);
                childrens = parent.getChildrens();

                if(!childrens.containsKey(ch)) { // adding a sibling
                    parent.addChild(ch);
                }

                // update parent pointer to the child
                parent = childrens.get(ch);
            }

            // after every word ending flag the ending node to indicate it is a word
            parent.setIsEndCharOfWord(true);
        }
    }

    public boolean hasWord(String word){

        boolean hasFound = false;

        if(word != null && !word.isEmpty()){
            int len = word.length(), index = 0;
            char ch = '0';
            Node parent = root;
            Map<Character, Node> childrens = null;

            // start from the top of the trie and follow a path
            while(index < len){
                ch = word.charAt(index);
                childrens = parent.getChildrens();

                if(!childrens.containsKey(ch)){
                    break;
                }

                // follow the path in the trie
                parent = childrens.get(ch);
                ++index;
            }

            // check if it is really a word in the trie
            hasFound = (index == len && parent.isEndCharOfAWord());
        }

        return hasFound;
    }

    public void remove(String word){

        if(word != null && !word.isEmpty()){

            Stack<Node> stack = new Stack<>();
            int len = word.length(), index = 0;
            char ch = '0';
            Node parent = root;
            Map<Character, Node> childrens = null;

            while(index < len){
                childrens = parent.getChildrens();
                ch = word.charAt(index);

                if(!childrens.containsKey(ch)){
                    break;
                }

                stack.push(parent);
                parent = childrens.get(ch);
                ++index;
            }

            // if word is found then remove it
            if(index == len && parent.isEndCharOfAWord()){

                // if the word is from the root to the leaf
                if(parent.getChildrens().isEmpty()){
                    Node child = parent;

                    // child is the leaf node
                    while(!stack.isEmpty()){
                        parent = stack.pop(); // parent of the child

                        // if child has no children then remove it
                        if(child.getChildrens().isEmpty()){
                            System.out.println(child.getVal());
                            parent.removeChild(child.getVal());
                        }else {
                            System.out.println("removed till " + child.getVal());
                            System.out.println(child.getChildrens());
                            break;
                        }

                        child = parent;
                    }
                }else{
                    // flag to indicate that the word is deleted
                    parent.setIsEndCharOfWord(false);
                    System.out.println("Word removed flaged!");
                }
            }else{
                throw new RuntimeException("No such word: " + word);
            }
        }
    }

    public String toString(){
        return root.toString();
    }

}
