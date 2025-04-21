import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkUpClass {
    /*The ballot class that may contain the propositions*/
    class Ballot{
        List<Proposition> props = new ArrayList<>();
        public Ballot(){}
    }

    /*
    * This will hold the details of each individual proposition including a list of the options*/
    class Proposition{
        private String title, proposition, description;
        private OptionsList options;
        private int numOptions;

        public void addOptions(ArrayList<String> c){
            options = new OptionsList(c);
        }
        public void addDescription(String d){ description = d;}
        public void addTitle(String t){title = t;}

        public String getProposition() {
            return proposition;
        }
        public String getDescription() {
            return description;
        }
        public String getTitle() {
            return title;
        }
        Boolean getOptionSelected(int index){return options.selectOptions(index);}
    }

    /*
    * Will contain the candidates, a boolean to confirm the selection and the index for the selection*/
    class OptionsList{
        private ArrayList<String> candidates;
        private Boolean selected;
        private int indexSelected;

        public OptionsList(ArrayList<String> c){
            selected = false;
            indexSelected = 6;
            candidates.addAll(c);
        }

        public void insertOptions(String opt, int size){
            candidates.add(opt);
            if(candidates.size() == size){}
        }

        public Boolean selectOptions(int select){
            if(!selected && candidates.size()<select){
                selected = true;
                indexSelected = select;
            }else {/*TODO: handle this error*/}
            return selected;
        }
    }

    /*
    * This is the class that will identify the keys and assign them to their respective category
    * More on tokens and pattern recognition https://www.w3schools.com/java/java_regex.asp */
    class Tokenizer{
        List<Map.Entry<String, String>> tokenize(String text){
            List<Map.Entry<String, String>> tokens = new ArrayList<>();
            Pattern p = Pattern.compile("/(\\w)\\s+(.*?)\\s+//\\1", Pattern.CASE_INSENSITIVE);
            Matcher m =p.matcher(text);

            while (m.find()){
                tokens.add(new AbstractMap.SimpleEntry<>(m.group(1), m.group(2)));
            }

            return tokens;
        }
    }
}
