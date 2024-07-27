import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashTable {
    private int size;
    private Map<Integer, List<Business>> tableWithArrayLists;
    private Map<Integer, LinkedList<Business>> tableWithLinkedLists;

    public HashTable(int size) {
        this.size = size;
        this.tableWithArrayLists = new HashMap<>();
        this.tableWithLinkedLists = new HashMap<>();
        for (int i = 0; i < size; i++) {
            this.tableWithArrayLists.put(i, new ArrayList<>());
            this.tableWithLinkedLists.put(i, new LinkedList<>());
        }
    }

    private int hashFunctionMultiplication(String key) {
        int hashCode = key.hashCode();
        double A = (Math.sqrt(5) - 1) / 2; // Constante (0 < A < 1)
        int hash = (int) (size * (hashCode * A % 1));
        return Math.abs(hash) % size;
    }

    private int hashFunctionDivision(String key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    public void insert(Business business, boolean useMultiplication, boolean useLinkedList) {
        String key = business.getId();
        int hashIndex = useMultiplication ? hashFunctionMultiplication(key) : hashFunctionDivision(key);
        if (useLinkedList) {
            this.tableWithLinkedLists.get(hashIndex).add(business);
        } else {
            this.tableWithArrayLists.get(hashIndex).add(business);
        }
    }

    public Business search(String id, boolean useMultiplication, boolean useLinkedList) {
        int hashIndex = useMultiplication ? hashFunctionMultiplication(id) : hashFunctionDivision(id);
        if (useLinkedList) {
            LinkedList<Business> list = this.tableWithLinkedLists.get(hashIndex);
            for (Business business : list) {
                if (business.getId().equals(id)) {
                    return business;
                }
            }
        } else {
            List<Business> list = this.tableWithArrayLists.get(hashIndex);
            for (Business business : list) {
                if (business.getId().equals(id)) {
                    return business;
                }
            }
        }
        return null;
    }
}
